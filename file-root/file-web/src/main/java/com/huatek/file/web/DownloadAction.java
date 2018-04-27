package com.huatek.file.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huatek.file.dto.CmdFileDto;
import com.huatek.file.service.CmdFileService;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.util.PropertyUtil;

/***
 * 实现文件下载的控制类.
 *
 * @author winner pan
 *
 */
@Controller
@RequestMapping("/download.do")
public class DownloadAction {	
	
	@Autowired
	private CmdFileService cmdFileService;
	/***
	 * 上传附件下载
	 * @param request
	 * @param response
	 * @param fileId
	 * @throws IOException
	 */
	@RequestMapping(params = "id", method = RequestMethod.GET)
	public void processUpload(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Long id = Long.valueOf(request.getParameter("id"));
		//上传附件本地保存路径
		String uploadRoot = PropertyUtil.getConfigValue("uploadPath");
		CmdFileDto cmdFileDto=cmdFileService.getCmdFileDtoById(id);
		File file = new File(uploadRoot+"/"+cmdFileDto.getFilePath());
		if(!file.exists()){
			response.sendError(404, "文件不存在");
			return;
		}
		String showFileName=new String(cmdFileDto.getFileName().getBytes("GBK"),"ISO-8859-1");
	    response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -10);
		response.addHeader("Content-Disposition","attachment;filename=" + showFileName);
		InputStream inputStream = null;
		OutputStream os = null;
		try {
		    inputStream = new FileInputStream(file);
		    os = response.getOutputStream();
		    byte[] b=new byte[1024];
		    int length;
		    while((length=inputStream.read(b))>0){
		        os.write(b,0,length);
		    }
		    os.flush();
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
			if(os!=null){
				os.close();
			}
			if(inputStream!=null){
				inputStream.close();
			}
		}
	}
	
	@RequestMapping(params = "ids", method = RequestMethod.GET)
	public void downloadMutil(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String[] ids = request.getParameter("ids").split("\\|");
		//上传附件本地保存路径
		String uploadRoot = PropertyUtil.getConfigValue("uploadPath");
		String showFileName = "";
		InputStream inputStream = null;
		OutputStream os = null;
		CmdFileDto cmdFileDto = null;
		if(ids.length == 1){
			cmdFileDto = cmdFileService.getCmdFileDtoById(Long.valueOf(ids[0]));
			showFileName = new String(cmdFileDto.getFileName().getBytes("GBK"),"ISO-8859-1");
		}else{
			showFileName = new String("多个文件.zip".getBytes("GBK"),"ISO-8859-1");
		}
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -10);
		response.addHeader("Content-Disposition","attachment;filename=" + showFileName);
		response.setContentType("application/octet-stream");
		response.flushBuffer();
			try {
				if(ids.length == 1){
					File file = new File(uploadRoot+cmdFileDto.getFilePath());
					if(!file.exists()){
						response.sendError(404, "文件不存在");
						return;
					}
				    inputStream = new FileInputStream(file);
				    os = response.getOutputStream();
				    byte[] b=new byte[1024];
				    int length;
				    while((length=inputStream.read(b))>0){
				        os.write(b,0,length);
				    }
				    os.flush();
				}else if(ids.length > 1){
					ZipOutputStream zos=new ZipOutputStream(response.getOutputStream());
					List<String> fileNames = new ArrayList<String>();
					//	重复文件数
					Integer fileCount = 0;
					for(String id : ids){
						if(StringUtils.isNotBlank(id)){
							CmdFileDto fileDto = cmdFileService.getCmdFileDtoById(Long.valueOf(id));
							String fileName = fileDto.getFileName();
							//	如果下载文件中包含同名文件则在文件名后追加数字
							if(fileNames.contains(fileDto.getFileName())){
								fileName = fileName.split("\\.")[0] +"("+String.valueOf(++fileCount)+")."+fileName.split("\\.")[1];
							}
							String path=uploadRoot+fileDto.getFilePath();
							inputStream = new FileInputStream(path);
							ZipEntry entry=new ZipEntry(fileName);
							zos.putNextEntry(entry);
							byte[] b=new byte[1024];
							int length;
							while((length=inputStream.read(b))>0){
								zos.write(b,0,length);
							}
							zos.closeEntry();
							fileNames.add(fileName);
						}
					}
					zos.flush();
					zos.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessRuntimeException("文件下载出错!", "-1");
			} finally {
				if(os!=null){
					os.close();
				}
				if(inputStream!=null){
					inputStream.close();
				}
			}
	}

	
}
