package com.huatek.file.excel.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.context.ContextLoader;

import com.huatek.cmd.service.ExcelImportRpcService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.file.excel.imp.validate.IValidate;
import com.huatek.file.excel.imp.validate.IValidateService;
import com.huatek.file.excel.imp.validate.ValidateFactory;
import com.huatek.file.excel.imp.validate.ValidateResult;
import com.huatek.file.excel.util.ExcelUtils;
import com.huatek.file.excel.util.RemoteFileUtils;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.session.data.UserInfo;

public class ValidateTask implements Callable<String> {
	private Long impId;
	private String filePath;
	private String busiCode;
	private Map<String, String> params;
	private UserInfo user;
	private RpcProxy rpc;
	public void init(Long impId,String filePath, String busiCode,Map<String, String> params,UserInfo user){
		this.impId=impId;
		this.filePath=filePath;
		this.busiCode=busiCode;
		this.params=params;
		this.user=user;
		rpc=ContextLoader.getCurrentWebApplicationContext().getBean(RpcProxy.class);
	}
	public String call() throws Exception {
		ExcelImportRpcService excelImportRpcService=rpc.create(ExcelImportRpcService.class);
		ImportConfig impotConfig=BeanCopy.getInstance().convert(excelImportRpcService.getCmdImportConfigByBusiCode(busiCode),ImportConfig.class);
		List<ImportFieldConfig> fieldConfigs=BeanCopy.getInstance().convertList(excelImportRpcService.getAllCmdImportFieldConfigDtoByConfigId(impotConfig.getId()),ImportFieldConfig.class);
		Map<String, List<IValidate>> validateMap=new HashMap<String, List<IValidate>>();
		for(ImportFieldConfig fieldConfig: fieldConfigs){
			validateMap.put(fieldConfig.getCode(), ValidateFactory.getRuleValidates(fieldConfig, params));
		}
		StringBuffer messageBuffer=new StringBuffer();
		List<Map<String,Object>> mapValues=new ArrayList<Map<String,Object>>();
		Workbook workbook=null;
		if(filePath.endsWith(".xls")){
			workbook=new HSSFWorkbook(RemoteFileUtils.getRemoteFileAsFileInputScreem(filePath));
		}else{
			workbook=new XSSFWorkbook(RemoteFileUtils.getRemoteFileAsFileInputScreem(filePath));
		}
		
		Sheet sheet=workbook.getSheetAt(impotConfig.getSheet());
		 FormulaEvaluator evaluator=workbook.getCreationHelper().createFormulaEvaluator();
		
		 //验证模板的正确性 根据模板的一行一列的数值和参数中定义的数值验证
		if(sheet.getRow(0)!=null&&sheet.getRow(0).getCell(0)!=null&&sheet.getRow(0).getCell(0).getRichStringCellValue()!=null&&busiCode.equals(sheet.getRow(0).getCell(0).getRichStringCellValue().toString())){
			//模板正确继续验证
			for(int rowIndex=impotConfig.getStartRow();rowIndex<=sheet.getLastRowNum();rowIndex++){
				 Row row=sheet.getRow(rowIndex);
				 Map<String,Object> mapValue=new HashMap<String, Object>();
				 for(ImportFieldConfig fieldConfig: fieldConfigs){
						int colIndex=impotConfig.getStartCol()+fieldConfig.getCol();
						Cell cell=row.getCell(colIndex);
						Object value=ExcelUtils.getCellStringGenerolValue(cell,evaluator);
						
						mapValue.put(fieldConfig.getCode(), value);
				}
				 mapValues.add(mapValue);
			 }
			
			 for(int i=0;i<mapValues.size();i++){
				 int rowIndex=impotConfig.getStartRow()+i;
				 Map<String,Object> mapValue=mapValues.get(i);
				 for(ImportFieldConfig fieldConfig: fieldConfigs){
						int colIndex=impotConfig.getStartCol()+fieldConfig.getCol();
						Object value=mapValue.get(fieldConfig.getCode());
						for(IValidate validate : validateMap.get(fieldConfig.getCode())){
							ValidateResult result=validate.check(value, fieldConfig.getCode(),rowIndex,mapValue,mapValues);
							if(!result.isOk()){
								messageBuffer.append("\n第").append(rowIndex+1).append("行").append(colIndex).append("列验证不通过").append(result.getMessage());
							}
						}
				}
			 }
			 
		}else{
			messageBuffer.append("选中的导入模板不正确,请确认后重新上传数据导入");
		}
		 if(messageBuffer.length()==0){
			 IValidateService validateService=ValidateFactory.getTotalValidateService(impotConfig);
			 if(validateService!=null){
				 ValidateResult result=validateService.checkTotal(mapValues, impotConfig, fieldConfigs, params, workbook);
				 if(!result.isOk()){
					 try{
						 excelImportRpcService.updateImportStatus(impId, ImportConstant.ImportStatus.VALIDATE_FAIL, "验证失败"+result.getMessage());
					 }catch(Exception e){
						 throw new BusinessRuntimeException("保存导入数据记录出错了", "-1");
					 }
						 return result.getMessage();
					 }
			 }
			 try{
				 excelImportRpcService.updateImportStatus(impId, ImportConstant.ImportStatus.VALIDATE_SUCCESS, "验证成功");
			 }catch(Exception e){
				throw new BusinessRuntimeException("保存导入数据记录出错了", "-1");
			 }
			 return null;
		 }else{
			 String message=messageBuffer.toString();
			 try{
				 excelImportRpcService.updateImportStatus(impId, ImportConstant.ImportStatus.VALIDATE_FAIL, "验证失败"+message);
			 }catch(Exception e){
				 throw new BusinessRuntimeException("保存导入数据记录出错了", "-1");
			 }
			 return message;
		 }
	}
	

}
