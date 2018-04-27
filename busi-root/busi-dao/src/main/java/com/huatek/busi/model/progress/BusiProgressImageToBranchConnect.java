package com.huatek.busi.model.progress;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import com.huatek.busi.model.contract.BusiContractTendersBranch;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 标段形象清单与标段分部分项挂接实体类.
  * @ClassName: BusiProgressImageToBranchConnect
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-12-06 11:30:29
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_progress_image_to_branch_connect")
public class BusiProgressImageToBranchConnect extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ID", nullable = true )
 	private Long id;
 
    
	/** @Fields progressImage : 形象清单 */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name= "IMAGE_ID")
    private BusiProgressImage progressImage;
    
    
	/** @Fields branchId : 分部分项 */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name= "BRANCH_ID")
    private BusiContractTendersBranch contractTendersBranch;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }

	public BusiProgressImage getProgressImage() {
		return progressImage;
	}

	public void setProgressImage(BusiProgressImage progressImage) {
		this.progressImage = progressImage;
	}

	public BusiContractTendersBranch getContractTendersBranch() {
		return contractTendersBranch;
	}

	public void setContractTendersBranch(
			BusiContractTendersBranch contractTendersBranch) {
		this.contractTendersBranch = contractTendersBranch;
	}

}
