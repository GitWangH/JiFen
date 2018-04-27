package com.huatek.file.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huatek.cmd.dto.CmdExportConfigDto;
import com.huatek.cmd.dto.CmdExportFieldConfigDto;
import com.huatek.cmd.dto.CmdImportConfigDto;
import com.huatek.cmd.service.CmdExportRpcService;
import com.huatek.cmd.service.ExcelImportRpcService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.file.excel.service.ExcelService;
import com.huatek.file.excel.util.ExcelUtils;
import com.huatek.file.excel.util.RemoteFileUtils;
import com.huatek.file.util.GsonUtil;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.rpc.server.core.SystemType;

@Controller
@RequestMapping("/excel.do")
public class ExcelAction {
	@Autowired
	private RpcProxy rpcProxy;

	@RequestMapping(params = "actionMethod=export", method = RequestMethod.POST)
	public void exp(HttpServletRequest request, HttpServletResponse response)throws Exception {
		response.setCharacterEncoding("UTF-8");
		Map<String,Object> values =new HashMap<String, Object>();
		
		String queryData = request.getParameter("queryData");
		Gson gson = new Gson();
		values = gson.fromJson(queryData, new TypeToken<Map<String, Object>>(){}.getType());
		String busiCode = values.get("busiCode").toString();
		CmdExportRpcService cmdExportRpcService=rpcProxy.create(CmdExportRpcService.class);
		//根据编码获取导出配置信息
		CmdExportConfigDto cmdExportConfigDto = cmdExportRpcService.getCmdExportConfigByBusiCode(busiCode);
		//根据导出配置id获取导出字段信息
		List<CmdExportFieldConfigDto> exportFieldList = cmdExportRpcService.getAllCmdExportFieldConfigDtoByConfigId(cmdExportConfigDto.getId());
		
		ExcelService excelService = (ExcelService) rpcProxy.create(ExcelService.class,SystemType.valueOf(cmdExportConfigDto.getSystemType()),30L,TimeUnit.MINUTES);
		
		//得到生成Excel的file文件路径
		String filePath = excelService.exportExcel(cmdExportConfigDto,exportFieldList,values);
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("filepath", filePath);
		String json = GsonUtil.gson.toJson(map);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(json);
	}
	
	@RequestMapping(params = "actionMethod=dowExp", method = RequestMethod.GET)
	public void dowExp(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		response.setCharacterEncoding("UTF-8");
		String busiCode = request.getParameter("busiCode");
		String filePath = request.getParameter("filePath");
		
		CmdExportRpcService cmdExportRpcService=rpcProxy.create(CmdExportRpcService.class);
		//根据编码获取导出配置信息
		CmdExportConfigDto cmdExportConfigDto = cmdExportRpcService.getCmdExportConfigByBusiCode(busiCode);
		
		String fileName = cmdExportConfigDto.getReportName();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -10);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		ExcelUtils.setFileDownloadHeader(request, response, fileName);
		
		OutputStream ouputStream = null;
		InputStream inputStream = null;
		try {
			ouputStream = response.getOutputStream();
			inputStream = RemoteFileUtils.getRemoteFileAsFileInputScreem(filePath);
			byte[] b = new byte[1024];
			int l = -1;
			while ((l = inputStream.read(b)) > 0) {
				ouputStream.write(b, 0, l);
			}
			ouputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(inputStream!=null){
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if(ouputStream!=null){
					ouputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			RemoteFileUtils.deleteRemoteFile(filePath);
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "actionMethod=import", method = RequestMethod.POST)
	public void imp(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		Map<String, String> params =new HashMap<String, String>();
		for(Map.Entry entry :(Set<Map.Entry>)(multipartRequest.getParameterMap().entrySet())){
			String[] values=multipartRequest.getParameterValues(entry.getKey().toString());
			if(values!=null&&values.length>0){
				params.put(entry.getKey().toString(), values[0]);
			}
			
		}
		String busiCode = (String) params.get("busiCode");
		ExcelImportRpcService excelImportRpcService=rpcProxy.create(ExcelImportRpcService.class);
		CmdImportConfigDto configDto=excelImportRpcService.getCmdImportConfigByBusiCode(busiCode);
		// 导入和上传文件的本地路径
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> files = new ArrayList<Map<String, Object>>();
		Iterator<String> it = multipartRequest.getFileNames();
		while (it.hasNext()) {
			String key = it.next();
			Map<String, Object> fileMap = new HashMap<String, Object>();
			MultipartFile file = multipartRequest.getFile(key);
			if (file.getOriginalFilename().length() > 0) {
				String fileName = file.getOriginalFilename();
				fileMap.put("name", fileName);
				fileMap.put("size", file.getSize());

				// 后缀名
				String extFileName = fileName.substring(fileName
						.lastIndexOf(".") + 1);
				try {
					String filepath = RemoteFileUtils.saveFileFromInputStream(
							file.getInputStream(), fileName);
					fileMap.put("filepath", filepath);
					// 执行导入
					ExcelService excelService = (ExcelService) rpcProxy
							.create(ExcelService.class,SystemType.valueOf(configDto.getBusiType()), 30L, TimeUnit.MINUTES);

					UserInfo user = ThreadLocalClient.get().getOperator();
					Map<String,String> message = excelService.importExcel(filepath,busiCode, params,user);

					fileMap.putAll(message);

				} catch (Exception e) {
					e.printStackTrace();
					fileMap.put("error", "导入失败");
					fileMap.put("message", e.getMessage());
				}
			} else {
				fileMap.put("error", "没有上传文件");
			}
			files.add(fileMap);
		}
		result.put("files", files.toArray());
		String json = GsonUtil.gson.toJson(result);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(json);

	}

	@RequestMapping(params = "actionMethod=download", method = RequestMethod.GET)
	public void download(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		response.setCharacterEncoding("UTF-8");
		String fileName = request.getParameter("filename");
		String busiCode = request.getParameter("busiCode");
		ExcelImportRpcService excelImportRpcService=rpcProxy.create(ExcelImportRpcService.class);
		CmdImportConfigDto configDto=excelImportRpcService.getCmdImportConfigByBusiCode(busiCode);
		String extFileName =configDto.getTemplateFileName();
		
		if(fileName!=null){
			extFileName=extFileName.substring(extFileName.indexOf(".")+1);
		}
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -10);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename="+fileName+"."+extFileName);

		OutputStream ouputStream = null;
		
		try {
			ouputStream = response.getOutputStream();
			ouputStream.write(excelImportRpcService.getTemplateData(busiCode));
			ouputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			

			try {
				if(ouputStream!=null){
					ouputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@RequestMapping(params = "actionMethod=importTemplate", method = RequestMethod.POST)
	public void uploadImportTemplate(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		Map<String, String> params =new HashMap<String, String>();
		for(Map.Entry entry :(Set<Map.Entry>)(multipartRequest.getParameterMap().entrySet())){
			String[] values=multipartRequest.getParameterValues(entry.getKey().toString());
			if(values!=null&&values.length>0){
				params.put(entry.getKey().toString(), values[0]);
			}
		}
		String busiCode = (String) params.get("busiCode");
		// 导入和上传文件的本地路径
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> files = new ArrayList<Map<String, Object>>();
		Iterator<String> it = multipartRequest.getFileNames();
		while (it.hasNext()) {
			String key = it.next();
			Map<String, Object> fileMap = new HashMap<String, Object>();
			MultipartFile file = multipartRequest.getFile(key);
			fileMap.put("name", file.getOriginalFilename());
			fileMap.put("size", file.getSize());
			fileMap.put("filepath", file.getOriginalFilename());
			if (file.getOriginalFilename().length() > 0) {
				String fileName = file.getOriginalFilename();
				// 后缀名
				String extFileName = fileName.substring(fileName.lastIndexOf(".") + 1);
				try {
					ExcelImportRpcService excelImportRpcService=rpcProxy.create(ExcelImportRpcService.class);
					excelImportRpcService.saveTemplateData(busiCode,fileName,file.getBytes());
					fileMap.put("message","上传成功！");
				} catch (Exception e) {
					e.printStackTrace();
					fileMap.put("error", "上传失败");
					fileMap.put("message", e.getMessage());
				}
			} else {
				fileMap.put("error", "上传文件为空");
			}
			files.add(fileMap);
		}
		result.put("files", files.toArray());
		String json = GsonUtil.gson.toJson(result);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(json);
	}
	@RequestMapping(params = "actionMethod=exportTemplate", method = RequestMethod.POST)
	public void uploadExportTemplate(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		Map<String, String> params =new HashMap<String, String>();
		for(Map.Entry entry :(Set<Map.Entry>)(multipartRequest.getParameterMap().entrySet())){
			String[] values=multipartRequest.getParameterValues(entry.getKey().toString());
			if(values!=null&&values.length>0){
				params.put(entry.getKey().toString(), values[0]);
			}
		}
		String busiCode = (String) params.get("busiCode");
		// 导入和上传文件的本地路径
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> files = new ArrayList<Map<String, Object>>();
		Iterator<String> it = multipartRequest.getFileNames();
		while (it.hasNext()) {
			String key = it.next();
			Map<String, Object> fileMap = new HashMap<String, Object>();
			MultipartFile file = multipartRequest.getFile(key);
			fileMap.put("name", file.getOriginalFilename());
			fileMap.put("size", file.getSize());
			fileMap.put("filepath", file.getOriginalFilename());
			if (file.getOriginalFilename().length() > 0) {
				String fileName = file.getOriginalFilename();
				// 后缀名
				String extFileName = fileName.substring(fileName
						.lastIndexOf(".") + 1);
				try {
					CmdExportRpcService cmdExportRpcService=rpcProxy.create(CmdExportRpcService.class);
					cmdExportRpcService.saveTemplateData(busiCode,fileName,file.getBytes());
					
				} catch (Exception e) {
					e.printStackTrace();
					fileMap.put("error", "上传失败");
					fileMap.put("message", e.getMessage());
				}
			} else {

				fileMap.put("error", "上传文件为空");
			}
			files.add(fileMap);
		}
		result.put("files", files.toArray());
		String json = GsonUtil.gson.toJson(result);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(json);
	}

//	@RequestMapping(params = "actionMethod=delete", method = RequestMethod.GET)
//	public void delete(HttpServletRequest request, HttpServletResponse response) {
//		String busiCode = request.getParameter("busiCode");
//		ExcelImportRpcService excelImportRpcService=rpcProxy.create(ExcelImportRpcService.class);
//		CmdImportConfigDto configDto=excelImportRpcService.getCmdImportConfigByBusiCode(busiCode);
//		RemoteFileUtils.deleteRemoteFile(configDto.getExcelPath());
//	}
}
