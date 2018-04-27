package com.huatek.file.web;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.huatek.file.service.CmdFileService;
import com.huatek.file.util.FileUtils;
import com.huatek.file.util.GsonUtil;
import com.huatek.frame.core.util.PropertyUtil;

/***
 * 发布报表
 *
 * @author winner pan
 *
 */
@Controller
@RequestMapping("/uploadReport.do")
public class UploadReportAction {
	
	@Autowired
	private CmdFileService cmdFileService;
	/***
	 * 完成文件的上传附件保存. 如果报错，则按照报错的内容执行
	 * @param docType 上传标志位:
	 * @param importServiceImpl 
	 * @param multipartRequest
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "actionMethod=process", method = RequestMethod.POST)
	public void createFile(final MultipartHttpServletRequest multipartRequest,final HttpServletResponse response) throws Exception {
		HttpServletResponse hResponse = (HttpServletResponse) response;
		hResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		//导入和上传文件的本地路径
		String uploadRoot = PropertyUtil.getConfigValue("reportPublishPath");
		//生成年月日的文件夹
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String dateStr = sdf.format(new Date());
		//访问文件的前缀地址
//		String visitPrefix = PropertyUtil.getConfigValue("visitPrefix");

//		String uploadFileDir = multipartRequest.getParameter("systemHeader");
//		String businessId=multipartRequest.getParameter("businessId")!=null?multipartRequest.getParameter("businessId"):"";//业务id对应业务表的主键
//		String busiType=multipartRequest.getParameter("busiType")!=null?multipartRequest.getParameter("busiType"):"";//业务类型id
		
		Map<String, Object> result = new HashMap<String, Object>();
//		if("".equals(uploadFileDir)|| "".equals(businessId)){
//			result.put("filePath", "");
//			result.put("extFileName", "");
//			result.put("fileName", "");
//			result.put("ifSuccess","false");
//			StringBuffer msg=new StringBuffer();
//			if("".equals(uploadFileDir)){
//				msg.append("上传目录不能为空");
//			}else if("".equals(businessId)){
//				msg.append("业务id不能为空");
//			}
//			result.put("msg",msg.toString());
//		}else{
			// 判断导入目录不存在，则创建导入目录.
//			File uploadFileUrl = new File(uploadRoot + "/" +uploadFileDir+"/"+ dateStr);
			File uploadFileUrl = new File(uploadRoot);
			if (!uploadFileUrl.exists()) {
				uploadFileUrl.mkdirs();
			}
			// 获取多个file
			for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = it.next();
				MultipartFile imgFile = multipartRequest.getFile(key);
				if (imgFile.getOriginalFilename().length() > 0) {
					String fileName = imgFile.getOriginalFilename();
					//后缀名
					String extFileName = fileName.substring(fileName.lastIndexOf(".") + 1);
					try {
//							CmdFileDto cmdFileDto=new CmdFileDto();//创建文件并且保存
							String uuidStr=UUID.randomUUID().toString();;
							//不带后缀名的路径
							String uploadFilePathWithoutExtName = uploadFileUrl + "/" + uuidStr;
							String uploadFilePath = uploadFilePathWithoutExtName + "."+extFileName;
							FileUtils.saveFileFromInputStream(imgFile.getInputStream(),new String(uploadFilePath.getBytes("UTF-8"),"UTF-8"));
							//返回文件路径
//							result.put("filePath", uploadFilePath);
//							result.put("extFileName", extFileName);
//							result.put("fileName", fileName);
//							result.put("ifSuccess","true");//visitPrefix
//							String saveVisitUrl="/"+uploadFileDir+"/"+ dateStr+"/"+uuidStr+"."+extFileName;
//							result.put("visitUrl", visitPrefix+saveVisitUrl);
//							hResponse.setStatus(HttpServletResponse.SC_OK); 
//							if(!"".equals(busiType)){
//								cmdFileDto.setBusinessType(busiType);
//							}
//							cmdFileDto.setViewUrl(saveVisitUrl);
//							cmdFileDto.setFileName(fileName);//文件名
//							cmdFileDto.setFilePath(uploadFilePath);//上传路径
//							cmdFileDto.setFileSize(imgFile!=null?imgFile.getSize()+"":0+"");//文件大小
//							cmdFileDto.setSuffixName(extFileName);//扩展名
//							cmdFileDto.setUploadTime(new Date());//时间
//							if(businessId!=null && !"".equals(businessId))//业务id
//								cmdFileDto.setBusinessId(businessId);
//							//cmdFileDto.setUploadUser("");
//							CmdFileDto saveFile=cmdFileService.saveCmdFileDto(cmdFileDto);
//							if(saveFile!=null){
//								result.put("fileId", saveFile.getId());
//							}
					} catch (Exception e) {
						e.printStackTrace();
						result.put("filePath", "");
						result.put("extFileName", "");
						result.put("fileName", "");
						result.put("ifSuccess","false");
						hResponse.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED); 
						hResponse.getWriter().print(e.getMessage());
					}
				}
			}
//		}


		String json = GsonUtil.gson.toJson(result);
		hResponse.getWriter().print(json);
	}
	
}
