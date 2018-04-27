package com.huatek.file.web;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huatek.file.service.CmdFileService;
import com.huatek.file.util.GsonUtil;

/**
 *  获取文件列表
  * @ClassName: GetFilesAction
  * @FullClassPath: com.huatek.file.web.GetFilesAction
  * @Description: TODO
  * @author: Ivan
  * @date: 2016年6月28日 上午11:07:35
  * @version: 1.0
 */
@Controller
@RequestMapping("/deleteFile.do")
public class DeleteFileAction {
	@Autowired
	private CmdFileService cmdFileService;
	@RequestMapping(params = "actionMethod=deleteFile")
	public void deleteFile( HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, Exception{
		//访问文件的前缀地址
		Map<String,Object> result=new HashMap<String,Object>();
		String fileId = request.getParameter("fileId");//获取文件的id
		if(fileId!=null && !"".equals(fileId)){
			//CmdFileDto file=cmdFileService.getCmdFileDtoById(Long.parseLong(fileId));
			 cmdFileService.deleteCmdFile(Long.valueOf(fileId));

		}else{
			result.put("success", false);
			result.put("message", "文件不存在");
		}
		String json = GsonUtil.gson.toJson(result);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
	
	}

}
