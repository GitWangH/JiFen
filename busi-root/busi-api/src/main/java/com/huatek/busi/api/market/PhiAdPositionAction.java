package com.huatek.busi.api.market;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.huatek.busi.dto.market.PhiAdPositionDto;
import com.huatek.busi.dto.market.PhiAdPositionPhoInfoDto;
import com.huatek.busi.dto.market.PhiPhoInfoDto;
import com.huatek.busi.service.market.PhiAdPositionService;
import com.huatek.busi.service.market.PhiPhoInfoService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.session.data.UserInfo;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIADPOSITION_API)
public class PhiAdPositionAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiAdPositionAction.class);

    @Autowired
    /*广告位置的service*/
    private PhiAdPositionService phiAdPositionService;
    @Autowired
    private OperationService operationService;
    @Autowired
    /*图片信息的service*/
    private PhiPhoInfoService phiPhoInfoService;
    /** 
    * @Title: getAllPhiAdPosition 
    * @Description:  翻页查询PhiAdPosition信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiAdPositionDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiAdPositionDto>> getAllPhiAdPosition(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<PhiAdPositionDto> phiAdPositionPages = phiAdPositionService.getAllPhiAdPositionPage(queryPage);
        return new ResponseEntity<>(phiAdPositionPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiAdPositionDto 
    * @Description: 添加PhiAdPosition 
    * @param    phiAdPositionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    * 
    * 多个广告标题 将广告标题放到图片信息的 plan1 和 plan2 中
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiAdPositionDto(@RequestBody PhiAdPositionPhoInfoDto phiAdPositionPhoInfoDto) throws Exception {
//        phiAdPositionService.savePhiAdPositionDto(phiAdPositionDto);
//        operationService.logOperation("创建【PhiAdPosition】");
//        return new ResponseEntity<>(ResponseMessage.success("PhiAdPosition创建成功"), HttpStatus.CREATED);
    	
    	if( "AD1".equals(phiAdPositionPhoInfoDto.getAdCode()) ){
    		/*移动端首页banner位*/
    		PhiAdPositionDto phiAdPositionDto = phiAdPositionService.getPhiAdPositionDtoById(Long.valueOf(1));
    		/*phiAdPositionDto.setAdTitle(phiAdPositionPhoInfoDto.getAdTitle());*/
    		phiAdPositionDto.setAdSubheading(phiAdPositionPhoInfoDto.getAdSubheading());
    		//更新首页Banner区的标题
    		phiAdPositionService.updatePhiAdPosition(Long.valueOf(1), phiAdPositionDto);
    		
    		UserInfo user = UserUtil.getUser();
    		
    		PhiPhoInfoDto PhiPhoInfoDto = new PhiPhoInfoDto();
    		/*保存广告标题*/
    		PhiPhoInfoDto.setPlan1(phiAdPositionPhoInfoDto.getPlan1());
    		PhiPhoInfoDto.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto.setPhoLink(phiAdPositionPhoInfoDto.getPhoLink());
    		PhiPhoInfoDto.setPhoStart(phiAdPositionPhoInfoDto.getPhoStart());
    		PhiPhoInfoDto.setPhoEnd(phiAdPositionPhoInfoDto.getPhoEnd());
    		
    		PhiPhoInfoDto.setOperator(user.getAcctName());
    		PhiPhoInfoDto.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName());
    		Format format = new SimpleDateFormat("yyyy-MM-dd");
    		PhiPhoInfoDto.setPhoEndOpTime(format.format(new Date()));
    		PhiPhoInfoDto.setPhoOrder(phiAdPositionPhoInfoDto.getPhoOrder());
    		PhiPhoInfoDto.setPhoSize(phiAdPositionPhoInfoDto.getPhoSize());
    		phiPhoInfoService.savePhiPhoInfoDto(PhiPhoInfoDto);
    		return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
    	}else if("AD2".equals(phiAdPositionPhoInfoDto.getAdCode())) {
    		/*移动端首页运营位*/
    		/*PhiAdPositionDto phiAdPositionDto = phiAdPositionService.getPhiAdPositionDtoById(Long.valueOf(2));
    		List<PhiAdPositionDto> phiAdPositionDtoList = new ArrayList<PhiAdPositionDto>();
    		PhiAdPositionDto phiAdPositionDto1 = phiAdPositionDto;
    		phiAdPositionDto.setAdTitle(phiAdPositionPhoInfoDto.getAdTitle());
    		phiAdPositionDto.setAdSubheading(phiAdPositionPhoInfoDto.getAdSubheading());
    		phiAdPositionDtoList.add(phiAdPositionDto);
    		
    		phiAdPositionDto1.setAdTitle(phiAdPositionPhoInfoDto.getAdTitle1());
    		phiAdPositionDto1.setAdSubheading(phiAdPositionPhoInfoDto.getAdSubheading1());
    		phiAdPositionDtoList.add(phiAdPositionDto1);
    		//批量添加广告位信息
    		phiAdPositionService.batchAdd(phiAdPositionDtoList);*/
    		/*多个广告标题 将广告副标题放到图片信息的 plan1 和 plan2 中*/
    		List<PhiPhoInfoDto> PhiPhoInfoDtoList = new ArrayList<PhiPhoInfoDto>();
    		PhiPhoInfoDto PhiPhoInfoDto1 = new PhiPhoInfoDto();
    		//保存广告位1信息
    		PhiPhoInfoDto1.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto1.setPhoPosition("AD2-1");
    		PhiPhoInfoDto1.setPhoLink(phiAdPositionPhoInfoDto.getPhoLink());
    		PhiPhoInfoDto1.setPhoStart(phiAdPositionPhoInfoDto.getPhoStart());
    		PhiPhoInfoDto1.setPhoEnd(phiAdPositionPhoInfoDto.getPhoEnd());
    		PhiPhoInfoDto1.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName());
    		PhiPhoInfoDto1.setPhoOrder(phiAdPositionPhoInfoDto.getPhoOrder());
    		PhiPhoInfoDto1.setPhoSize(phiAdPositionPhoInfoDto.getPhoSize());
    		PhiPhoInfoDto1.setPlan1(phiAdPositionPhoInfoDto.getAdTitle());
    		PhiPhoInfoDto1.setPlan2(phiAdPositionPhoInfoDto.getAdSubheading());
    		PhiPhoInfoDtoList.add(PhiPhoInfoDto1);
    		
    		//保存广告位2信息
    		PhiPhoInfoDto PhiPhoInfoDto2 = new PhiPhoInfoDto();
    		PhiPhoInfoDto2.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto2.setPhoPosition("AD2-2");
    		PhiPhoInfoDto2.setPhoLink(phiAdPositionPhoInfoDto.getPhoLink1());
    		PhiPhoInfoDto2.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName1());
    		PhiPhoInfoDto2.setPhoOrder(phiAdPositionPhoInfoDto.getPhoOrder1());
    		PhiPhoInfoDto2.setPlan1(phiAdPositionPhoInfoDto.getAdTitle1());
    		PhiPhoInfoDto2.setPlan2(phiAdPositionPhoInfoDto.getAdSubheading1());
    		PhiPhoInfoDtoList.add(PhiPhoInfoDto2);
    		//批量删除广告信息
    		List<PhiPhoInfoDto> list = phiPhoInfoService.getPhiPhoInfoByAdCode("AD2");
    		if(list!=null&&list.size()>0){
    			phiPhoInfoService.batchDelete(list);
    		}
    		//批量添加广告信息
    		phiPhoInfoService.batchAdd(PhiPhoInfoDtoList);
    		
    		return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
    	}else if("AD3".equals(phiAdPositionPhoInfoDto.getAdCode())) {
    		/*移动端首页游戏专区*/
    		PhiAdPositionDto phiAdPositionDto = phiAdPositionService.getPhiAdPositionDtoById(Long.valueOf(3));
    		phiAdPositionDto.setAdTitle(phiAdPositionPhoInfoDto.getAdTitle());
    		phiAdPositionDto.setAdSubheading(phiAdPositionPhoInfoDto.getAdSubheading());
    		//更新首页Banner区的标题
    		phiAdPositionService.updatePhiAdPosition(Long.valueOf(3), phiAdPositionDto);
    		List<PhiPhoInfoDto> phiPhoInfoDtoList = new ArrayList<PhiPhoInfoDto>();
    		PhiPhoInfoDto PhiPhoInfoDto1 = new PhiPhoInfoDto();
    		PhiPhoInfoDto PhiPhoInfoDto2 = new PhiPhoInfoDto();
    		PhiPhoInfoDto PhiPhoInfoDto3 = new PhiPhoInfoDto();
    		PhiPhoInfoDto PhiPhoInfoDto4 = new PhiPhoInfoDto();
    		//保存广告位1信息
    		PhiPhoInfoDto1.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto1.setPhoPosition("AD3-1");
    		PhiPhoInfoDto1.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName());
    		phiPhoInfoDtoList.add(PhiPhoInfoDto1);
    		//保存广告位2信息
    		PhiPhoInfoDto2.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto2.setPhoPosition("AD3-2");
    		PhiPhoInfoDto2.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName1());
    		phiPhoInfoDtoList.add(PhiPhoInfoDto2);
    		//保存广告位3信息
    		PhiPhoInfoDto3.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto3.setPhoPosition("AD3-3");
    		PhiPhoInfoDto3.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName2());
    		phiPhoInfoDtoList.add(PhiPhoInfoDto3);
    		//保存广告位4信息
    		PhiPhoInfoDto4.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto4.setPhoPosition("AD3-4");
    		PhiPhoInfoDto4.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName3());
    		phiPhoInfoDtoList.add(PhiPhoInfoDto4);
    		//批量删除广告信息
    		List<PhiPhoInfoDto> list = phiPhoInfoService.getPhiPhoInfoByAdCode("AD3");
    		if(list!=null&&list.size()>0){
    			phiPhoInfoService.batchDelete(list);
    		}
    		//批量添加广告信息
    		phiPhoInfoService.batchAdd(phiPhoInfoDtoList);
    		
    		return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
    	}else if("AD4".equals(phiAdPositionPhoInfoDto.getAdCode())) {
    		/*移动端首页热门推荐*/
    		PhiPhoInfoDto PhiPhoInfoDto = new PhiPhoInfoDto();
    		PhiPhoInfoDto.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto.setPhoLink(phiAdPositionPhoInfoDto.getPhoLink());
    		/*PhiPhoInfoDto.setPhoStart(phiAdPositionPhoInfoDto.getPhoStart());
    		PhiPhoInfoDto.setPhoEnd(phiAdPositionPhoInfoDto.getPhoEnd());*/
    		PhiPhoInfoDto.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName());
    		PhiPhoInfoDto.setPhoOrder(phiAdPositionPhoInfoDto.getPhoOrder());
    		PhiPhoInfoDto.setPhoSize(phiAdPositionPhoInfoDto.getPhoSize());
    		//批量删除广告信息
    		List<PhiPhoInfoDto> list = phiPhoInfoService.getPhiPhoInfoByAdCode("AD4");
    		if(list!=null&&list.size()>0){
    			phiPhoInfoService.batchDelete(list);
    		}
    		phiPhoInfoService.savePhiPhoInfoDto(PhiPhoInfoDto);
    		
    		return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
    	}else if("AD5".equals(phiAdPositionPhoInfoDto.getAdCode())) {
    		/*移动端列表筛选配置*/
    		PhiAdPositionDto phiAdPositionDto = phiAdPositionService.getPhiAdPositionDtoById(Long.valueOf(5));
    		phiAdPositionDto.setAdTitle(phiAdPositionPhoInfoDto.getAdTitle());
    		//更新首页Banner区的标题
    		phiAdPositionService.updatePhiAdPosition(Long.valueOf(5), phiAdPositionDto);
    		/**
    		 * 移动端的列表配置，用图片信息表中的字段来存储
    		 * below ==  PHO_URL  == PhoUrl
    		 * choose1 == PHO_ADDR == phoAddr
    		 * section == PHO_LINK == phoLink
    		 * section1 == PHO_ORDER == phoOrder
    		 * choose2 ==  PHO_SIZE  == phoSize
    		 * over ==  PLAN_1  == plan1
    		 * choose3 == PLAN_2 == plan2
    		 * adCode == AD5
    		 * */
    		PhiPhoInfoDto PhiPhoInfoDto = new PhiPhoInfoDto();
    		PhiPhoInfoDto.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		/*以下--phoUrl*/
    		PhiPhoInfoDto.setBelow(phiAdPositionPhoInfoDto.getBelow());
    		/*选择1 -- phoAddr */
    		PhiPhoInfoDto.setChoose1(phiAdPositionPhoInfoDto.getChoose1());
    		/*区间*/
    		PhiPhoInfoDto.setSection(phiAdPositionPhoInfoDto.getSection());
    		PhiPhoInfoDto.setSection1(phiAdPositionPhoInfoDto.getSection1());
    		/*选择2*/
    		PhiPhoInfoDto.setChoose2(phiAdPositionPhoInfoDto.getChoose2());
    		/*以上*/
    		PhiPhoInfoDto.setOver(phiAdPositionPhoInfoDto.getOver());
    		PhiPhoInfoDto.setChoose3(phiAdPositionPhoInfoDto.getChoose3());
    		//删除广告信息
    		List<PhiPhoInfoDto> list = phiPhoInfoService.getPhiPhoInfoByAdCode("AD5");
    		if(list!=null&&list.size()>0){
    			phiPhoInfoService.batchDelete(list);
    		}
    		phiPhoInfoService.savePhiPhoInfoDto(PhiPhoInfoDto);
    		return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
    	}else if("AD6".equals(phiAdPositionPhoInfoDto.getAdCode())) {
    		PhiAdPositionDto phiAdPositionDto = phiAdPositionService.getPhiAdPositionDtoById(Long.valueOf(6));
    		phiAdPositionDto.setAdTitle(phiAdPositionPhoInfoDto.getAdTitle());
    		//更新首页Banner区的标题
    		phiAdPositionService.updatePhiAdPosition(Long.valueOf(6), phiAdPositionDto);
    		
    		List<PhiPhoInfoDto> phiPhoInfoDtoList = new ArrayList<PhiPhoInfoDto>();
    		
    		//保存广告位1信息
    		PhiPhoInfoDto PhiPhoInfoDto1 = new PhiPhoInfoDto();
    		PhiPhoInfoDto1.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto1.setPhoPosition("AD6-1");
    		PhiPhoInfoDto1.setPlan1(phiAdPositionPhoInfoDto.getAdTitle());
    		PhiPhoInfoDto1.setPhoLink(phiAdPositionPhoInfoDto.getPhoLink());
    		PhiPhoInfoDto1.setPhoStart(phiAdPositionPhoInfoDto.getPhoStart());
    		PhiPhoInfoDto1.setPhoEnd(phiAdPositionPhoInfoDto.getPhoEnd());
    		PhiPhoInfoDto1.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName());
    		PhiPhoInfoDto1.setPhoOrder(phiAdPositionPhoInfoDto.getPhoOrder());
    		PhiPhoInfoDto1.setPhoSize(phiAdPositionPhoInfoDto.getPhoSize());
    		phiPhoInfoDtoList.add(PhiPhoInfoDto1);
    		
    		//保存广告位2信息
    		PhiPhoInfoDto PhiPhoInfoDto2 = new PhiPhoInfoDto();
    		PhiPhoInfoDto2.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto2.setPhoPosition("AD6-2");
    		PhiPhoInfoDto2.setPlan1(phiAdPositionPhoInfoDto.getAdTitle1());
    		PhiPhoInfoDto2.setPhoLink(phiAdPositionPhoInfoDto.getPhoLink1());
    		PhiPhoInfoDto2.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName1());
    		PhiPhoInfoDto2.setPhoOrder(phiAdPositionPhoInfoDto.getPhoOrder1());
    		phiPhoInfoDtoList.add(PhiPhoInfoDto2);
    		
    		//批量删除广告信息
    		List<PhiPhoInfoDto> list = phiPhoInfoService.getPhiPhoInfoByAdCode("AD6");
    		if(list!=null&&list.size()>0){
    			phiPhoInfoService.batchDelete(list);
    		}
    		//批量添加广告信息
    		phiPhoInfoService.batchAdd(phiPhoInfoDtoList);
    		return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
    	}else if("AD7".equals(phiAdPositionPhoInfoDto.getAdCode())) {
    		/*PC端首页积分兑换运营位*/

    		/*移动端首页运营位*/
    		PhiAdPositionDto phiAdPositionDto = phiAdPositionService.getPhiAdPositionDtoById(Long.valueOf(7));
    		List<PhiAdPositionDto> phiAdPositionDtoList = new ArrayList<PhiAdPositionDto>();
    		phiAdPositionDto.setAdTitle(phiAdPositionPhoInfoDto.getAdTitle());
    		phiAdPositionDto.setAdSubheading(phiAdPositionPhoInfoDto.getAdSubheading());
    		phiAdPositionDtoList.add(phiAdPositionDto);
    		phiAdPositionService.updatePhiAdPosition(Long.valueOf(7), phiAdPositionDto);
    		
    		
    		/*PC端首页游戏专区*/
    		List<PhiPhoInfoDto> phiPhoInfoDtoList = new ArrayList<PhiPhoInfoDto>();
    		PhiPhoInfoDto PhiPhoInfoDto1 = new PhiPhoInfoDto();
    		PhiPhoInfoDto PhiPhoInfoDto2 = new PhiPhoInfoDto();
    		PhiPhoInfoDto PhiPhoInfoDto3 = new PhiPhoInfoDto();
    		//保存广告位1信息
    		PhiPhoInfoDto1.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto1.setPhoPosition("AD7-1");
    		PhiPhoInfoDto1.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName1());
    		phiPhoInfoDtoList.add(PhiPhoInfoDto1);
    		//保存广告位2信息
    		PhiPhoInfoDto2.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto2.setPhoPosition("AD7-2");
    		PhiPhoInfoDto2.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName2());
    		phiPhoInfoDtoList.add(PhiPhoInfoDto2);
    		//保存广告位3信息
    		PhiPhoInfoDto3.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto3.setPhoPosition("AD7-3");
    		PhiPhoInfoDto3.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName3());
    		phiPhoInfoDtoList.add(PhiPhoInfoDto3);
    		//批量删除广告信息
    		List<PhiPhoInfoDto> list = phiPhoInfoService.getPhiPhoInfoByAdCode("AD7");
    		if(list!=null&&list.size()>0){
    			phiPhoInfoService.batchDelete(list);
    		}
    		//批量添加广告信息
    		phiPhoInfoService.batchAdd(phiPhoInfoDtoList);
    		return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
    	}
    	return new ResponseEntity<>(ResponseMessage.success("创建失败"), HttpStatus.FAILED_DEPENDENCY);
    }
    
    /** 
    * @Title: getPhiAdPositionDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiAdPositionDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiAdPositionDto> getPhiAdPositionDto(@PathVariable("id") Long id) {
    	PhiAdPositionDto phiAdPositionDto = phiAdPositionService.getPhiAdPositionDtoById(id);
        return new ResponseEntity<>(phiAdPositionDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiAdPosition 
    * @Description:修改PhiAdPosition信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiAdPositionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiAdPosition(@PathVariable("id") Long id, @RequestBody PhiAdPositionDto phiAdPositionDto) throws Exception {
        phiAdPositionService.updatePhiAdPosition(id, phiAdPositionDto);
        operationService.logOperation("修改【PhiAdPosition("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiAdPositionById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiAdPositionById(@PathVariable("id") Long id) throws Exception {
        phiAdPositionService.deletePhiAdPosition(id);
        operationService.logOperation("删除【PhiAdPosition("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAdAddressFromAdpositon(){
    	
    	return null;
    }
    
    /*修改广告位的图片信息 */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updatePhiAdPositionDto(@PathVariable("id") Long id,@RequestBody PhiAdPositionPhoInfoDto phiAdPositionPhoInfoDto)throws Exception{
    	if( "AD1".equals(phiAdPositionPhoInfoDto.getAdCode()) ){
    		/*移动端首页banner位*/
    		PhiAdPositionDto phiAdPositionDto = phiAdPositionService.getPhiAdPositionDtoById(Long.valueOf(1));
    		/*phiAdPositionDto.setAdTitle(phiAdPositionPhoInfoDto.getAdTitle());*/
    		phiAdPositionDto.setAdSubheading(phiAdPositionPhoInfoDto.getAdSubheading());
    		//更新首页Banner区的标题
    		phiAdPositionService.updatePhiAdPosition(Long.valueOf(1), phiAdPositionDto);
    		/*List<PhiPhoInfoDto> PhiPhoInfoDtoList = phiPhoInfoService.getPhiPhoInfoByAdCode("AD1");*/
    		UserInfo user = UserUtil.getUser();
    		
    		PhiPhoInfoDto PhiPhoInfoDto = phiPhoInfoService.getPhiPhoInfoDtoById(id);
    		PhiPhoInfoDto.setPlan1(phiAdPositionPhoInfoDto.getPlan1());
    		PhiPhoInfoDto.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto.setPhoLink(phiAdPositionPhoInfoDto.getPhoLink());
    		PhiPhoInfoDto.setOperator(user.getAcctName());
    		PhiPhoInfoDto.setPhoStart(phiAdPositionPhoInfoDto.getPhoStart());
    		PhiPhoInfoDto.setPhoEnd(phiAdPositionPhoInfoDto.getPhoEnd());
    		/*PhiPhoInfoDto.setPhoEndOpTime(phiAdPositionPhoInfoDto.getPhoEndOpTime());*/
    		Format format = new SimpleDateFormat("yyyy-MM-dd");
    		PhiPhoInfoDto.setPhoEndOpTime(format.format(new Date()));
    		PhiPhoInfoDto.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName());
    		PhiPhoInfoDto.setPhoOrder(phiAdPositionPhoInfoDto.getPhoOrder());
    		PhiPhoInfoDto.setPhoSize(phiAdPositionPhoInfoDto.getPhoSize());
    		phiPhoInfoService.saveOrUpdatePhiPhoInfo(PhiPhoInfoDto);
    		return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.CREATED);
    	}else if("AD2".equals(phiAdPositionPhoInfoDto.getAdCode())){
    		
    		List<PhiPhoInfoDto> PhiPhoInfoDtoList = phiPhoInfoService.getPhiPhoInfoByAdCode("AD2");
    		List<PhiPhoInfoDto> PhiPhoInfoDtoListNew = new ArrayList<PhiPhoInfoDto>();
    		PhiPhoInfoDto PhiPhoInfoDto1 = PhiPhoInfoDtoList.get(0);
    		PhiPhoInfoDto1.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto1.setPhoLink(phiAdPositionPhoInfoDto.getPhoLink());
    		PhiPhoInfoDto1.setPhoStart(phiAdPositionPhoInfoDto.getPhoStart());
    		PhiPhoInfoDto1.setPhoEnd(phiAdPositionPhoInfoDto.getPhoEnd());
    		PhiPhoInfoDto1.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName());
    		PhiPhoInfoDto1.setPhoOrder(phiAdPositionPhoInfoDto.getPhoOrder());
    		PhiPhoInfoDto1.setPhoSize(phiAdPositionPhoInfoDto.getPhoSize());
    		PhiPhoInfoDto1.setPlan1(phiAdPositionPhoInfoDto.getAdTitle());
    		PhiPhoInfoDto1.setPlan2(phiAdPositionPhoInfoDto.getAdSubheading());
    		PhiPhoInfoDtoListNew.add(PhiPhoInfoDto1);
    		if(phiAdPositionPhoInfoDto.getPhoLink1() != null){
    			PhiPhoInfoDto PhiPhoInfoDto2 = null;
    			if(PhiPhoInfoDtoList.size() > 1){
    				 PhiPhoInfoDto2 = PhiPhoInfoDtoList.get(1);
    			}else{
    				 PhiPhoInfoDto2 = new PhiPhoInfoDto();
    			}
    			PhiPhoInfoDto2.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    			PhiPhoInfoDto2.setPhoLink(phiAdPositionPhoInfoDto.getPhoLink1());
    			PhiPhoInfoDto2.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName1());
    			PhiPhoInfoDto2.setPhoOrder(phiAdPositionPhoInfoDto.getPhoOrder1());
    			PhiPhoInfoDto2.setPlan1(phiAdPositionPhoInfoDto.getAdTitle1());
    			PhiPhoInfoDto2.setPlan2(phiAdPositionPhoInfoDto.getAdSubheading1());
    			PhiPhoInfoDtoListNew.add(PhiPhoInfoDto2);
    		}
    		//批量添加广告信息
    		phiPhoInfoService.batchUpdate(PhiPhoInfoDtoListNew);
    		return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.CREATED);
    	}else if("AD3".equals(phiAdPositionPhoInfoDto.getAdCode())){
    		PhiAdPositionDto phiAdPositionDto = phiAdPositionService.getPhiAdPositionDtoById(Long.valueOf(3));
    		List<PhiPhoInfoDto> PhiPhoInfoDtoListNew = new ArrayList<PhiPhoInfoDto>();
    		phiAdPositionDto.setAdTitle(phiAdPositionPhoInfoDto.getAdTitle());
    		phiAdPositionDto.setAdSubheading(phiAdPositionPhoInfoDto.getAdSubheading());
    		//更新首页Banner区的标题
    		phiAdPositionService.updatePhiAdPosition(Long.valueOf(3), phiAdPositionDto);
    		/*List<PhiPhoInfoDto> phiPhoInfoDtoList = new ArrayList<PhiPhoInfoDto>();*/
    		List<PhiPhoInfoDto> PhiPhoInfoDtoList = phiPhoInfoService.getPhiPhoInfoByAdCode("AD3");
    		/*PhiPhoInfoDto PhiPhoInfoDto1 = new PhiPhoInfoDto();
    		PhiPhoInfoDto PhiPhoInfoDto2 = new PhiPhoInfoDto();
    		PhiPhoInfoDto PhiPhoInfoDto3 = new PhiPhoInfoDto();
    		PhiPhoInfoDto PhiPhoInfoDto4 = new PhiPhoInfoDto();*/
    		if(PhiPhoInfoDtoList != null && PhiPhoInfoDtoList.size() > 0){
    			PhiPhoInfoDto PhiPhoInfoDto1 = null;
    			if(PhiPhoInfoDtoList.size() > 1){
    				 PhiPhoInfoDto1 = PhiPhoInfoDtoList.get(0);
    			}else{
    				 PhiPhoInfoDto1 = new PhiPhoInfoDto();
    			}
    			PhiPhoInfoDto1.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
        		PhiPhoInfoDto1.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName());
        		PhiPhoInfoDtoListNew.add(PhiPhoInfoDto1);
    		}
    		
    		if(phiAdPositionPhoInfoDto.getPhoUuidName1() != null){
    			PhiPhoInfoDto PhiPhoInfoDto2 = null;
    			if(PhiPhoInfoDtoList.size() > 2){
    				 PhiPhoInfoDto2 = PhiPhoInfoDtoList.get(1);
    			}else{
    				 PhiPhoInfoDto2 = new PhiPhoInfoDto();
    			}
    			PhiPhoInfoDto2.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
        		PhiPhoInfoDto2.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName1());
        		PhiPhoInfoDtoListNew.add(PhiPhoInfoDto2);
    		}
    		
    		if(phiAdPositionPhoInfoDto.getPhoUuidName2() != null){
    			PhiPhoInfoDto PhiPhoInfoDto3 = null;
    			if(PhiPhoInfoDtoList.size() > 3){
    				 PhiPhoInfoDto3 = PhiPhoInfoDtoList.get(2);
    			}else{
    				 PhiPhoInfoDto3 = new PhiPhoInfoDto();
    			}
    			PhiPhoInfoDto3.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    			PhiPhoInfoDto3.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName2());
    			PhiPhoInfoDtoListNew.add(PhiPhoInfoDto3);
    		}
    		
    		if(phiAdPositionPhoInfoDto.getPhoUuidName3() != null){
    			PhiPhoInfoDto PhiPhoInfoDto4 = null;
    			if(PhiPhoInfoDtoList.size() > 4){
    				 PhiPhoInfoDto4 = PhiPhoInfoDtoList.get(3);
    			}else{
    				 PhiPhoInfoDto4 = new PhiPhoInfoDto();
    			}
    			PhiPhoInfoDto4.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
        		PhiPhoInfoDto4.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName3());
        		PhiPhoInfoDtoListNew.add(PhiPhoInfoDto4);
    		}
    		//批量修改广告信息
    		phiPhoInfoService.batchUpdate(PhiPhoInfoDtoListNew);
    		return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.CREATED);
    	}else if("AD4".equals(phiAdPositionPhoInfoDto.getAdCode())) {
    		/*移动端首页热门推荐*/
    		List<PhiPhoInfoDto> PhiPhoInfoDtoList = phiPhoInfoService.getPhiPhoInfoByAdCode("AD4");
    		PhiPhoInfoDto PhiPhoInfoDto = PhiPhoInfoDtoList.get(0);
    		PhiPhoInfoDto.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto.setPhoLink(phiAdPositionPhoInfoDto.getPhoLink());
    		PhiPhoInfoDto.setPhoStart(phiAdPositionPhoInfoDto.getPhoStart());
    		PhiPhoInfoDto.setPhoEnd(phiAdPositionPhoInfoDto.getPhoEnd());
    		PhiPhoInfoDto.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName());
    		PhiPhoInfoDto.setPhoOrder(phiAdPositionPhoInfoDto.getPhoOrder());
    		PhiPhoInfoDto.setPhoSize(phiAdPositionPhoInfoDto.getPhoSize());
    		phiPhoInfoService.saveOrUpdatePhiPhoInfo(PhiPhoInfoDto);
    		
    		return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.CREATED);
    	}else if("AD5".equals(phiAdPositionPhoInfoDto.getAdCode())) {
    		/*移动端列表筛选配置*/
    		PhiAdPositionDto phiAdPositionDto = phiAdPositionService.getPhiAdPositionDtoById(Long.valueOf(5));
    		phiAdPositionDto.setAdTitle(phiAdPositionPhoInfoDto.getAdTitle());
    		//更新首页Banner区的标题
    		phiAdPositionService.updatePhiAdPosition(Long.valueOf(5), phiAdPositionDto);
    		/**
    		 * 移动端的列表配置，用图片信息表中的字段来存储
    		 * below ==  PHO_URL  == PhoUrl
    		 * choose1 == PHO_ADDR == phoAddr
    		 * section == PHO_LINK == phoLink
    		 * section1 == PHO_ORDER == phoOrder
    		 * choose2 ==  PHO_SIZE  == phoSize
    		 * over ==  PLAN_1  == plan1
    		 * choose3 == PLAN_2 == plan2
    		 * adCode == AD5
    		 * */
    		List<PhiPhoInfoDto> PhiPhoInfoDtoList = phiPhoInfoService.getPhiPhoInfoByAdCode("AD5");
    		PhiPhoInfoDto PhiPhoInfoDto = PhiPhoInfoDtoList.get(0);
    		PhiPhoInfoDto.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		/*以下--phoUrl*/
    		PhiPhoInfoDto.setBelow(phiAdPositionPhoInfoDto.getBelow());
    		/*选择1 -- phoAddr */
    		PhiPhoInfoDto.setChoose1(phiAdPositionPhoInfoDto.getChoose1());
    		/*区间*/
    		PhiPhoInfoDto.setSection(phiAdPositionPhoInfoDto.getSection());
    		PhiPhoInfoDto.setSection1(phiAdPositionPhoInfoDto.getSection1());
    		/*选择2*/
    		PhiPhoInfoDto.setChoose2(phiAdPositionPhoInfoDto.getChoose2());
    		/*以上*/
    		PhiPhoInfoDto.setOver(phiAdPositionPhoInfoDto.getOver());
    		PhiPhoInfoDto.setChoose3(phiAdPositionPhoInfoDto.getChoose3());
    		phiPhoInfoService.saveOrUpdatePhiPhoInfo(PhiPhoInfoDto);
    		return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
    	}else if("AD6".equals(phiAdPositionPhoInfoDto.getAdCode())) {
    		/*PC端首页游戏专区*/
    		List<PhiPhoInfoDto> phiPhoInfoDtoList = phiPhoInfoService.getPhiPhoInfoByAdCode("AD6");
    		List<PhiPhoInfoDto> phiPhoInfoDtoListNew = new ArrayList<PhiPhoInfoDto>();;
    		
    		PhiPhoInfoDto PhiPhoInfoDto1 = phiPhoInfoDtoList.get(0);
    		
    		
    		PhiPhoInfoDto1.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto1.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName1());
    		phiPhoInfoDtoListNew.add(PhiPhoInfoDto1);
    		
    		if(phiAdPositionPhoInfoDto.getPhoUuidName1() != null){
    			PhiPhoInfoDto PhiPhoInfoDto2 = null;
    			if(phiPhoInfoDtoList.size() >1){
    				PhiPhoInfoDto2 = phiPhoInfoDtoList.get(1);
    			}else{
    				PhiPhoInfoDto2 = new PhiPhoInfoDto();
    			}
	    		PhiPhoInfoDto2.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
	    		PhiPhoInfoDto2.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName2());
	    		phiPhoInfoDtoListNew.add(PhiPhoInfoDto2);
    		}
    		
    		if(phiAdPositionPhoInfoDto.getPhoUuidName2() != null){
    			PhiPhoInfoDto PhiPhoInfoDto3 = null;
    			if(phiPhoInfoDtoList.size() >2){
    				PhiPhoInfoDto3 = phiPhoInfoDtoList.get(2);
    			}else{
    				PhiPhoInfoDto3 = new PhiPhoInfoDto();
    			}
	    		PhiPhoInfoDto3.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
	    		PhiPhoInfoDto3.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName3());
	    		phiPhoInfoDtoListNew.add(PhiPhoInfoDto3);
    		}
    		//批量添加广告信息
    		phiPhoInfoService.batchUpdate(phiPhoInfoDtoListNew);
    		return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.CREATED);
    	}else if("AD7".equals(phiAdPositionPhoInfoDto.getAdCode())) {
    		/*PC端首页积分兑换运营位*/

    		/*移动端首页运营位*/
    		PhiAdPositionDto phiAdPositionDto = phiAdPositionService.getPhiAdPositionDtoById(Long.valueOf(7));
    		List<PhiAdPositionDto> phiAdPositionDtoList = new ArrayList<PhiAdPositionDto>();
    		phiAdPositionDto.setAdTitle(phiAdPositionPhoInfoDto.getAdTitle());
    		phiAdPositionDto.setAdSubheading(phiAdPositionPhoInfoDto.getAdSubheading());
    		phiAdPositionDtoList.add(phiAdPositionDto);
    		phiAdPositionService.updatePhiAdPosition(Long.valueOf(7), phiAdPositionDto);
    		
    		List<PhiPhoInfoDto> phiPhoInfoDtoList = phiPhoInfoService.getPhiPhoInfoByAdCode("AD6");
    		List<PhiPhoInfoDto> phiPhoInfoDtoListNew = new ArrayList<PhiPhoInfoDto>();
    		PhiPhoInfoDto PhiPhoInfoDto1 = phiPhoInfoDtoList.get(0);
    		PhiPhoInfoDto1.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto1.setPhoLink(phiAdPositionPhoInfoDto.getPhoLink());
    		PhiPhoInfoDto1.setPhoStart(phiAdPositionPhoInfoDto.getPhoStart());
    		PhiPhoInfoDto1.setPhoEnd(phiAdPositionPhoInfoDto.getPhoEnd());
    		PhiPhoInfoDto1.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName());
    		PhiPhoInfoDto1.setPhoOrder(phiAdPositionPhoInfoDto.getPhoOrder());
    		PhiPhoInfoDto1.setPhoSize(phiAdPositionPhoInfoDto.getPhoSize());
    		phiPhoInfoDtoListNew.add(PhiPhoInfoDto1);
    		
    		PhiPhoInfoDto PhiPhoInfoDto2 = null;
    		if(phiPhoInfoDtoList.size() > 0){
    			PhiPhoInfoDto2 = phiPhoInfoDtoList.get(1);
    		}else{
    			PhiPhoInfoDto2 = new PhiPhoInfoDto();
    			}
    		PhiPhoInfoDto2.setAdCode(phiAdPositionPhoInfoDto.getAdCode());
    		PhiPhoInfoDto2.setPhoLink(phiAdPositionPhoInfoDto.getPhoLink1());
    		PhiPhoInfoDto2.setPhoUuidName(phiAdPositionPhoInfoDto.getPhoUuidName1());
    		PhiPhoInfoDto2.setPhoOrder(phiAdPositionPhoInfoDto.getPhoOrder1());
    		phiPhoInfoDtoListNew.add(PhiPhoInfoDto2);
    		//批量添加广告信息
    		phiPhoInfoService.batchUpdate(phiPhoInfoDtoListNew);
    		return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.CREATED);
    	}
    	return new ResponseEntity<>(ResponseMessage.success("修改失败"), HttpStatus.FAILED_DEPENDENCY);
    }
    
    
}
