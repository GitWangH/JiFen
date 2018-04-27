package com.huatek.busi.api.progress;
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

import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.base.BusiBaseImageListDto;
import com.huatek.busi.dto.progress.BusiProgressImageDto;
import com.huatek.busi.service.progress.BusiProgressImageService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 标段形象清单 
 * @author eli_cui
 *
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSIPROGRESSIMAGE_API)
public class BusiProgressImageAction {

    private static final Logger log = LoggerFactory.getLogger(BusiProgressImageAction.class);

    @Autowired
    private BusiProgressImageService busiProgressImageService;
    
    /**
     * 获取顶级节点
     * @param queryPage
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryTopLevel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<BusiProgressImageDto>> _getTopeLevel(@RequestBody QueryPage queryPage) throws Exception {
        return new ResponseEntity<>(busiProgressImageService.getTopLevelData(queryPage), HttpStatus.OK);
    }
    
    /**
     * 获取子节点
     * @param id
     * @param queryPage
     * @return
     */
    @RequestMapping(value = "/queryChildNodes/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<BusiProgressImageDto>> _getChildqNodesByParentUUID(@PathVariable("id") Long id, @RequestBody QueryPage queryPage){
    	return new ResponseEntity<>(busiProgressImageService.getChildqNodesByParentPK(id, queryPage), HttpStatus.OK);
    }
    
    /**
     * 获取子节点和设计金额
     * @param id
     * @param queryPage
     * @return
     */
    @RequestMapping(value = "/queryChildNodesAndDesignQuantity/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<BusiProgressImageDto>> _getChildqNodesAndDesignQuantityByParentUUID(@PathVariable("id") Long id, @RequestBody QueryPage queryPage){
    	List<BusiProgressImageDto> dtoList = busiProgressImageService.getChildqNodesAndDesignQuantityByParentPK(id, queryPage);
    	return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
    
    /**
     * 新增 修改 删除action
     * @param orgId
     * @param dtoList
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveData/{orgId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<TreeGridAddIdAndUUIDDto>> saveData(@PathVariable("orgId") Long orgId, @RequestBody List<BusiProgressImageDto> dtoList) throws Exception {
        return new ResponseEntity<>(busiProgressImageService.dataManipulation(orgId, dtoList), HttpStatus.CREATED);
    }
    
    /**
     * 生成形象清单action
     * @param orgId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/createData/{orgId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiProcessImage(@PathVariable("orgId") Long orgId) throws Exception {
    	busiProgressImageService.createProgressImage(orgId);
    	return new ResponseEntity<>(ResponseMessage.success("生成形象清单成功"), HttpStatus.OK);
    }
    
  
}
