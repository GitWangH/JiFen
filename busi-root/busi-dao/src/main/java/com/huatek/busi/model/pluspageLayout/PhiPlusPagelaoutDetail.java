package com.huatek.busi.model.pluspageLayout;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: PhiPluPagelaoutDetail
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-25 13:13:37
  * @version: 1.0
  */

@Entity
@Table(name = "phi_plus_pagelaout_detail")
public class PhiPlusPagelaoutDetail extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7876691503797561300L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "PLUS_PAGELAYOUT_DETIAL_ID", nullable = true )
	private Long id ;
	
	/** @Fields plusPagelayoutId : plus页面维护id) */ 
	@Column(name = "PLUS_PAGELAYOUT_ID", nullable = false, length=20)
    private Long plusPagelayoutId;
	
	/** @Fields rightTile1 : 权益1标题*/ 
	@Column(name= "RIGHT_TILE", nullable = true, length=200 )
    private String rightTile;
	
	/** @Fields rightPicture1 : 权益1图片*/ 
	@Column(name= "RIGHT_PICTURE", nullable = true, length=200 )
    private String rightPicture;
	
	/** @Fields rightPholink1 : 权益1跳转链接*/ 
	@Column(name= "RIGHT_PHOLINK", nullable = true, length=200 )
    private String rightPholink;
	
	@Column(name= "ORDER_NUM", nullable = true, length=11 )
	private int orderNum;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPlusPagelayoutId() {
		return plusPagelayoutId;
	}
	public void setPlusPagelayoutId(Long plusPagelayoutId) {
		this.plusPagelayoutId = plusPagelayoutId;
	}
	public String getRightTile() {
		return rightTile;
	}
	public void setRightTile(String rightTile) {
		this.rightTile = rightTile;
	}
	public String getRightPicture() {
		return rightPicture;
	}
	public void setRightPicture(String rightPicture) {
		this.rightPicture = rightPicture;
	}
	public String getRightPholink() {
		return rightPholink;
	}
	public void setRightPholink(String rightPholink) {
		this.rightPholink = rightPholink;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
}
