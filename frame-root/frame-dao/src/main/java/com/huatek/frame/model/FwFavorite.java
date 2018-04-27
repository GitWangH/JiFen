package com.huatek.frame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;
import com.huatek.frame.dao.model.FwSource;

 /**
  * 代码自动生成model类.
  * @ClassName: FwFavorite
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-10 15:27:55
  * @version: Windows 7
  */

@Entity
@Table(name = "fw_favorite")
public class FwFavorite extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ID", nullable = true )
 	private Long id;
 
    
	/** @Fields acctId : 用户id */
	@Column(name= "ACCT_ID", nullable = true)
    private Long acctId;
    
    
	/** @Fields sourceId : 菜单id */
	@ManyToOne
	@JoinColumn(name= "SOURCE_ID", nullable = true)
    private FwSource source;
    
    
	/** @Fields orderNo : 排序 */
	@Column(name= "ORDER_NO", nullable = false)
    private Integer orderNo;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setAcctId(Long acctId){
        this.acctId = acctId;
    }
      
    public Long getAcctId(){
        return this.acctId;
    }
      
    public FwSource getSource() {
		return source;
	}

	public void setSource(FwSource source) {
		this.source = source;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderNo(){
        return this.orderNo;
    }
      

}
