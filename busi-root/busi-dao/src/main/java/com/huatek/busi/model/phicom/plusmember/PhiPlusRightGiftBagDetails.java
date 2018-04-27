package com.huatek.busi.model.phicom.plusmember;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: PhiPlusRightGiftBagDetails
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-10 14:56:12
  * @version: 1.0
  */

@Entity
@Table(name = "phi_plus_right_gift_bag_details")
public class PhiPlusRightGiftBagDetails extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "plus_right_gift_bag_details_id", nullable = false)
    private Long id;
	
	/** @Fields giftbagId : plus会员礼包id) */ 
   // @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@Column(name = "gift_bag_id", nullable = false, length=20)
	private Long  giftbagId;
	
	
	/** @Fields cpnsWayId : 优惠劵方案id) */ 
	@Column(name= "cpns_way_id", nullable = false, length=20)
    private Long cpnsWayId;
	
	/** @Fields cpnsQuantity : 优惠劵数量) */ 
	@Column(name= "cpns_quantity", nullable = false, length=20)
    private String cpnsQuantity;
	
	
	/** @Fields cpnsMoney : 面值) */ 
	@Column(name= "cpns_money", nullable = false, length=20)
	private int cpnsMoney;
	
	/** @Fields cpnsName ： 优惠券名称*/
	@Column(name = "cpns_name",nullable = true, length=100 )
	private String cpnsName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public Long getGiftbagId() {
		return giftbagId;
	}
	public void setGiftbagId(Long giftbagId) {
		this.giftbagId = giftbagId;
	}
	public int getCpnsMoney() {
		return cpnsMoney;
	}
	public void setCpnsMoney(int cpnsMoney) {
		this.cpnsMoney = cpnsMoney;
	}
	public String getCpnsName() {
		return cpnsName;
	}
	public void setCpnsName(String cpnsName) {
		this.cpnsName = cpnsName;
	}
	
	
}
