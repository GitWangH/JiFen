/**
 *
 */
package oracle.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 属性字典分类实体.
 * @author winner pan
 *
 */
@Entity
@Table(name="FW_CATEGORY")
public class FwCategory extends BaseEntity  {

	/**
	 *
	 */
	private static final long serialVersionUID = -4912360231516449732L;
	@Id
	@Column(name="category_id", nullable=false)	
	@SequenceGenerator(name="my_seq", sequenceName="S_FRAMEWORK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	private Long id;
	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	@Column(name = "category_name", nullable = false,length = 50)
	private String categoryName;
	
	@Column(name = "kind_name", nullable = false,length = 50)
	private String kindName;
	
	@Column(name = "CREATED_DATE", nullable = false)
	private Date createTime;
	
	@Column(name = "CREATOR", nullable = false)
	private String createUser;
	
	@Column(name = "MODIFIED_DATE", nullable = false)
	private Date updateTime;
	
	@Column(name = "MODIFIER", nullable = false)
	private String updateUser;
	//cascade = CascadeType.ALL, fetch = FetchType.LAZY
	@OneToMany(cascade={CascadeType.ALL},orphanRemoval=true,mappedBy = "fwCategory" ,fetch=FetchType.LAZY)
	//@JsonBackReference
	private Set<FwProperty> fwPropertySet;
	
	
	

	public Set<FwProperty> getFwPropertySet() {
		return fwPropertySet;
	}
	
	public void setFwPropertySet(Set<FwProperty> fwPropertySet) {
		this.fwPropertySet = fwPropertySet;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}
