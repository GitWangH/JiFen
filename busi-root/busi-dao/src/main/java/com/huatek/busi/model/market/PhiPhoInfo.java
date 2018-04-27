package com.huatek.busi.model.market;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: PhiPhoInfo
 * @Description:
 * @author: nemo_wang
 * @Email :
 * @date: 2018-01-19 13:43:48
 * @version: Windows 7 广告位的图片描述
 */

@Entity
@Table(name = "phi_pho_info")
public class PhiPhoInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** @Fields phoId : 图片ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "PHO_ID", nullable = true)
	private Long id;

	/** @Fields adCode : 广告位CODE */
	@Column(name = "AD_CODE", nullable = false, length = 40)
	private String adCode;

	/** @Fields phoUrl : 图片URL */
	@Column(name = "PHO_URL", nullable = false, length = 200)
	private String phoUrl;

	/** @Fields phoAddr : 图片位置 */
	@Column(name = "PHO_ADDR", nullable = false, length = 200)
	private String phoAddr;

	/** @Fields phoLink : 图片链接 */
	@Column(name = "PHO_LINK", nullable = false, length = 200)
	private String phoLink;

	/** @Fields phoStart : 图片开始时间 */
	@Column(name = "PHO_START", nullable = false)
	private Date phoStart;

	/** @Fields phoEnd : 图片结束时间 */
	@Column(name = "PHO_END", nullable = false)
	private Date phoEnd;

	/** @Fields phoOrder : 图片排序标识 */
	@Column(name = "PHO_ORDER", nullable = false, length = 20)
	private String phoOrder;

	/** @Fields phoSize : 图片大小 */
	@Column(name = "PHO_SIZE", nullable = false, length = 40)
	private String phoSize;

	/** @Fields plan1 : 预留1 */
	@Column(name = "PLAN_1", nullable = false, length = 10)
	private String plan1;

	/** @Fields plan2 : 预留2 */
	@Column(name = "PLAN_2", nullable = false, length = 10)
	private String plan2;

	/** @Fields phoUuidName : 图片UUID名称 */
	@Column(name = "PHO_UUID_NAME", nullable = false, length = 100)
	private String phoUuidName;

	/** @Fields phoUuidName : 末次操作时间 */
	@Column(name = "PHO_ENDOPTIME", nullable = false, length = 100)
	private String phoEndOpTime;

	/** @Fields phoAddr : 展示位置 */
	@Column(name = "PHO_POSITION", nullable = false, length = 20)
	private String phoPosition;

	/** @Fields operator : 操作人 */
	@Column(name = "OPERATOR", nullable = false, length = 20)
	private String operator;

	/** @Fields section : 区间 */
	@Column(name = "SECTION", nullable = false, length = 255)
	private String section;

	/** @Fields section1 : 区间1 */
	@Column(name = "SECTION1", nullable = false, length = 255)
	private String section1;

	/** @Fields over : 以上 */
	@Column(name = "OVER", nullable = false, length = 255)
	private String over;

	/** @Fields below : 以下 */
	@Column(name = "BELOW", nullable = false, length = 255)
	private String below;

	/** @Fields choose1 : 选择1 */
	@Column(name = "CHOOSE1", nullable = false, length = 255)
	private String choose1;

	/** @Fields choose2 : 选择2 */
	@Column(name = "CHOOSE2", nullable = false, length = 255)
	private String choose2;

	/** @Fields choose3 : 选择3 */
	@Column(name = "CHOOSE3", nullable = false, length = 255)
	private String choose3;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}

	public String getAdCode() {
		return this.adCode;
	}

	public void setPhoUrl(String phoUrl) {
		this.phoUrl = phoUrl;
	}

	public String getPhoUrl() {
		return this.phoUrl;
	}

	public void setPhoAddr(String phoAddr) {
		this.phoAddr = phoAddr;
	}

	public String getPhoAddr() {
		return this.phoAddr;
	}

	public void setPhoLink(String phoLink) {
		this.phoLink = phoLink;
	}

	public String getPhoLink() {
		return this.phoLink;
	}

	public void setPhoStart(Date phoStart) {
		this.phoStart = phoStart;
	}

	public Date getPhoStart() {
		return this.phoStart;
	}

	public void setPhoEnd(Date phoEnd) {
		this.phoEnd = phoEnd;
	}

	public Date getPhoEnd() {
		return this.phoEnd;
	}

	public void setPhoOrder(String phoOrder) {
		this.phoOrder = phoOrder;
	}

	public String getPhoOrder() {
		return this.phoOrder;
	}

	public void setPhoSize(String phoSize) {
		this.phoSize = phoSize;
	}

	public String getPhoSize() {
		return this.phoSize;
	}

	public void setPlan1(String plan1) {
		this.plan1 = plan1;
	}

	public String getPlan1() {
		return this.plan1;
	}

	public void setPlan2(String plan2) {
		this.plan2 = plan2;
	}

	public String getPlan2() {
		return this.plan2;
	}

	public void setPhoUuidName(String phoUuidName) {
		this.phoUuidName = phoUuidName;
	}

	public String getPhoUuidName() {
		return this.phoUuidName;
	}

	public String getPhoEndOpTime() {
		return phoEndOpTime;
	}

	public void setPhoEndOpTime(String phoEndOpTime) {
		this.phoEndOpTime = phoEndOpTime;
	}

	public String getPhoPosition() {
		return phoPosition;
	}

	public void setPhoPosition(String phoPosition) {
		this.phoPosition = phoPosition;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSection1() {
		return section1;
	}

	public void setSection1(String section1) {
		this.section1 = section1;
	}

	public String getOver() {
		return over;
	}

	public void setOver(String over) {
		this.over = over;
	}

	public String getBelow() {
		return below;
	}

	public void setBelow(String below) {
		this.below = below;
	}

	public String getChoose1() {
		return choose1;
	}

	public void setChoose1(String choose1) {
		this.choose1 = choose1;
	}

	public String getChoose2() {
		return choose2;
	}

	public void setChoose2(String choose2) {
		this.choose2 = choose2;
	}

	public String getChoose3() {
		return choose3;
	}

	public void setChoose3(String choose3) {
		this.choose3 = choose3;
	}

}
