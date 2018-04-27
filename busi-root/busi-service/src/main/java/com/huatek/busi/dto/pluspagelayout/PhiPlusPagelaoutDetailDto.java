package com.huatek.busi.dto.pluspagelayout;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.huatek.frame.core.page.DataPage;


public class PhiPlusPagelaoutDetailDto {
	//plusPagelayoutDetialId
    private Long id ;
    
	/** @Fields plusPagelayoutId : plus页面维护id) */ 
    private Long plusPagelayoutId;
	
	/** @Fields rightTile1 : 权益1标题*/ 
    private String rightTile;
	
	/** @Fields rightPicture1 : 权益1图片*/ 
    private String rightPicture;
	
	/** @Fields rightPholink1 : 权益1跳转链接*/ 
    private String rightPholink;
    
    private int orderNum;
    
    /**
	 * 默认构造器
	 */
	public PhiPlusPagelaoutDetailDto(){}


    /**
	 * 生成getter，setter 访问器
	 */
	
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