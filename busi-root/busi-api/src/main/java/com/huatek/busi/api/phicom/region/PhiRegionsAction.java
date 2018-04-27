package com.huatek.busi.api.phicom.region;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.huatek.busi.dto.phicom.region.PhiRegionsDto;
import com.huatek.busi.service.phicom.region.PhiRegionsService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIREGIONS_API)
public class PhiRegionsAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiRegionsAction.class);

    @Autowired
    private PhiRegionsService phiRegionsService;

    
    /** 
    * @Title: getAllPhiRegions 
    * @Description:  翻页查询PhiRegions信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiRegionsDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiRegionsDto>> getAllPhiRegions(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiRegions of param " + queryPage.getQueryInfo());
        DataPage<PhiRegionsDto> phiRegionsPages = phiRegionsService.getAllPhiRegionsPage(queryPage);
        log.debug("get phiRegions size @" + phiRegionsPages.getContent().size());
        return new ResponseEntity<>(phiRegionsPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiRegionsDto 
    * @Description: 添加PhiRegions 
    * @param    phiRegionsDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiRegionsDto(@PathVariable("id") Long id) {
//        phiRegionsService.savePhiRegionsDto(phiRegionsDto);
    	phiRegionsService.mysave();
        return new ResponseEntity<>(ResponseMessage.success("PhiRegions创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiRegionsDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiRegionsDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiRegionsDto> getPhiRegionsDto(@PathVariable("id") Long id) {
    	PhiRegionsDto phiRegionsDto = phiRegionsService.getPhiRegionsDtoById(id);
        return new ResponseEntity<>(phiRegionsDto, HttpStatus.OK);
    }
    
    /** 
     * @Title: getPhiRegionsDto 
     * @Description: 获取需要修改 MdmLineBaseInfo信息
     * @createDate: 2016年4月25日 下午1:49:40
     * @param    id
     * @return   ResponseEntity<PhiRegionsDto>    
     */ 
     @RequestMapping(value = "/getAllAddress", method = RequestMethod.GET)
     @ResponseBody
     public ResponseEntity<List<PhiRegionsDto>> getAllPhiRegionsDto() {
     	List<PhiRegionsDto> phiRegionsDto = phiRegionsService.getAllPhiRegionsDto();
     	List<PhiRegionsDto> returnList = createTreeData(phiRegionsDto);
         return new ResponseEntity<>(returnList, HttpStatus.OK);
     }
    
     public static List<PhiRegionsDto> createTreeData(List<PhiRegionsDto> list){
    	 Map<String, PhiRegionsDto> level1 = new HashMap<String, PhiRegionsDto>();
    	 Map<String, PhiRegionsDto> level2 = new HashMap<String, PhiRegionsDto>();
    	 if(list != null && list.size() > 0){
    		 for (int i = 0; i < list.size(); i++) {
    			 if(list.get(i).getLevel().equals(new Long(1))){
    				 list.get(i).setChilds(new ArrayList<PhiRegionsDto>());
    				 level1.put(list.get(i).getCode(), list.get(i));
    			 }else if(list.get(i).getLevel().equals(new Long(2))){
    				 list.get(i).setChilds(new ArrayList<PhiRegionsDto>());
    				 level2.put(list.get(i).getCode(), list.get(i));
    			 }else if(list.get(i).getLevel().equals(new Long(3))){
    				 if(level2.get(list.get(i).getParentCode().toString()) != null){
    					 level2.get(list.get(i).getParentCode().toString()).getChilds().add(list.get(i));
    				 }
    			 }
			}
    	 }
    	 for (Map.Entry<String, PhiRegionsDto> dto : level2.entrySet()) { 
    		 if(level1.get(dto.getValue().getParentCode().toString()) != null){
    			 level1.get(dto.getValue().getParentCode().toString()).getChilds().add(dto.getValue());
    		 }
    	 }  
    	 Collection<PhiRegionsDto> treeList = level1.values();
    	 List<PhiRegionsDto> valueList = new ArrayList<PhiRegionsDto>(treeList);
    	 return valueList;
     }
    /** 
    * @Title: editPhiRegions 
    * @Description:修改PhiRegions信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiRegionsDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiRegions(@PathVariable("id") Long id, @RequestBody PhiRegionsDto phiRegionsDto) {
        phiRegionsService.updatePhiRegions(id, phiRegionsDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiRegionsById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiRegionsById(@PathVariable("id") Long id) {
        phiRegionsService.deletePhiRegions(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
}
