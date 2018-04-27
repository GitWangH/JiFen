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
import com.huatek.file.excel.imp.persist.IPersist;
import com.huatek.file.excel.imp.persist.PersistFactory;
import com.huatek.file.excel.imp.transform.ITransform;
import com.huatek.file.excel.imp.transform.ITransformService;
import com.huatek.file.excel.imp.transform.TransformFactory;
import com.huatek.file.excel.util.ExcelUtils;
import com.huatek.file.excel.util.RemoteFileUtils;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.session.data.UserInfo;

public class PersistTask implements  Callable<String> {
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
		try{
			ImportConfig impotConfig=BeanCopy.getInstance().convert(excelImportRpcService.getCmdImportConfigByBusiCode(busiCode),ImportConfig.class);
			List<ImportFieldConfig> fieldConfigs=BeanCopy.getInstance().convertList(excelImportRpcService.getAllCmdImportFieldConfigDtoByConfigId(impotConfig.getId()),ImportFieldConfig.class);
			Map<String, ITransform> transformMap=new HashMap<String,ITransform>();
			for(ImportFieldConfig fieldConfig: fieldConfigs){
				transformMap.put(fieldConfig.getCode(), TransformFactory.getTransform(fieldConfig, params));
			}
			List<Map<String,Object>> mapValues=new ArrayList<Map<String,Object>>();
			Workbook workbook=null;
			if(filePath.endsWith(".xls")){
				workbook=new HSSFWorkbook(RemoteFileUtils.getRemoteFileAsFileInputScreem(filePath));
			}else{
				workbook=new XSSFWorkbook(RemoteFileUtils.getRemoteFileAsFileInputScreem(filePath));
			}
			 FormulaEvaluator evaluator=workbook.getCreationHelper().createFormulaEvaluator();
			 Sheet sheet=workbook.getSheetAt(impotConfig.getSheet());
			 for(int rowIndex=impotConfig.getStartRow();rowIndex<=sheet.getLastRowNum();rowIndex++){
				 Row row=sheet.getRow(rowIndex);
				 Map<String,Object> mapValue=new HashMap<String, Object>();
				 for(ImportFieldConfig fieldConfig: fieldConfigs){
						int colIndex=impotConfig.getStartCol()+fieldConfig.getCol();
						Cell cell=row.getCell(colIndex);
						Object value=ExcelUtils.getCellStringGenerolValue(cell,evaluator);
						ITransform transform=transformMap.get(fieldConfig.getCode());
						if(transform!=null){
							value=transform.transform(value, fieldConfig.getCode());
						}
						mapValue.put(fieldConfig.getCode(), value);
				}
				 mapValues.add(mapValue);
			 }
			 ITransformService transformService=TransformFactory.getTransformService(impotConfig);
			 if(transformService!=null){
				 mapValues=transformService.transform(mapValues, impotConfig, fieldConfigs, params, workbook);
			 }
			 IPersist persist=PersistFactory.getPersist(impotConfig,fieldConfigs, params);
			 persist.persist(mapValues, busiCode,user, workbook);
			 
			 excelImportRpcService.updateImportStatus(impId, ImportConstant.ImportStatus.PERSIST_SUCCESS, "导入成功！");
			
			 //删除上传的临时文件
			 //RemoteFileUtils.deleteRemoteFile(filePath);
		}catch(Exception e){
			e.printStackTrace();
			excelImportRpcService.updateImportStatus(impId, ImportConstant.ImportStatus.PERSIST_FAIL, "导入失败："+e.getMessage());
		}
		 return null;
	}

}
