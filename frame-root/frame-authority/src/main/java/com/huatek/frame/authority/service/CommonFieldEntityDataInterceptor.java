package com.huatek.frame.authority.service;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.PropertyUtils;
import com.huatek.frame.core.dao.EntityInterceptor;
import com.huatek.frame.session.data.UserInfo;
@Component
public class CommonFieldEntityDataInterceptor implements EntityInterceptor {

	@Override
	public void checkAuthority(Object intance, Class<?> entityClass) {
		try{
			if(!PropertyUtils.isReadable(intance, "id")){
				return;
			}
			Long id=(Long)PropertyUtils.getProperty(intance, "id");
			UserInfo userInfo=UserUtil.getUser();
			Date now=new Date();
			if(id==null){
				if(PropertyUtils.isWriteable(intance, "createTime")){
					PropertyUtils.setProperty(intance, "createTime", now);
				}
				if(userInfo!=null){
					if(PropertyUtils.isWriteable(intance, "creater")){
						PropertyUtils.setProperty(intance, "creater", userInfo.getId());
					}
					if(PropertyUtils.isWriteable(intance, "createrName")){
						PropertyUtils.setProperty(intance, "createrName", userInfo.getUserName());
					}
					if(PropertyUtils.isReadable(intance, "tenantId")){
						Long tenantId=(Long)PropertyUtils.getProperty(intance, "tenantId");
						if(PropertyUtils.isWriteable(intance, "tenantId")&&tenantId==null){
							PropertyUtils.setProperty(intance, "tenantId", userInfo.getTenantId());
						}
					}
					
				}
				
			}
			if(PropertyUtils.isWriteable(intance, "modifyTime")){
				PropertyUtils.setProperty(intance, "modifyTime", now);
			}
			if(userInfo!=null){
				if(PropertyUtils.isWriteable(intance, "modifer")){
					PropertyUtils.setProperty(intance, "modifer", userInfo.getId());
				}
				if(PropertyUtils.isWriteable(intance, "modiferName")){
					PropertyUtils.setProperty(intance, "modiferName", userInfo.getUserName());
				}
				if(PropertyUtils.isWriteable(intance, "modifierName")){
					PropertyUtils.setProperty(intance, "modifierName", userInfo.getUserName());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
