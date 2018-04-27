package com.huatek.file.web;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.attribute.IfBlankAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huatek.file.dto.CmdFileDto;
import com.huatek.file.service.CmdFileService;
import com.huatek.file.util.GsonUtil;
import com.huatek.frame.core.util.PropertyUtil;

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
@RequestMapping("/getFiles.do")
public class GetFilesAction {	
	@Autowired
	private CmdFileService cmdFileService;
	
	@RequestMapping(params = "actionMethod=getFiles")
	public void getFiles( HttpServletRequest request,HttpServletResponse response) throws IOException{
		//浏览文件的前缀地址
		String viewUrl = PropertyUtil.getConfigValue("viewUrl");
		//预览文件的前缀地址
		String previewUrl = PropertyUtil.getConfigValue("previewUrl");
		//下载文件的前缀地址
		String downloadUrl = PropertyUtil.getConfigValue("downloadUrl");
		
		//访问文件的前缀地址
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		String businessIds = request.getParameter("businessIds");//获取文件的id
		List<Map<String, Object>> data=new  ArrayList<Map<String, Object>>();
		if(StringUtils.isNoneBlank(businessIds)){
			//CmdFileDto file=cmdFileService.getCmdFileDtoById(Long.parseLong(fileId));
			List <CmdFileDto> fileList= cmdFileService.getCmdFileDtoByBusiIds(businessIds.split(","));
			if(fileList!=null  && fileList.size()>0){
				for (int i=0;i<fileList.size();i++){
					CmdFileDto file= fileList.get(i);
					Map<String, Object> fileResult=new HashMap<String, Object>();
					fileResult.put("id", file.getId());
					fileResult.put("name", file.getFileName());
					fileResult.put("viewUrl", viewUrl+"?id="+file.getId());
					fileResult.put("size", file.getFileSize());
					fileResult.put("fileType", file.getSuffixName());
					fileResult.put("preViewUrl", file.getCompressPath()!=null?previewUrl+"?id="+file.getId():null);
					fileResult.put("downloadUrl",downloadUrl+"?id="+file.getId());
					data.add(fileResult);
				}
				result.put("data", data);
	
			}else{
				result.put("success", false);
				result.put("message", "记录不存在");
			}
		}else{
			result.put("success", false);
			result.put("message", "文件businessId不能为空");
		}
		String json = GsonUtil.gson.toJson(result);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
	
	}

}
