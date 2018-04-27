package com.huatek.busi.service.impl.project;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.BusiRemoteMonitorDao;
import com.huatek.busi.dto.project.BusiRemoteMonitorDto;
import com.huatek.busi.model.project.BusiRemoteMonitor;
import com.huatek.busi.service.project.BusiRemoteMonitorService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.PasswordEncoder;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiRemoteMonitorServiceImpl")
@Transactional
public class BusiRemoteMonitorServiceImpl implements BusiRemoteMonitorService {
	
	private static final BeanCopy beanToDto = BeanCopy.getInstance().addFieldMap("tenders.id", "tendersId").addFieldMap("tenders.shortName", "tendersName").addFieldMap("tenders.orgCode", "tendersCode");
	private static final BeanCopy dtoToBean = BeanCopy.getInstance().addFieldMap("tendersId", "tenders");
	
	@Autowired
	private BusiRemoteMonitorDao busiRemoteMonitorDao;
	
	@Override
	public void saveBusiRemoteMonitorDto(BusiRemoteMonitorDto entityDto)  {
		//根据页面传进来的值设置保存的值信息
		BusiRemoteMonitor entity = dtoToBean.convert(entityDto, BusiRemoteMonitor.class);
		entity.setCreator(UserUtil.getUser().getId());
		entity.setCreateDate(new Date());
		//保存之前操作
		entity.setTenantId(UserUtil.getUser().getTenantId());
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiRemoteMonitorDao.persistentBusiRemoteMonitor(entity);
	}
	
	
	@Override
	public BusiRemoteMonitorDto getBusiRemoteMonitorDtoById(Long id) {
		BusiRemoteMonitor entity = busiRemoteMonitorDao.findBusiRemoteMonitorById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiRemoteMonitorDto entityDto = beanToDto.convert(entity, BusiRemoteMonitorDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiRemoteMonitor(Long id, BusiRemoteMonitorDto entityDto) {
		BusiRemoteMonitor entity = busiRemoteMonitorDao.findBusiRemoteMonitorById(id);
		entityDto.setTenantId(entity.getTenantId());
		dtoToBean.mapIgnoreId(entityDto, entity);
		//	校验链接地址是否有效
	    this.isValidUrl(entityDto.getRemoteAddress());
	    //	密码修改
		if(StringUtils.isNotBlank(entityDto.getAcctPass())){
			try {
				if(null == decodeBase64(entityDto.getAcctPass())){
					String newPassword = new PasswordEncoder(entity.getAcctName(),null).encode(entityDto.getAcctPass());
					entity.setAcctPass(newPassword);
				}else {
					entity.setAcctPass(entityDto.getAcctPass());
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessRuntimeException("转码出错!", "-1");
			}
		}
		//进行持久化保存
		busiRemoteMonitorDao.persistentBusiRemoteMonitor(entity);
	}
	
	@Override
	public void deleteBusiRemoteMonitor(Long id) {
		beforeRemove(id);
		BusiRemoteMonitor entity = busiRemoteMonitorDao.findBusiRemoteMonitorById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiRemoteMonitorDao.deleteBusiRemoteMonitor(entity);
	}
	
	@Override
	public DataPage<BusiRemoteMonitorDto> getAllBusiRemoteMonitorPage(QueryPage queryPage) {
		DataPage<BusiRemoteMonitor> dataPage = busiRemoteMonitorDao.getAllBusiRemoteMonitor(queryPage);
		DataPage<BusiRemoteMonitorDto> datPageDto = beanToDto.convertPage(dataPage, BusiRemoteMonitorDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiRemoteMonitorDto> getAllBusiRemoteMonitorDto() {
		List<BusiRemoteMonitor> entityList = busiRemoteMonitorDao.findAllBusiRemoteMonitor();
		List<BusiRemoteMonitorDto> dtos = beanToDto.convertList(entityList, BusiRemoteMonitorDto.class);
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
	* @param    busiRemoteMonitorDto
	* @param    busiRemoteMonitor
	* @return  void    
	* @
	*/
	private void beforeSave(BusiRemoteMonitorDto entityDto, BusiRemoteMonitor entity) {
		//	String password = new String(decodeBase64(entity.getAcctPass()));
		//	更新密码
		if(StringUtils.isNotBlank(entityDto.getAcctPass())){
			String newPassword = new PasswordEncoder(entity.getAcctName(),null).encode(entityDto.getAcctPass());
			entity.setAcctPass(newPassword);
		}
		//	校验链接地址是否有效
	    this.isValidUrl(entityDto.getRemoteAddress()); 
	}
	
	/*** 
     * decode by Base64 
     */  
	public static byte[] decodeBase64(String input) throws Exception {
		Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method mainMethod = clazz.getMethod("decode", String.class);
		mainMethod.setAccessible(true);
		Object retObj = mainMethod.invoke(null, input);
		return (byte[]) retObj;
	}


	@Override
	public List<BusiRemoteMonitorDto> getAllUserRemoteMonitorByMotitorType(
			String type, Long orgId, Long currProId) {
		List<BusiRemoteMonitorDto> dtos = beanToDto.convertList(busiRemoteMonitorDao.getAllUserRemoteMonitorByMotitorType(type, orgId, currProId), BusiRemoteMonitorDto.class);
		return dtos;
	}
	
	/**
	 * 
	* @Title: isValidUrl 
	* @Description: 校验远程链接地址是否有效 
	* @createDate: 2017年11月17日 下午3:41:50
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	private void isValidUrl(String remoteAddress) {
		URL url;  
	    try {  
	         url = new URL(remoteAddress);  
	         url.openStream();  
	         url = null; 
	    } catch (Exception e1) {  
	         url = null;  
	         throw new BusinessRuntimeException("远程IP地址不可用!","-1");
	    }
		
	}
}
