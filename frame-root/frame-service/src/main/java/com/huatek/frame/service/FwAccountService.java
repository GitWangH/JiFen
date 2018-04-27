package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.dto.FwAccountDto;
import com.huatek.frame.service.dto.SoureZtreeDto;
import com.huatek.frame.service.dto.UserForm;

public interface FwAccountService {
    /**
     * 保存FwAccount信息
     * 
     * @throws Exception
     */
    Long saveFwAccountDto(FwAccountDto fwAccountDto);

    /**
     * 删除FwAccount信息
     * 
     * @param id
     */
    void deleteFwAccount(Long id);

    /**
     * 获取FwAccount的Dto
     * 
     * @param id
     * @return
     */
    FwAccountDto getFwAccountDtoById(Long id);

    /**
     * 更新FwAccount信息
     * 
     * @throws Exception
     */
    void updateFwAccount(Long id, FwAccountDto fwAccountDto);

    /**
     * 分页查询
     */
    DataPage<FwAccountDto> getAllFwAccountPage(QueryPage queryPage);

    /**
     * 获取所有的FwAccount
     * 
     * @return
     */
   // List<FwAccountDto> getAllFwAccountDto();

    FwAccountDto getFwAccount(String name, String password);

    /**
     * 获取角色信息
     * 
     * @param userId
     * @return
     */
    List<SoureZtreeDto> getRole(Long userId);

    /**
     * 保存该用户下的角色权限关联信息
     * 
     * @param userId
     * @param dataStr
     */
    void saveAccountRole(Long userId, String dataStr, Long tenantId);

    /**
     * 获取数据角色
     * 
     * @Title: getDateRole
     * @Description: TODO
     * @createDate: 2016年7月18日 下午4:45:43
     * @param
     * @return List<SoureZtreeDto>
     * @throws
     */
    List<SoureZtreeDto> getDateRole(Long userId);
    
    /**
     * 保存该用户下的角色权限关联信息
     * 
     * @param userId
     * @param dataStr
     */
    void saveAccountDataRole(Long userId, String dataStr);

    /**
     * 直接更新数据
     * 
     * @param id
     * @throws Exception
     */
    void updateFwAccount(FwAccountDto acc);

    void updateFwAccountPass(FwAccountDto acc, String pass, String pass2);

   // void updateFwAccountOrg(Long accId, String belognOrg, String holdOrg);

    void updateUser(Long id, UserForm userForm);

    /**
     * 根据名称(模糊)
     * 
     * @Description: TODO
     * @param @param name
     * @param @return
     * @return List<FwAccount>
     * @throws
     * @author martin_ju
     * @e_mail martin_ju@huatek.com
     * @date 2016年6月25日
     */
    List<FwAccountDto> getFwAccountListLikeUserName(String name);

    /**
     * 根据组织结构获取人员信息
     * 
     * @Description: TODO
     * @param @param id
     * @param @return
     * @return List<FwAccount>
     * @throws
     * @author martin_ju
     * @e_mail martin_ju@huatek.com
     * @date 2016年6月25日
     */
    List<FwAccountDto> getFwAccountListByOrgId(Long id);
    
    /**
     * 
    * @Title: getFwAccountByAcctNameAndUserName 
    * @Description: 根据 职员姓名和职员代码查询
    * @createDate: 2016年8月24日 下午4:35:46
    * @param   @param name
    * @param   @param userName
    * @param   @return   
    * @return  FwAccountDto    
    * @throws 
    * @author fanyahui
    * @e_mail fanyahui@longshang.com
     */
    FwAccountDto getFwAccountByAcctNameAndUserName(String name, String userName);
    public FwAccountDto getFwAccountByAcctName(String name);
}
