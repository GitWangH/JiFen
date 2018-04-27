package com.huatek.file.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.huatek.file.dto.CmdFileCatalogDto;
import com.huatek.file.dto.CmdFileDto;
import com.huatek.file.service.CmdFileCatalogService;
import com.huatek.file.service.CmdFileService;
import com.huatek.file.util.FileConverter;
import com.huatek.file.util.FileUtils;
import com.huatek.file.util.GsonUtil;
import com.huatek.file.util.ImgCompress;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.util.PropertyUtil;

/***
 * 负责文档上传的控制类.
 *
 * @author winner pan
 *
 */
@Controller
@RequestMapping("/upload.do")
public class UploadAction {
	
	@Autowired
	private CmdFileService cmdFileService;
	
	@Autowired
	private CmdFileCatalogService cmdFileCatalogService;
	
	/***
	 * 完成文件的上传附件保存. 如果报错，则按照报错的内容执行
	 * @param docType 上传标志位:
	 * 							导入时为1，单附件上传时为2，多附件上传时为3.
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
		String uploadRoot = PropertyUtil.getConfigValue("uploadPath");
		//生成年月日的文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(new Date());
		//浏览文件的前缀地址
		String viewUrl = PropertyUtil.getConfigValue("viewUrl");
		//预览文件的前缀地址
		String previewUrl = PropertyUtil.getConfigValue("previewUrl");
		//下载文件的前缀地址
		String downloadUrl = PropertyUtil.getConfigValue("downloadUrl");

		String uploadFileDir = multipartRequest.getParameter("systemHeader");

		//	根据systemHeader查询file_path, 中文路径, 目录信息
		//	如果文件为word/ppt/excel等进行格式转换, 转换为pdf
//		Long tenantId = ThreadLocalClient.get().getOperator().getTenantId();
//		CmdFileCatalogDto catalog = cmdFileCatalogService.getFileCatalogByPath(tenantId, uploadFileDir);
		//	转换中文路径
//		String catalogPath = catalog.getPath();
//		List<String> catalogPaths = this.getAllPath(catalogPath);
		StringBuffer zhNamePath = new StringBuffer();
//		if(null != catalogPaths && !catalogPaths.isEmpty()){
//			for(String path : catalogPaths){
//				CmdFileCatalogDto catalogManager = cmdFileCatalogService.getFileCatalogByPath(tenantId, path);
//				if(null == catalogManager){
////					zhNamePath.append(catalogManager.getName());
//					catalogManager = cmdFileCatalogService.getFileCatalogByPath(null, path);
//				}
//				zhNamePath.append("/").append(catalogManager.getName());
//			}
//		}

		String businessId=multipartRequest.getParameter("businessId")!=null?multipartRequest.getParameter("businessId"):"";//业务id对应业务表的主键
		String busiType=multipartRequest.getParameter("busiType")!=null?multipartRequest.getParameter("busiType"):"";//业务类型id
		
		Map<String, Object> result = new HashMap<String, Object>();
		if("".equals(uploadFileDir)|| "".equals(businessId)){
			result.put("filePath", "");
			result.put("extFileName", "");
			result.put("fileName", "");
			result.put("ifSuccess","false");
			StringBuffer msg=new StringBuffer();
			if("".equals(uploadFileDir)){
				msg.append("上传目录不能为空");
			}else if("".equals(businessId)){
				msg.append("业务id不能为空");
			}
			result.put("msg",msg.toString());
		}else{
			// 判断导入目录不存在，则创建导入目录.
			File uploadFileUrl = new File(uploadRoot + "/" +uploadFileDir+"/"+ dateStr);
			if (!uploadFileUrl.exists()) {
				uploadFileUrl.mkdirs();
			}
			// 获取多个file
			for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = it.next();
				MultipartFile imgFile = multipartRequest.getFile(key);
				if (imgFile!=null&&imgFile.getOriginalFilename().length() > 0) {
					String fileName = imgFile.getOriginalFilename();
					//后缀名
					String extFileName = fileName.substring(fileName.lastIndexOf(".") + 1);
					try {
							CmdFileDto cmdFileDto=new CmdFileDto();//创建文件并且保存
							String uuidStr=UUID.randomUUID().toString();
							//不带后缀名的路径
							String uploadFilePathWithoutExtName = uploadFileUrl + "/" + uuidStr;
							String uploadFilePath = uploadFilePathWithoutExtName + "."+extFileName;
							File file = FileUtils.saveFileFromInputStream(imgFile.getInputStream(),new String(uploadFilePath.getBytes("UTF-8"),"UTF-8"));
							
							//返回文件路径
							result.put("filePath", "/"+uploadFileDir+"/"+ dateStr+"/"+uuidStr + "."+extFileName);
							result.put("fileType", extFileName);
							result.put("fileName", fileName);
							result.put("ifSuccess","true");//visitPrefix
							String saveVisitUrl = uploadFileDir+"/"+ dateStr+"/"+uuidStr+"."+extFileName;
							
							hResponse.setStatus(HttpServletResponse.SC_OK); 
							if(!"".equals(busiType)){
								cmdFileDto.setBusinessType(busiType);
							}
							cmdFileDto.setFileName(fileName);//文件名
							cmdFileDto.setFilePath(uploadFileDir+"/"+ dateStr+"/"+uuidStr + "."+extFileName);//上传路径
							cmdFileDto.setFileSize(imgFile!=null?imgFile.getSize()+"":0+"");//文件大小
							cmdFileDto.setSuffixName(extFileName);//扩展名
							//如果为图片文件，并且大于200K，则生成缩略图
							if(FileUtils.isImage(extFileName) && FileUtils.checkImageSize(imgFile)){
								  cmdFileDto.setViewPath(cmdFileDto.getFilePath());
								  ImgCompress imgCom = new ImgCompress(file);  
							      imgCom.resizeFix(800, 500,new String(uploadFilePathWithoutExtName + ".min.jpg"));  
							      cmdFileDto.setCompressPath(uploadFileDir+"/"+ dateStr+"/"+uuidStr + ".min.jpg");
							}else {
								cmdFileDto.setViewPath(saveVisitUrl);
							}
							/*if(FileUtils.isOffice(extFileName) ){
								FileConverter.converter(uploadFilePath, uploadFilePathWithoutExtName+ ".pdf");
								cmdFileDto.setViewPath(uploadFileDir+"/"+ dateStr+"/"+uuidStr + ".pdf");
							}*/
							cmdFileDto.setUploadTime(new Date());//时间
							if(businessId!=null && !"".equals(businessId))//业务id
								cmdFileDto.setBusinessId(businessId);
							//	目录信息
//							cmdFileDto.setCatalogId(catalog.getId());
							//	中文路径
							cmdFileDto.setFilePathCh(zhNamePath.toString());
							//	机构
							cmdFileDto.setOrgId(ThreadLocalClient.get().getOperator().getOrgId());
							//	租户ID
//							cmdFileDto.setTenantId(tenantId);
							//	上传人
							cmdFileDto.setUploadUser(ThreadLocalClient.get().getOperator().getUserName());
							/*//	预览地址
							if(!FileUtils.isOffice(extFileName)){
								cmdFileDto.setViewPath(saveVisitUrl);
							}*/
							CmdFileDto saveFile=cmdFileService.saveCmdFileDto(cmdFileDto);
							if(saveFile!=null){
								result.put("fileId", saveFile.getId());
								result.put("viewUrl", viewUrl+"?id="+saveFile.getId());
								result.put("previewUrl", previewUrl+"?id="+saveFile.getId());
								result.put("downloadUrl", downloadUrl+"?id="+saveFile.getId());
							}
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
		}


		String json = GsonUtil.gson.toJson(result);
		hResponse.getWriter().print(json);
	}
	
	/**
	 * 
	* @Title: getAllPath 
	* @Description: 根据path截取所有path 
	* @createDate: 2017年11月8日 下午8:47:46
	* @param   
	* @return  List<String> 
	* @author cloud_liu   
	* @throws
	 */
	private List<String> getAllPath(String path) {
		StringBuffer strTemp = new StringBuffer("/");
		List<String> strs = new ArrayList<String>();
		int i =0;
		for(String str : path.split("/")){
			if(i == 0){
				i++;
				continue;
			}
			strTemp.append("/").append(str);
			strs.add(strTemp.toString().substring(1));
			i++;
		}
		return strs;
	}
	
}
