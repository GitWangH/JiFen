package com.huatek.busi.api.contract;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.constants.Constant;
import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.contract.BusiContractTendersBranchDetailDto;
import com.huatek.busi.dto.contract.BusiContractTendersBranchDto;
import com.huatek.busi.service.contract.BusiContractTendersBranchDetailService;
import com.huatek.busi.service.contract.BusiContractTendersBranchService;
import com.huatek.file.excel.util.RemoteFileUtils;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractTendersContractAction
 * @Description: 标段分部分项 后台控制器Action
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-28 14:00:35
 * @version: 1.0
 * @memuInfo [合同管理] - [项目合同] -[标段分部分项]
 * ------------------------------------------------------------------
 * 修改历史 
 * 序号       版本号          	    修改日期       			 修改人           	        修改原因 
 *  1	  1.0	2017-10-28 14:00:35	  mickey_meng	 Create New Class
 *  2
 *  ...
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_CONTRACT_TENDERS_BRANCH_API)
public class BusiContractTendersBranchAction {

    private static final Logger log = LoggerFactory.getLogger(BusiContractTendersBranchAction.class);

    @Autowired
    private OperationService oprationService;//日志记录服务类
    
    @Autowired
    private BusiContractTendersBranchService busiContractTendersBranchService;
    
    @Autowired
    private BusiContractTendersBranchDetailService busiContractTendersBranchDetailService;

    /**
     * 查询顶级节点列表数据
     * @param queryPage
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "/queryTopLevel")
    @ResponseBody
    public ResponseEntity<List<BusiContractTendersBranchDto>> _getAllTopLevelBusiContractTendersBranch(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	List<BusiContractTendersBranchDto> topLevelBusiContractTendersBranchList = busiContractTendersBranchService.getAllTopLevelBusiContractTendersBranchDto(queryPage);
        return new ResponseEntity<>(topLevelBusiContractTendersBranchList, HttpStatus.OK);
    }
    
    /**
     * 根据父级ID查询自己节点数据
     * @param parentUUID
     * @return
     */
    @RequestMapping(value = "/queryChildNodes/{parentUUID}")
    @ResponseBody
    public ResponseEntity<List<BusiContractTendersBranchDto>> _getChildBusiContractTendersBranchDtoByParentUUID(@PathVariable("parentUUID") String parentUUID) { 	
    	List<BusiContractTendersBranchDto> childBusiContractTendersBranchDtoList = busiContractTendersBranchService.getChildBusiContractTendersBranchDtoByParentUUID(parentUUID);
    	return new ResponseEntity<>(childBusiContractTendersBranchDtoList, HttpStatus.OK);
    }
    
    /**
     * 保存标段分部分项列表数据
     * @param orgId
     * @param busiContractOtherContractDetailDtoList
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveData/{orgId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<TreeGridAddIdAndUUIDDto>> saveTreeGridData(@PathVariable("orgId") Long orgId, @RequestBody List<BusiContractTendersBranchDto> busiContractTendersBranchDtoList) throws Exception {
    	oprationService.logOperation("保存标段分部分项列表数据");
        return new ResponseEntity<>(busiContractTendersBranchService.saveTreeGridData(orgId, busiContractTendersBranchDtoList), HttpStatus.CREATED);
    }
    
    /**
     * 查询翻页数据分部分项清单列表数据
     * @param queryPage
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */    
    @RequestMapping(value = "/queryDetail")
    @ResponseBody
    public ResponseEntity<DataPage<BusiContractTendersBranchDetailDto>> getAllBusiContractTendersBranchDetail(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	log.debug("get all BusiContractTendersBranchDetail of param " + queryPage.getQueryInfo());
    	DataPage<BusiContractTendersBranchDetailDto> busiContractTendersBranchDetailPages = busiContractTendersBranchDetailService.getAllBusiContractTendersBranchDetailPage(queryPage);
    	log.debug("get BusiContractTendersBranchDetail size @" + busiContractTendersBranchDetailPages.getContent().size());
    	return new ResponseEntity<>(busiContractTendersBranchDetailPages, HttpStatus.OK);
    }
    
    /**
     * 分部分项流程申请
     * @param orgId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/apply/{orgId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> applyBusiContractTendersBranch(@PathVariable("orgId") Long orgId) throws Exception {
    	busiContractTendersBranchService.applyBusiContractTendersBranchByOrgId(orgId);;//申请
		return new ResponseEntity<>(ResponseMessage.success("申请成功"), HttpStatus.OK);
    }
    
    /**
     * 根据机构ID查询审批状态 
     * @param orgId
     * @return
     */
    @RequestMapping(value = "/getTendersFlowInfo/{orgId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiContractTendersBranchDto> getTendersFlowInfo(@PathVariable("orgId") Long orgId) {
    	BusiContractTendersBranchDto busiContractTendersBranchDto = busiContractTendersBranchService.getBusiContractTendersBranchDtoByOrgId(orgId);
  	  if(null != busiContractTendersBranchDto){
  		  if(StringUtils.isNotEmpty(busiContractTendersBranchDto.getApprovalStatus())){
      		  if(busiContractTendersBranchDto.getApprovalStatus().equals(Constant.FLOW_STATUS_UNAPPROVED)){
      			busiContractTendersBranchDto.setApprovalStatusName("未审批");
          	  }else if(busiContractTendersBranchDto.getApprovalStatus().equals(Constant.FLOW_STATUS_INAPPROVAL)){
          		busiContractTendersBranchDto.setApprovalStatusName("审批中");
          	  }else if(busiContractTendersBranchDto.getApprovalStatus().equals(Constant.FLOW_STATUS_REBUT)){
          		busiContractTendersBranchDto.setApprovalStatusName("已驳回");
          	  }else if(busiContractTendersBranchDto.getApprovalStatus().equals(Constant.FLOW_STATUS_PASSED)){
          		busiContractTendersBranchDto.setApprovalStatusName("已通过");
          	  }  
      	  }else{
      		busiContractTendersBranchDto.setApprovalStatusName("未申请");
      	  } 
  	  }else{
  		  	busiContractTendersBranchDto = new BusiContractTendersBranchDto();
    		busiContractTendersBranchDto.setApprovalStatusName("未申请");
  	  } 
  	  return new ResponseEntity<>(busiContractTendersBranchDto, HttpStatus.OK);
    }
    
    /**
     * 生成分部分项[生成规则：根据机构编号、单位工程类型 获取工程标段管理明细数据和 基础数据-项目分部分项 相匹配的数据]
     * @param orgId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/createTendersBranch/{orgId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createTendersBranchData(@PathVariable("orgId") Long orgId) throws Exception {
    	busiContractTendersBranchService.createTendersBranchDataByOrgId(orgId);;//申请
		return new ResponseEntity<>(ResponseMessage.success("生成分部分项成功"), HttpStatus.OK);
    }
    
    /**
     * 查询已挂接分部分项列表数据
     * @param queryPage
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "/queryRelationBranchTreeList/{tendersContractDetailId}")
    @ResponseBody
    public ResponseEntity<List<BusiContractTendersBranchDto>> _getAllRelationBranchTreeDataList(@PathVariable("tendersContractDetailId") Long tendersContractDetailId) throws JsonParseException, JsonMappingException, IOException { 	
  	  List<BusiContractTendersBranchDto> relationBranchTreeDataList = busiContractTendersBranchService.getAllRelationBranchTreeDataListByContractDetailId(tendersContractDetailId);
  	  return new ResponseEntity<>(relationBranchTreeDataList, HttpStatus.OK);
    }
    
    /**
     * 分部分项导入
     * @param request
     * @param response
     * @param orgId
     * @return
     * @throws FileUploadException
     * @throws IOException
     */
    @RequestMapping(value = "/importData/{orgId}")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> importData(HttpServletRequest request, HttpServletResponse response,@PathVariable("orgId") Long orgId) throws FileUploadException, IOException  { 	
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	List<Map<String, Object>> files = new ArrayList<Map<String, Object>>();
//    	boolean isMultipart = ServletFileUpload.isMultipartContent(request);  //是否多文件上传
    	// Create a factory for disk-based file items  
    	DiskFileItemFactory factory = new DiskFileItemFactory();  
    	factory.setSizeThreshold(1024*1024*1024);  //设置阀值
    	// 与上一个结合使用，设置临时文件的路径（绝对路径）  
    	factory.setRepository(new File("/temp"));  
    	// Create a new file upload handler  
    	ServletFileUpload uploadHandler = new ServletFileUpload(factory);  
    	// 设置上传内容的大小限制（单位：字节）  
    	uploadHandler.setSizeMax(1024*1024*1024);  
    	// Parse the request  
    	List<?> items = uploadHandler.parseRequest(request);  
    	Iterator<?> iterator = items.iterator();  
    	while(iterator.hasNext()) {  
    	    FileItem fileItem = (FileItem) iterator.next();  
    	    if (!fileItem.isFormField()) { 
    	    	Map<String, Object> fileMap = new HashMap<String, Object>();
        	    String fileName = fileItem.getName();
        	    fileMap.put("name", fileName);
    			fileMap.put("size", fileItem.getSize());
            	String filepath = RemoteFileUtils.saveFileFromInputStream(fileItem.getInputStream(), fileName);
            	fileMap.put("filepath", filepath);
            	try {
					fileMap.putAll(busiContractTendersBranchService.importDataByEtl(RemoteFileUtils.basepath+"/"+filepath, orgId));
				} catch (Exception e) {
					e.printStackTrace();
					fileMap.put("error", "导入失败");
					fileMap.put("message", e.getMessage());
				}
            	files.add(fileMap);
    	    }
    	}
    	resultMap.put("files", files.toArray());
    	return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    
    /**
     * 根据orgID和修改时间查询所有修改的分部分项数据(for App)
     * @param queryPage
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "/queryAllLevelOfEdit")
    @ResponseBody
    public ResponseEntity<List<BusiContractTendersBranchDto>> _getAllAllLevelOfEditBusiContractTendersBranch(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	List<BusiContractTendersBranchDto> topLevelBusiContractTendersBranchList = busiContractTendersBranchService.getAllLevelOfEditBusiContractTendersBranchDto(queryPage);
        return new ResponseEntity<>(topLevelBusiContractTendersBranchList, HttpStatus.OK);
    }
    
}