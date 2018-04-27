package com.huatek.busi.api.phicom.coupons;
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
import com.huatek.busi.dto.phicom.coupons.PhiCouponsDto;
import com.huatek.busi.service.phicom.coupons.PhiCouponsService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHICOUPONS_API)
public class PhiCouponsAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiCouponsAction.class);

    @Autowired
    private PhiCouponsService phiCouponsService;

    
    /** 
    * @Title: getAllPhiCoupons 
    * @Description:  翻页查询PhiCoupons信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiCouponsDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiCouponsDto>> getAllPhiCoupons(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiCoupons of param " + queryPage.getQueryInfo());
        DataPage<PhiCouponsDto> phiCouponsPages = phiCouponsService.getAllPhiCouponsPage(queryPage);
        log.debug("get phiCoupons size @" + phiCouponsPages.getContent().size());
        return new ResponseEntity<>(phiCouponsPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiCouponsDto 
    * @Description: 添加PhiCoupons 
    * @param    phiCouponsDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiCouponsDto(@RequestBody PhiCouponsDto phiCouponsDto) {
        phiCouponsService.savePhiCouponsDto(phiCouponsDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiCoupons创建成功"), HttpStatus.CREATED);
    }
    
    
    
    /** 
    * @Title: getPhiCouponsDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiCouponsDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiCouponsDto> getPhiCouponsDto(@PathVariable("id") Long id) {
    	PhiCouponsDto phiCouponsDto = phiCouponsService.getPhiCouponsDtoById(id);
        return new ResponseEntity<>(phiCouponsDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiCoupons 
    * @Description:修改PhiCoupons信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiCouponsDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiCoupons(@PathVariable("id") Long id, @RequestBody PhiCouponsDto phiCouponsDto) {
        phiCouponsService.updatePhiCoupons(id, phiCouponsDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiCouponsById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiCouponsById(@PathVariable("id") Long id) {
        phiCouponsService.deletePhiCoupons(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /** 
    * @Title: getPhiCouponsDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiCouponsDto>    
    */ 
/*    @RequestMapping(value = "/choose/{cpnsMoney}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PhiCouponsDto>> choosePhiCouponsDto(@PathVariable("cpnsMoney") int cpnsMoney) {
    	List<PhiCouponsDto> phiCouponsDto = phiCouponsService.getPhiCoupons(cpnsMoney);
        return new ResponseEntity<>(phiCouponsDto, HttpStatus.OK);
    }*/
    
    /** 
    * @Title: getPhiCouponsDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiCouponsDto>    
    */ 
    @RequestMapping(value = "/select/{cpnsName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PhiCouponsDto>> choosePhiCouponsDto(@PathVariable("cpnsName") String cpnsName) {
    	List<PhiCouponsDto> phiCouponsDto = phiCouponsService.getPhiCouponsByCpnsName(cpnsName);
        return new ResponseEntity<>(phiCouponsDto, HttpStatus.OK);
    }
}
