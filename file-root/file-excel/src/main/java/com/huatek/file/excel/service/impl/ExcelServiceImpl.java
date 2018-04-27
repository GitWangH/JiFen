package com.huatek.file.excel.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.GsonBuilder;
import com.huatek.cmd.dto.CmdExportConfigDto;
import com.huatek.cmd.dto.CmdExportFieldConfigDto;
import com.huatek.cmd.dto.CmdImportConfigDto;
import com.huatek.cmd.dto.CmdImportDto;
import com.huatek.cmd.service.CmdExportRpcService;
import com.huatek.cmd.service.ExcelImportRpcService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.file.excel.dao.CommonDao;
import com.huatek.file.excel.exp.conversion.ConversionParamsForExcel;
import com.huatek.file.excel.exp.conversion.CreateExcelForCustomer;
import com.huatek.file.excel.exp.conversion.impl.ConversionParamsForExcelImpl;
import com.huatek.file.excel.exp.conversion.impl.CreateExcelForCustomerImpl;
import com.huatek.file.excel.imp.ImportConstant;
import com.huatek.file.excel.imp.ImportQueue;
import com.huatek.file.excel.imp.PersistTask;
import com.huatek.file.excel.imp.ValidateTask;
import com.huatek.file.excel.service.ExcelService;
import com.huatek.file.excel.util.ExcelUtils;
import com.huatek.file.excel.util.RemoteFileUtils;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.rpc.server.core.RpcService;
@RpcService(ExcelService.class)
public class ExcelServiceImpl implements ExcelService {
	 private Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);
	@Autowired
	RpcProxy rpc;
	@Autowired
	CommonDao dao;
	public Map<String,String> importExcel(String filePath, String busiCode,Map<String, String> params,UserInfo user)  {
		
		Map<String,String> result=new HashMap<String,String>();
		ExcelImportRpcService excelImportRpcService=rpc.create(ExcelImportRpcService.class);
		CmdImportConfigDto configDto=excelImportRpcService.getCmdImportConfigByBusiCode(busiCode);
		CmdImportDto importDto=new CmdImportDto();
		importDto.setImportConfigId(configDto.getId());
		importDto.setExcelPath(filePath);
		importDto.setParam(new GsonBuilder().create().toJson(params));
		importDto.setLog("");
		Long impId=null;
		try {
			impId=excelImportRpcService.saveCmdImportDto(importDto);
		} catch (Exception e1) {
			e1.printStackTrace();
			result.put("error", "保存出现异常");
			result.put("message", e1.getMessage());
			return result;
		}
		try{
			 excelImportRpcService.updateImportStatus(impId, ImportConstant.ImportStatus.SUBMIT, "提交成功");
		}catch(Exception e){
				e.printStackTrace(); 
		}
		ValidateTask validateTask=new ValidateTask();
		validateTask.init(impId,filePath, busiCode, params,user);
		String message;
		try {
			message = validateTask.call();
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", "验证出现异常");
			result.put("message", e.getMessage());
			return result;
		}
		if(message!=null){
			result.put("error", "验证不通过");
			result.put("message", message);
			return result;
		}
		PersistTask persistTask=new PersistTask();
		persistTask.init(impId,filePath, busiCode, params,user);
		result.putAll(ImportQueue.submit(persistTask));
		
		return result;
		
		
	}
	
	/**
	 *导出excel 
	 * @throws IOException 
	 */
	@Override
	public String exportExcel(CmdExportConfigDto cmdExportConfigDto, List<CmdExportFieldConfigDto> exportFieldList,Map<String, Object> params) throws IOException {

		List<Object[]> resultsList = null;
		//处理参数
		if(cmdExportConfigDto.getExportType().equals("export_model_type")){//默认导出
			resultsList = getResultsList(cmdExportConfigDto,exportFieldList,params);
		}else if(cmdExportConfigDto.getExportType().equals("export_service_type")){//业务人员自定义处理参数或结果集导出
			ConversionParamsForExcel conversionParamsForExcel = new ConversionParamsForExcelImpl();
			
			conversionParamsForExcel.initConversion(cmdExportConfigDto);
			Map<String, Object> newParams = conversionParamsForExcel.conversionParams(params);
			List<Object[]> oldResultsList = getResultsList(cmdExportConfigDto,exportFieldList,newParams);
			resultsList = conversionParamsForExcel.conversionResults(oldResultsList,params);
		}
		
		Workbook wb = null;
		CmdExportRpcService cmdExportRpcService=rpc.create(CmdExportRpcService.class);
	byte[] templdateData=cmdExportRpcService.getTemplateData(cmdExportConfigDto.getCode());
	if(templdateData!=null){
		wb=new XSSFWorkbook(new ByteArrayInputStream(templdateData));
	}
		if(params != null && params.get("create_excel_customer_service") != null){//自定义创建excel
			CreateExcelForCustomer createExcelForCustomer = new CreateExcelForCustomerImpl();
			createExcelForCustomer.initConversion(params.get("create_excel_customer_service").toString());
			
			wb = createExcelForCustomer.createExcel(cmdExportConfigDto,resultsList, exportFieldList, params,wb);
		}else{//默认创建excel
			//写入Excel
			wb = ExcelUtils.getExcel(cmdExportConfigDto,resultsList, exportFieldList, params,wb);
		}
		
		String filepath = "";
		if(null!=wb){
			filepath = RemoteFileUtils.saveExcelFile(wb);
		}
		cmdExportRpcService.log(cmdExportConfigDto.getCode(), cmdExportConfigDto.getName(), filepath, params.toString());
		return filepath;
	}
	/**
	 * 得到导出结果集
	 * */
	public List<Object[]> getResultsList(CmdExportConfigDto cmdExportConfigDto, List<CmdExportFieldConfigDto> exportFieldList,Map<String, Object> params){
		if(null==cmdExportConfigDto || cmdExportConfigDto.getHqlContent().trim().equals("null") || null==exportFieldList || exportFieldList.size()==0){
			return null;
		}
		//得到hql语句
		StringBuffer fileHql = new StringBuffer();
		StringBuffer conditionsHql = new StringBuffer();
		for (CmdExportFieldConfigDto exportFieldConfig : exportFieldList) {
			String tableAliasName = exportFieldConfig.getTableAliasName();//表别名
			String fieldName = exportFieldConfig.getFieldName();//字段名
			String fieldAliasName = exportFieldConfig.getFieldAliasName();//字段别名
			if(exportFieldConfig.getIsExport().equals("1")){//需要导出
				if(StringUtils.isNotEmpty(fieldAliasName)){
					fileHql.append(fieldName+" as "+fieldAliasName+",");
				}else{
					fileHql.append(tableAliasName +"."+ fieldName+",");
				}
			}
			
			String conditionsName = exportFieldConfig.getConditionsName();//条件字段名称
			String conditionsOper = exportFieldConfig.getConditionsOperation();//运算条件
			//是条件且有传该参数 
			if(StringUtils.isNotEmpty(conditionsName) && params.containsKey(conditionsName) && checkValue(params.get(conditionsName))){
				
				if(StringUtils.isNotEmpty(conditionsOper)){//指定条件运算
					
					if(conditionsOper.equals("1")){
						conditionsHql.append(" and "+tableAliasName+"."+ fieldName +" = :"+conditionsName);
					}else if(conditionsOper.equals("2")){
						conditionsHql.append(" and "+tableAliasName+"."+ fieldName +" > :"+conditionsName);
					}else if(conditionsOper.equals("3")){
						conditionsHql.append(" and "+tableAliasName+"."+ fieldName +" < :"+conditionsName);
					}else if(conditionsOper.equals("4")){
						conditionsHql.append(" and "+tableAliasName+"."+ fieldName +" like :"+conditionsName);
						Object paramValue = params.get(conditionsName);
						paramValue = "%" + paramValue + "%";
						params.put(conditionsName, paramValue);
						
					}else if(conditionsOper.equals("5")){
						conditionsHql.append(" and "+tableAliasName+"."+ fieldName +" in (:"+conditionsName+") ");
					}else if(conditionsOper.equals("6")){
						conditionsHql.append(" and "+tableAliasName+"."+ fieldName +" >= :"+conditionsName);
					}else if(conditionsOper.equals("7")){
						conditionsHql.append(" and "+tableAliasName+"."+ fieldName +" <= :"+conditionsName);
					}
				}else{//是条件但未指定运算符，默认为等号
					conditionsHql.append(" and "+tableAliasName+"."+ fieldName +" = :"+conditionsName);
				}
				
			}
		}
		String queryHql ="select "+fileHql.toString().substring(0, fileHql.length()-1) +" "+ cmdExportConfigDto.getHqlContent();
		
		//用户自定义权限控制语句
		if(params != null && params.size() > 0){
			if(null!=params.get("table_name_hql") && !params.get("table_name_hql").equals("")){
				queryHql += " "+params.get("table_name_hql");//动态拼接表名
			}
			if(null!=params.get("user_permissions_hql") && !params.get("user_permissions_hql").equals("")){
				queryHql += " "+params.get("user_permissions_hql");//拼接权限
			}
		}
		queryHql += conditionsHql.toString();//拼接条件
		if(StringUtils.isNotEmpty(cmdExportConfigDto.getHqlConditions())){
			queryHql += " "+cmdExportConfigDto.getHqlConditions();
		}
		List<Object[]> results = new ArrayList<Object[]>();
		if(StringUtils.isEmpty(cmdExportConfigDto.getSqlType()) || "1".equals(cmdExportConfigDto.getSqlType())){
			results = dao.findResultsByHql(queryHql, params);
		}else{
			// sql
			results = dao.findResultsBySql(queryHql, params);
		}
		//处理结果集
		return results;
	}
	//判断object是否为空
	private boolean checkValue(Object conditions){
		Class retType = conditions.getClass();
		if ( Collection.class.isAssignableFrom( retType ) ) {
			Collection cc = (Collection)conditions;
			return !cc.isEmpty();
		}
		else if ( retType.isArray() ) {
			Object[] cc = (Object[])conditions;
			return cc.length>0?true:false;
		}
		else {
			return StringUtils.isNotEmpty(conditions.toString());
		}
	}
}
