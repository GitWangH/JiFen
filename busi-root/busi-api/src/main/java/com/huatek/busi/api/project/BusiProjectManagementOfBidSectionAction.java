package com.huatek.busi.api.project;
import java.io.IOException;
import java.util.List;

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
import com.huatek.busi.dto.base.BusiBaseImageListSubConnectionTableShowDto;
import com.huatek.busi.dto.project.BusiProjectManagementOfBidSectionDetailDto;
import com.huatek.busi.dto.project.BusiProjectManagementOfBidSectionDto;
import com.huatek.busi.dto.project.BusiProjectManagementOfBidSectionShowDto;
import com.huatek.busi.model.project.BusiProjectManagementOfBidSectionShow;
import com.huatek.busi.service.project.BusiProjectManagementOfBidSectionService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 工程标段管理
 * @author eli_cui
 *
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_PROJECT_MANAGEMENT_OF_BID_SECTION_API)
public class BusiProjectManagementOfBidSectionAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiProjectManagementOfBidSectionAction.class);

    @Autowired
    private BusiProjectManagementOfBidSectionService busiProjectManagementOfBidSectionService;

    
    /***
     * 工程标段管理 - 页面数据查询
     * @param queryPage
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiProjectManagementOfBidSectionShowDto>> getAllBusiProjectManagementOfBidSection(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiProjectManagementOfBidSectionShowDto> busiProjectManagementOfBidSectionPages = busiProjectManagementOfBidSectionService.getAllBusiProjectManagementOfBidSectionPage(queryPage);
        return new ResponseEntity<>(busiProjectManagementOfBidSectionPages, HttpStatus.OK);
       
    }
    
    /**
     * 工程标段管理 - 修改功能
     * @param busiProjectManagementOfBidSectionDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiProjectManagementOfBidSectionDto(@RequestBody BusiProjectManagementOfBidSectionDto busiProjectManagementOfBidSectionDto) throws Exception {
        busiProjectManagementOfBidSectionService.updateBusiProjectManagementOfBidSection(busiProjectManagementOfBidSectionDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.CREATED);
    }
    
    /**
     * 根据 工程标段管理 id 获取明细数据
     * @return
     */
    @RequestMapping(value = "/getDetailInfoByParentId/{parentId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<DataPage<BusiProjectManagementOfBidSectionDetailDto>> getDetailInfoByParentId(@PathVariable("parentId") Long parentId){
    	DataPage<BusiProjectManagementOfBidSectionDetailDto> detailDtoPage = busiProjectManagementOfBidSectionService.getDetailByParentId(parentId);
		return new ResponseEntity<>(detailDtoPage, HttpStatus.OK);
    }
    
    /***
     * 根据前台选择要删除的 工程管理标段-明细 主键id列表 获取是否可以删除状态。
     * @param detailIdList
     * @return code = 1 表示校验通过可以删除， else 返回 选中数据不可以删除的状态。
     */
    @RequestMapping(value = "/checkIsCanDelete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> checkIsCanDeleteByDetailIdList(@RequestBody List<Long> detailIdList){
    	String msgStr = busiProjectManagementOfBidSectionService.getBusiProjectManagementOfBidSectionDetailListById(detailIdList);
    	return new ResponseEntity<>(msgStr, HttpStatus.OK);
    }
    
}
