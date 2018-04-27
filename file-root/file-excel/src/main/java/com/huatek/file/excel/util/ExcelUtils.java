package com.huatek.file.excel.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.huatek.cmd.dto.CmdExportConfigDto;
import com.huatek.cmd.dto.CmdExportFieldConfigDto;

public class ExcelUtils {
    public static Object getCellValue(Cell cell) { 
    	Object value=null;
    	if((cell.getCellType()==Cell.CELL_TYPE_FORMULA&&cell.getCachedFormulaResultType()==Cell.CELL_TYPE_BOOLEAN)||cell.getCellType()==Cell.CELL_TYPE_BOOLEAN){
			value=cell.getBooleanCellValue();
		}else if((cell.getCellType()==Cell.CELL_TYPE_FORMULA&&cell.getCachedFormulaResultType()==Cell.CELL_TYPE_NUMERIC)||cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
			value=cell.getNumericCellValue();
			if(DateUtil.isCellDateFormatted(cell)){
				value=DateUtil.getJavaDate(cell.getNumericCellValue());
			}
			
		}else if((cell.getCellType()==Cell.CELL_TYPE_FORMULA&&cell.getCachedFormulaResultType()==Cell.CELL_TYPE_STRING)||cell.getCellType()==Cell.CELL_TYPE_STRING){
			value=cell.getStringCellValue();
		}
    	return value;
    }  
    
    
    
    public static String getCellStringGenerolValue( Cell cell,FormulaEvaluator evaluator){
    	SimpleDateFormat   df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	//判断是否为null或空串
        if (cell==null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        int cellType=cell.getCellType();
        if(cellType==Cell.CELL_TYPE_FORMULA){ //表达式类型
            cellType=evaluator.evaluate(cell).getCellType();;
        }
         
        switch (cellType) {
        case Cell.CELL_TYPE_STRING: //字符串类型
            cellValue= cell.getStringCellValue().trim();
            cellValue=StringUtils.isEmpty(cellValue) ? "" : cellValue; 
            break;
        case Cell.CELL_TYPE_BOOLEAN:  //布尔类型
            cellValue = String.valueOf(cell.getBooleanCellValue()); 
            break; 
        case Cell.CELL_TYPE_NUMERIC: //数值类型
             if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
                 cellValue =    df.format(cell.getDateCellValue());
             } else {  //否
                 cellValue = String.valueOf(cell.getNumericCellValue()); 
             } 
            break;
        default: //其它类型，取空串吧
            cellValue = "";
            break;
        }
        return cellValue;
	}
    
    /**
	 * 创建excel
	 * @param results
	 * @param fieldConfigs
	 * @return
	 */
	public static Workbook getExcel(CmdExportConfigDto cmdExportConfigDto,List<Object[]> results,List<CmdExportFieldConfigDto> fieldConfigs, Map<String, Object> params,Workbook templateWorkBook) {
		Workbook hwb =null; 
		Sheet hs =null; 
		if(templateWorkBook!=null){
			hwb=templateWorkBook;
			hs=hwb.getSheetAt(0);
		}else{
			hwb=new XSSFWorkbook();
			hs=hwb.createSheet("sheet");
		}
		int rowIndex = cmdExportConfigDto.getRow();
		Row row = null;
		
//		if(params != null && params.get("importBusiCode") != null){
//			row = hs.createRow(rowIndex++);
//			row.createCell((short) 0).setCellValue(params.get("importBusiCode").toString());
//			row = hs.createRow(rowIndex++);
//			row.createCell((short) 0).setCellValue(null == params.get("importBusiName")? null : params.get("importBusiName").toString());
//		}
		
		//表头
		int n = 0;
		row = hs.createRow(rowIndex++);
		for(int i=0;i<fieldConfigs.size();i++){
			CmdExportFieldConfigDto exportFieldConfig = fieldConfigs.get(i);
			if(exportFieldConfig.getIsExport().equals("1")){//需要导出
				row.createCell((short) n).setCellValue(exportFieldConfig.getFieldDesc());
				n++;
			}
		}
		//表数据
		for (int i = 0; null!=results && i < results.size(); i++,rowIndex++) {
			
			row = hs.getRow(rowIndex);
			if(row==null){
				row=hs.createRow(rowIndex);
			}
			if (cmdExportConfigDto.getNumCol()!=null&&cmdExportConfigDto.getNumCol()>=0) {
				Cell cell=row.getCell(cmdExportConfigDto.getNumCol());
				if(cell==null){
					cell=row.createCell(cmdExportConfigDto.getNumCol());
				}
				cell.setCellValue(i+1);
			}
			
			Object[] entity = results.get(i);
			for (int k=0;k<entity.length;k++) {
				CmdExportFieldConfigDto fieldConfig=fieldConfigs.get(k);
				Cell cell=row.getCell(fieldConfig.getOrderBy());
				if(cell==null){
					cell=row.createCell(fieldConfig.getOrderBy());
				}
				cell.setCellValue(null!=entity[k]?entity[k].toString():"");
			}
			
		}
		return hwb;
	}
	
	public static void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
        final String userAgent = request.getHeader("USER-AGENT");
        try {
            String finalFileName = null;
            if(StringUtils.contains(userAgent, "MSIE")){//IE浏览器
                finalFileName = URLEncoder.encode(fileName,"UTF-8");
            }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
                finalFileName = new String(fileName.getBytes("GBK"), "ISO8859-1"); 
            }else{
                finalFileName = URLEncoder.encode(fileName,"UTF-8");//其他浏览器
            }
            response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFileName + ".xlsx\"");//这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开
        } catch (UnsupportedEncodingException e) {
        }
    }
}
