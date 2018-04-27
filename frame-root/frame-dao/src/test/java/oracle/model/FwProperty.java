package oracle.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.huatek.frame.core.model.BaseEntity;

/***
 * 字典属性实体.
 * 
 * @author winner pan.
 *
 */
@Entity
@Table(name="FW_PROPERTY")
public class FwProperty extends BaseEntity {

	private static final long serialVersionUID = 672390836669453253L;
	@Id
	@Column(name="property_id", nullable=false)	
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
	//属性值
	@Column(name="property_value", nullable=false)	
	private String propertyValue;
	//属性名称
	@Column(name="property_name", nullable=false)	
	private String propertyName;
	//属性类别
	@NotNull
	@ManyToOne
	@JoinColumn(name = "category_id")
	private FwCategory fwCategory;
	
	@Column(name = "CREATED_DATE", nullable = false)
	private Date createTime;
	
	@Column(name = "CREATOR", nullable = false)
	private String createUser;
	
	@Column(name = "MODIFIED_DATE", nullable = false)
	private Date updateTime;
	
	@Column(name = "MODIFIER", nullable = false)
	private String updateUser;
	

	public FwProperty(){

	}
	
	public FwProperty(Long id, String propertyName,String propertyValue){
		this.id = id;
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}

	public FwProperty(String propertyName,String propertyValue){
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}

	

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}


	public FwCategory getBaseProperty() {
		return fwCategory;
	}

	public void setBaseProperty(FwCategory baseProperty) {
		this.fwCategory = baseProperty;
	}

	public FwCategory getFwCategory() {
		return fwCategory;
	}
	
	public void setFwCategory(FwCategory fwCategory) {
		this.fwCategory = fwCategory;
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
