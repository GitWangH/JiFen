package com.huatek.file.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huatek.file.dto.CmdFileDto;
import com.huatek.file.service.CmdFileService;
import com.huatek.file.util.GsonUtil;
import com.huatek.frame.core.util.PropertyUtil;

/**
 * 
 * @Title: <P>GetFileUrlAction.java</P>
 * @Package <P>com.huatek.file.web</P>
 * @Description: <P>获取文件的url</P>
 * @author hobart
 * @date 2016年5月13日 下午5:10:04
 * @version 1.0
 * @functionModule 
 * ---------------------------------------------------------
 * 修改历史 
 * 序号    版本号          修改日期    修改人           修改原因 
 *  1
 *  2
 */
@Controller
@RequestMapping("/getFileUrl.do")
public class GetFileUrlAction {	
	@Autowired
	private CmdFileService cmdFileService;
	@RequestMapping(params = "actionMethod=getUrl", method = RequestMethod.POST)
	public void processUpload( HttpServletRequest request,HttpServletResponse response) throws IOException{
		//浏览文件的前缀地址
		String viewUrl = PropertyUtil.getConfigValue("viewUrl");
		//预览文件的前缀地址
		String previewUrl = PropertyUtil.getConfigValue("previewUrl");
		//下载文件的前缀地址
		String downloadUrl = PropertyUtil.getConfigValue("downloadUrl");
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		String fileId = request.getParameter("fileId");//获取文件的id
		List<Map<String, Object>> data=new  ArrayList<Map<String, Object>>();
		if(fileId!=null && !"".equals(fileId)){
			//CmdFileDto file=cmdFileService.getCmdFileDtoById(Long.parseLong(fileId));
			List <CmdFileDto> fileList= cmdFileService.getCmdFileDtoByBusiId(fileId);
			if(fileList!=null  && fileList.size()>0){
				for (int i=0;i<fileList.size();i++){
					CmdFileDto file= fileList.get(i);
					Map<String, Object> fileResult=new HashMap<String, Object>();
					fileResult.put("viewUrl", viewUrl+"?id="+file.getId());
					fileResult.put("extFileName", file.getSuffixName());
					data.add(fileResult);
				}
				result.put("data", data);
	
			}else{
				result.put("success", false);
				result.put("message", "记录不存在");
			}
		}else{
			result.put("success", false);
			result.put("message", "文件id不能为空");
		}
		String json = GsonUtil.gson.toJson(result);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
	
	}

}
