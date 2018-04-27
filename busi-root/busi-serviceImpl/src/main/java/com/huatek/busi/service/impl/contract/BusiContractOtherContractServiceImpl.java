package com.huatek.busi.service.impl.contract;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.contract.BusiContractOtherContractDao;
import com.huatek.busi.dto.contract.BusiContractOtherContractDto;
import com.huatek.busi.model.contract.BusiContractOtherContract;
import com.huatek.busi.service.contract.BusiContractOtherContractService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;

/**
 * @ClassName: BusiContractOtherContractServiceImpl
 * @Description: 其它合同服务层接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-27 13:33:35
 * @version: 1.0
 */
@Service("busiContractOtherContractServiceImpl")
@Transactional
public class BusiContractOtherContractServiceImpl implements BusiContractOtherContractService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiContractOtherContractServiceImpl.class);
	
	@Autowired
	BusiContractOtherContractDao busiContractOtherContractDao;
	
	/**
	 * 机构对象的机构ID、机构名称转换至Dto对象的数据常量
	 */
	public static final Map<String,String> entityToFields = new HashMap<String,String>();
	static {
		entityToFields.put("busiFwOrg.name", "orgName");
		entityToFields.put("busiFwOrg.id", "orgId");
	}
	
	@Override
	public void saveBusiContractOtherContractDto(BusiContractOtherContractDto entityDto)  {
		log.debug("save busiContractOtherContractDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiContractOtherContract entity = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg")//这里map的key要写model里面的子对象名，不能写子对象的id
				  								   .convert(entityDto, BusiContractOtherContract.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiContractOtherContractDao.persistentBusiContractOtherContract(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	@Override
	public BusiContractOtherContractDto getBusiContractOtherContractDtoById(Long id) {
		log.debug("get busiContractOtherContract by id@" + id);
		BusiContractOtherContract entity = busiContractOtherContractDao.findBusiContractOtherContractById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		BusiContractOtherContractDto entityDto = BeanCopy.getInstance().addFieldMap("busiFwOrg.id", "orgId").convert(entity, BusiContractOtherContractDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiContractOtherContract(Long id, BusiContractOtherContractDto entityDto, String[] ignoreTargetFields) {
		log.debug("upadte entityDto by id@" + id);
		BusiContractOtherContract entity = busiContractOtherContractDao.findBusiContractOtherContractById(id);
		BeanCopy.getInstance().addIgnoreFields(ignoreTargetFields).mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiContractOtherContractDao.persistentBusiContractOtherContract(entity);
	}
	
	@Override
	public void deleteBusiContractOtherContract(Long id) {
		log.debug("delete busiContractOtherContract by id@" + id);
		beforeRemove(id);
		BusiContractOtherContract busiContractOtherContract = busiContractOtherContractDao.findBusiContractOtherContractById(id);
		if (null == busiContractOtherContract) {
			throw new ResourceNotFoundException(id);
		}
		busiContractOtherContract.setIsDelete(Constant.DELETE_STATUS_DELETED);//已软删除
		busiContractOtherContractDao.persistentBusiContractOtherContract(busiContractOtherContract);;//执行更新操作，不执行物理删除
//		busiContractOtherContractDao.deleteBusiContractOtherContract(busiContractOtherContract);
	}
	
	@Override
	public DataPage<BusiContractOtherContractDto> getAllBusiContractOtherContractPage(QueryPage queryPage) {
		queryPage.setSqlCondition(" busiFwOrg.level3 ="+UserUtil.getUser().getCurrProId()+" ");
		DataPage<BusiContractOtherContract> dataPage = busiContractOtherContractDao.getAllBusiContractOtherContract(queryPage);
		DataPage<BusiContractOtherContractDto> datPageDto = BeanCopy.getInstance().addFieldMaps(BusiContractTendersContractServiceImpl.entityToFields).convertPage(dataPage, BusiContractOtherContractDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiContractOtherContractDto> getAllBusiContractOtherContractDto() {
		List<BusiContractOtherContract> entityList = busiContractOtherContractDao.findAllBusiContractOtherContract();
		List<BusiContractOtherContractDto> dtos = BeanCopy.getInstance().addFieldMaps(BusiContractTendersContractServiceImpl.entityToFields).convertList(entityList, BusiContractOtherContractDto.class);
		return dtos;
	}
	
	/** 
	* @Title: beforeRemove 
	* @Description:  删除之前的操作 
	* @param    id
	* @return  void    
	* @throws  Exception
	*/
	private void beforeRemove(Long id) {

	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @param    busiContractOtherContractDto
	* @param    busiContractOtherContract
	* @return  void    
	* @
	*/
	private void beforeSave(BusiContractOtherContractDto busiContractOtherContractDto, BusiContractOtherContract busiContractOtherContract) {
		UserInfo currentUser = ThreadLocalClient.get().getOperator();
		busiContractOtherContract.setCreater(currentUser.getId());// 创建人id
		busiContractOtherContract.setCreaterName(currentUser.getUserName());// 创建人姓名
		busiContractOtherContract.setCreateTime(new Date());// 创建时间
		busiContractOtherContract.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);//未删除
	}
}
