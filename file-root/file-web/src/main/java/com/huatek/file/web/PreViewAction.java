package com.huatek.file.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huatek.file.dto.CmdFileDto;
import com.huatek.file.service.CmdFileService;
import com.huatek.frame.core.util.PropertyUtil;

/***
 * 实现文件下载的控制类.
 *
 * @author winner pan
 *
 */
@Controller
@RequestMapping("/preview.do")
public class PreViewAction {	
	
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
		File file = new File(uploadRoot+"/"+cmdFileDto.getCompressPath());
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
		response.flushBuffer();
	}
}