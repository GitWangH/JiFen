package com.huatek.busi.service.phicom.member;

import java.math.BigDecimal;
import java.util.List;

import com.huatek.busi.dto.phicom.member.PhiMemberDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiMemberService {
	
	/** 
	* @Title: savePhiMemberDto 
	* @Description: 保存PhiMember信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiMemberDto(PhiMemberDto entityDto) ;
	
	/** 
	* @Title: deletePhiMember 
	* @Description:  删除PhiMember信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiMember(Long id) ;
	
	/** 
	* @Title: getPhiMemberDtoById 
	* @Description: 获取PhiMember的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiMemberDto getPhiMemberDtoById(Long id);

	
    /** 
	* @Title: updatePhiMember 
	* @Description:  更新PhiMember信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiMember(Long id, PhiMemberDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiMemberPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiMemberDto>    
	*/ 
	DataPage<PhiMemberDto> getAllPhiMemberPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiMemberDto 
	* @Description: 获取所有的PhiMember
	* @param      
	* @return  List<PhiMemberDto>    
	* @throws 
	*/
	List<PhiMemberDto> getAllPhiMemberDto();

	/**
	 * @Description: 拉黑会员
	 * @param id
	 * @param status
	 */
	void updateBackList(Long id, String status,PhiMemberDto entityDto);
	
	/**
	 * @Description: 根据经验值更新会员等级
	 * @param entityDto
	 */
	void updateMemberGradeByAllScore(Long memberId) ;
	
	/**
	 * @Description: 更新会员兑换订单数量
	 * @param id
	 */
	void updateOrderCount(Long id);
	
	PhiMember findPhiMemberByUid(int UId);
	
    /** 
	* @Title: updatePhiMemberInfo 
	* @Description:  更新PhiMember信息 
	* @param   UId
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiMemberInfo(int UId, PhiMemberDto entityDto) ;
	
	/**
	 * @Title:getPhiMemberDtoByUId
	 * @Description:  根据（UID）获取会员信息
	 * @param UId
	 * @return
	 */
	PhiMemberDto getPhiMemberDtoByUId(int UId);
	
	/**
	 * 
	 * @author eden  
	 * @date Jan 16, 2018 3:07:46 PM
	 * @desc 根据id查找用户信息
	 * @param: @param id
	 * @param: @return  
	 * @return: PhiMember      
	 * @throws
	 */
	public PhiMember findPhiMemberById(Long id);
	
	public void saveOrUpdatePhiMember(PhiMember entity);

	PhiMemberDto getPhiMemberVOByUId(int UId);

	public void memberAutoOpen();
	
	void UpdatePhimember(Long memberId);
	
	/***
	 * 统计累计积分
	 * @param memberId
	 * @return
	 */
	void UpdateAllScore(Long memberId);
	/***
	 * plus会员开通
	 * @param memberId
	 */
	void OpenPhimember(Long memberId,String plusCode);
	
	/***
	 * plus会员开通(重构方法)
	 * @param memberId
	 */
	void openPhimember(Long memberId,String plusCode);
	
	
	List<PhiMemberDto> getAllPlusPhiMemberDto();


	/***
	 * 查找PLus会员
	 * @param id
	 * @return
	 */
	PhiMemberDto getPlusPhiMemberDtoById(Long id);
	
	/**
	 * @author eden  
	 * @date Feb 1, 2018 11:58:07 PM
	 * @desc 推送积分商城会员信息给辰商商城
	 * @param: @param phiMember  
	 * @return: void      
	 * @throws
	 */
	public void pullMemberInfoToChenShang(PhiMember phiMember,String modelName);


	/**
	 * 通过uid得到用户信息
	 * @param valueOf
	 * @return
	 */
	PhiMember GetMemberPhiMemberByUid(Integer valueOf);
	
	boolean calcMemberAllScore();
	PhiMember calcEnableScore(PhiMember member);
	
	/**
	 * 根据手机号码查询会员信息
	 * @param valueOf
	 * @return
	 */
	PhiMember findPhiMemberByTelNumber(String valueOf);
	
	/***
	 * 根据会员id查询plus会员年龄
	 * @param memberId
	 * @return
	 */
	int getPhiMemberPlusYear(Long memberId);
}
