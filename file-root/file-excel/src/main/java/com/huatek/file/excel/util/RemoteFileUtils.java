package com.huatek.file.excel.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.huatek.frame.core.util.PropertyUtil;


public class RemoteFileUtils {
	public final static String basepath=PropertyUtil.getConfigValue("excel_file_basepath");
	
	private static final int FILE_OPERATE_BUFFER_SIZE = 1048576;
	public static InputStream getRemoteFileAsFileInputScreem(String filePath) throws FileNotFoundException{
		return new FileInputStream(basepath+"/"+filePath);
	}
	public static void deleteRemoteFile(String filePath) {
		File file = new File(basepath + "/" + filePath);
		if(file.exists()){
			file.delete();
		}
	}
	public static String  saveFileFromInputStream(final InputStream stream,String fileName) throws IOException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(new Date());
		
		File importFileUrl = new File(basepath + "/" + dateStr);
		if (!importFileUrl.exists()) {
			importFileUrl.mkdirs();
		}
		String filePath=dateStr+"/"+UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
		File file = new File(basepath + "/" +filePath);
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(file);
			byte[] buffer = new byte[FILE_OPERATE_BUFFER_SIZE];
			int byteread = 0;
			while ((byteread = stream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
		} finally {
			if (fs != null) {
				fs.close();
			}
			if (stream != null) {
				stream.close();
			}
		}
		return filePath;
	}
	/**
	 * 把生成的excel文件保存到服务器中
	 * */
	public static String  saveExcelFile(final Workbook wb) throws IOException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(new Date());
		
		File importFileUrl = new File(basepath + "/" + dateStr);
		if (!importFileUrl.exists()) {
			importFileUrl.mkdirs();
		}
		String filePath="";
		if(wb instanceof HSSFWorkbook){
			filePath=dateStr+"/"+UUID.randomUUID().toString()+".xls";
		}else{
			filePath=dateStr+"/"+UUID.randomUUID().toString()+".xlsx";
		}
		File file = new File(basepath + "/" + filePath);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();  
		wb.write(os);  
        byte[] xls = os.toByteArray();  
          
        OutputStream out = null;  
        try {  
            out = new FileOutputStream(file);  
            out.write(xls);
 		} finally {
 			if (out != null) {
 				out.close();
 			}
 		}
        
        return filePath;
	}
	
}
