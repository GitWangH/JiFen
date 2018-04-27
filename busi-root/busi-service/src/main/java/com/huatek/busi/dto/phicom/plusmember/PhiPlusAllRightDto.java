package com.huatek.busi.dto.phicom.plusmember;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.management.loading.PrivateClassLoader;
import javax.persistence.Column;

import com.huatek.busi.dto.phicom.score.PhiScoreConfigRuleDto;

public class PhiPlusAllRightDto {

	/*plus会员权益*/
	private Long id;
    //private String giftBagType;
   // private String giftCategroy;
	//权益名称
    private String rightName;
    //任务是否开启（0ff：否；on：是）
    private String isValidate;
    //权限说明
    private String remark;
    //末次操作时间
    private String lastoperationtime;
    //操作人
    private String operationperson;
    //plus级别id：1 默认级别 339 
    private Long plusId;
  
	/** @Fields tasktype :任务类别  默认为1
	 * 任务类别,分为一下大类
	 * 1、消费类(forPayPoints):斐讯商城实际支付1元，获取1积分
	 * 2、签到类(forCheckinPoints):评论得积分，好评加晒图额外得分
	 * 3、评论类(forAppraisePoints):评论得积分，好评加晒图额外得分
	 * 4、个人资料类(forMInfoPoints):头像、昵称、生日、性别即得50积分
	 * 5、实名认证类(forAuthPoints):身份证实名认证
	 * 6、账号绑定类(forBindPoints):绑定微信/QQ/微博，每项得20积分
	 * 7、论坛活动类(forum):社区发帖得10积分，优质回复得5积分
	 * 8、商城分享类(forSharePoints):点击分享按钮即送积分，每日仅限2次
	 * 9、邀请注册类(forInviteePoints):成功邀请好友并注册，后续首次登录，推荐人都可获取积分 
	 * 10、首次开通plus会员（firstExclusive）专享礼包
	 * 11、每月专享（everyMothExclusive）:开通会员后次月开始发放每月专享礼包
	 * */ 
    private String tasktype;
    

    /**
     * 积分配置详细
     * */
    private PhiPlusRightDetailsDto plusRightDetail;
    
    /**
     * 礼包配置详细
     */
    private Set<PhiPlusRightGiftBagDetailsDto> plusRightGiftBagDetails;
    
    private List<PhiPlusRightGiftBagDetailsDto> plusRightGiftBagDetailsList;
    
    public List<PhiPlusRightGiftBagDetailsDto> getPlusRightGiftBagDetailsList() {
		return plusRightGiftBagDetailsList;
	}
	public void setPlusRightGiftBagDetailsList(
			List<PhiPlusRightGiftBagDetailsDto> plusRightGiftBagDetailsList) {
		this.plusRightGiftBagDetailsList = plusRightGiftBagDetailsList;
	}
	/**
     * 子表内容
     * */  
  
	//奖励
    private String award;
    
    /** @Fields scoreValue1 :积分值1(额外积分数值)*/ 
    private String scorevalue1;
    
    /** @Fields scoreValue2 :倍数（翻倍时）*/ 
    private String scorevalue2;
    
	/** @Fields scoreOrMutiply : 积分翻倍或者额外增加积分值（0：翻倍；1：额外积分） */ 
    private int scoreOrMutiply;
    
    /** @Fields cpnsWayId : 优惠劵方案id) */ 
    private Long cpnsWayId;
    
    /** @Fields cpnsQuantity : 优惠劵数量) */
    private String cpnsQuantity;
    
    /**@Fields sendTime : 发放时机*/
    private String startTip;
    /** @Fields cpnsMoney :面值*/
	private int cpnsMoney;
	
	/** @Fields cpnsName ： 优惠券名称*/
	private String cpnsName;
    
	/**礼包详情id*/
	private Long detailId;
    
/*    //礼包类型（从数据字典获取1：首次开通送 2：自下月开始每月送）
    private String giftBagType;
   
	public String getGiftBagType() {
		return giftBagType;
	}
	public void setGiftBagType(String giftBagType) {
		this.giftBagType = giftBagType;
	}*/
	
	public String getTasktype() {
		return tasktype;
	}
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}
	
	public PhiPlusRightDetailsDto getPlusRightDetail() {
		return plusRightDetail;
	}
	public void setPlusRightDetail(PhiPlusRightDetailsDto plusRightDetail) {
		this.plusRightDetail = plusRightDetail;
	}
/*	public PhiPlusRightGiftBagDetailsDto getPlusRightGiftBagDetails() {
		return plusRightGiftBagDetails;
	}
	public void setPlusRightGiftBagDetails(
			PhiPlusRightGiftBagDetailsDto plusRightGiftBagDetails) {
		this.plusRightGiftBagDetails = plusRightGiftBagDetails;
	}*/
	public String getAward() {
		return award;
	}
	public void setAward(String award) {
		this.award = award;
	}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	public String getIsValidate() {
		return isValidate;
	}
	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLastoperationtime() {
		return lastoperationtime;
	}
	public void setLastoperationtime(String lastoperationtime) {
		this.lastoperationtime = lastoperationtime;
	}
	public String getOperationperson() {
		return operationperson;
	}
	public void setOperationperson(String operationperson) {
		this.operationperson = operationperson;
	}
	public Long getPlusId() {
		return plusId;
	}
	public void setPlusId(Long plusId) {
		this.plusId = plusId;
	}
	public String getScorevalue1() {
		return scorevalue1;
	}
	public void setScorevalue1(String scorevalue1) {
		this.scorevalue1 = scorevalue1;
	}
	public String getScorevalue2() {
		return scorevalue2;
	}
	public void setScorevalue2(String scorevalue2) {
		this.scorevalue2 = scorevalue2;
	}
	public int getScoreOrMutiply() {
		return scoreOrMutiply;
	}
	public void setScoreOrMutiply(int scoreOrMutiply) {
		this.scoreOrMutiply = scoreOrMutiply;
	}
	public Long getCpnsWayId() {
		return cpnsWayId;
	}
	public void setCpnsWayId(Long cpnsWayId) {
		this.cpnsWayId = cpnsWayId;
	}
	public String getCpnsQuantity() {
		return cpnsQuantity;
	}
	public void setCpnsQuantity(String cpnsQuantity) {
		this.cpnsQuantity = cpnsQuantity;
	}
	public String getStartTip() {
		return startTip;
	}
	public void setStartTip(String startTip) {
		this.startTip = startTip;
	}
	public int getCpnsMoney() {
		return cpnsMoney;
	}
	public void setCpnsMoney(int cpnsMoney) {
		this.cpnsMoney = cpnsMoney;
	}
	public Set<PhiPlusRightGiftBagDetailsDto> getPlusRightGiftBagDetails() {
		return plusRightGiftBagDetails;
	}
	public void setPlusRightGiftBagDetails(
			Set<PhiPlusRightGiftBagDetailsDto> plusRightGiftBagDetails) {
		this.plusRightGiftBagDetails = plusRightGiftBagDetails;
	}
	public String getCpnsName() {
		return cpnsName;
	}
	public void setCpnsName(String cpnsName) {
		this.cpnsName = cpnsName;
	}
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	
	
}
