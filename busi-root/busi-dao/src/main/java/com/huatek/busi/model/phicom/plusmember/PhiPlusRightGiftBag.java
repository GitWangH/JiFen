package com.huatek.busi.model.phicom.plusmember;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.huatek.busi.model.phicom.order.PhiOrder;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: PhiPlusRightGiftBag
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-10 14:53:38
  * @version: 1.0
  */

@Entity
@Table(name = "phi_plus_right_gift_bag")
public class PhiPlusRightGiftBag extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "gift_bag_id", nullable = false)
    private Long id;

	/** @Fields giftBagType :礼包类型（firstExclusive:首次开通plus会员专享礼包;everyMothExclusive:每月专享(首月开通次月送)*/ 
	@Column(name= "gift_bag_type", nullable = false, length=20)
    private String giftBagType;
	
	/** @Fields giftCategroy :礼物分类（从数据字段获取，默认为优惠券）*/ 
	@Column(name= "gift_categroy", nullable = false, length=20)
    private String giftCategroy;
	
	/** @Fields rightName :权益名称*/ 
	@Column(name= "right_name", nullable = false, length=20)
    private String rightName;
	
	/** @Fields sendTime :发放时间*/ 
	@Column(name= "send_time", nullable = false, length=20)
    private Date sendTime;
	
	/** @Fields giftPackageMoney :礼包配置(金额)*/ 
	@Column(name= "gift_package_money", nullable = false, length=20)
    private String giftPackageMoney;
	
	/** @Fields isValidate :任务是否开启（0ff：否；on：是）*/ 
	@Column(name= "is_validate", nullable = false, length=20)
    private String isValidate;
	
	/** @Fields remark :权限说明*/ 
	@Column(name= "remark", nullable = false, length=20)
    private String remark;
	
	/** @Fields lastoperationtime :最后操作时间*/ 
	@Column(name= "lastOperationTime", nullable = false, length=20)
    private Date lastoperationtime;
	
	/** @Fields operationperson :操作人*/ 
	@Column(name= "operationPerson", nullable = false, length=20)
    private String operationperson;
	
	/** @Fields plusId : PLUS会员编码ID */ 
	@Column(name= "plus_id", nullable = false, length=20)
    private Long plusId;
	
	/** @Fields startTip : PLUS会员编码ID */ 
	@Column(name= "start_tip", nullable = true, length=200)
	private String startTip;
	//积分配置类
	
	/*@OneToOne(mappedBy = "phiPlusRightGiftBag")
	private PhiPlusRightGiftBagDetails plusRightGiftBagDetails;*/
	/**一个礼包对应多个优惠券*/
	@OneToMany(cascade={CascadeType.ALL},mappedBy="giftbagId", fetch = FetchType.EAGER)
	//private Set<PhiPlusRightGiftBagDetails>  phiPlusRightGiftBagDetails;
	private List<PhiPlusRightGiftBagDetails> plusRightGiftBagDetailsList;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGiftBagType() {
		return giftBagType;
	}

	public void setGiftBagType(String giftBagType) {
		this.giftBagType = giftBagType;
	}

	public String getGiftCategroy() {
		return giftCategroy;
	}

	public void setGiftCategroy(String giftCategroy) {
		this.giftCategroy = giftCategroy;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getGiftPackageMoney() {
		return giftPackageMoney;
	}

	public void setGiftPackageMoney(String giftPackageMoney) {
		this.giftPackageMoney = giftPackageMoney;
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

	public Date getLastoperationtime() {
		return lastoperationtime;
	}

	public void setLastoperationtime(Date lastoperationtime) {
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

/*	public Set<PhiPlusRightGiftBagDetails> getPhiPlusRightGiftBagDetails() {
		return phiPlusRightGiftBagDetails;
	}

	public void setPhiPlusRightGiftBagDetails(
			Set<PhiPlusRightGiftBagDetails> phiPlusRightGiftBagDetails) {
		this.phiPlusRightGiftBagDetails = phiPlusRightGiftBagDetails;
	}
*/
	public String getStartTip() {
		return startTip;
	}

	public void setStartTip(String startTip) {
		this.startTip = startTip;
	}

	public List<PhiPlusRightGiftBagDetails> getPlusRightGiftBagDetailsList() {
		return plusRightGiftBagDetailsList;
	}

	public void setPlusRightGiftBagDetailsList(
			List<PhiPlusRightGiftBagDetails> plusRightGiftBagDetailsList) {
		this.plusRightGiftBagDetailsList = plusRightGiftBagDetailsList;
	}
	
	
	
}
