package com.huatek.busi.model.phicom.coupons;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 第三方券管理实体类.
 * 
 * @ClassName: PhiThirdPartyCoupons
 * @Description:
 * @author: jordan_li
 * @Email :
 * @date: 2018-01-20 16:03:00
 * @version: Windows 7
 */

@Entity
@Table(name = "phi_third_party_coupons")
public class PhiThirdPartyCoupons extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "id", nullable = true)
	private Long id;

	/** @Fields cpnsId : 优惠券id */
	@Column(name = "cpns_id", nullable = true, length = 100)
	private String cpnsId;

	/** @Fields cpnsSource : 券来源（第三方公司名） */
	@Column(name = "cpns_source", nullable = false, length = 100)
	private String cpnsSource;

	/** @Fields cpnsName : 劵名称 */
	@Column(name = "cpns_name", nullable = false, length = 40)
	private String cpnsName;

	/** @Fields cpnsQuantity : 券数量 */
	@Column(name = "cpns_quantity", nullable = false, precision = 40, scale = 0)
	private BigDecimal cpnsQuantity;

	/** @Fields cpnsType : 券类型（0 : 加息券、1 : 加速券、2 : K码券、 3：现金券） */
	@Column(name = "cpns_type", nullable = false, length = 40)
	private String cpnsType;

	/** @Fields cpnsWay : 优惠券方案 */
	@Column(name = "cpns_way", nullable = false, length = 100)
	private String cpnsWay;

	/** @Fields cpnsValid : 券有效期（绝对有效期——展示具体到期日，相对有效期——展示有效天数） */
	@Column(name = "cpns_valid", nullable = false, length = 100)
	private String cpnsValid;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setCpnsId(String cpnsId) {
		this.cpnsId = cpnsId;
	}

	public String getCpnsId() {
		return this.cpnsId;
	}

	public void setCpnsSource(String cpnsSource) {
		this.cpnsSource = cpnsSource;
	}

	public String getCpnsSource() {
		return this.cpnsSource;
	}

	public void setCpnsName(String cpnsName) {
		this.cpnsName = cpnsName;
	}

	public String getCpnsName() {
		return this.cpnsName;
	}

	public void setCpnsQuantity(BigDecimal cpnsQuantity) {
		this.cpnsQuantity = cpnsQuantity;
	}

	public BigDecimal getCpnsQuantity() {
		return this.cpnsQuantity;
	}

	public void setCpnsType(String cpnsType) {
		this.cpnsType = cpnsType;
	}

	public String getCpnsType() {
		return this.cpnsType;
	}

	public void setCpnsWay(String cpnsWay) {
		this.cpnsWay = cpnsWay;
	}

	public String getCpnsWay() {
		return this.cpnsWay;
	}

	public void setCpnsValid(String cpnsValid) {
		this.cpnsValid = cpnsValid;
	}

	public String getCpnsValid() {
		return this.cpnsValid;
	}

}
