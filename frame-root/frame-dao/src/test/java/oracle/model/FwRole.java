package oracle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.huatek.frame.core.model.BaseEntity;
/**
 * 系统功能角色.
 * @author winner pan.
 *
 */
@Entity
@Table(name="FW_ROLE")
public class FwRole extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -8474025844522178714L;
	@Id
	@Column(name="role_id", nullable=false)	
	@SequenceGenerator(name="my_seq", sequenceName="S_FRAMEWORK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 角色描述.
	 */
	@Size(max=200)
	@Column(name="comments", nullable=true)	
	private String comments;
	/***
	 * 角色名称.
	 */
	@Size(min=5,max=50)
	@Column(name="name", nullable=false)	
	private String name;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/***
	 * 角色所属组织.
	 */
	@ManyToOne
	@JoinColumn(name = "group_id")
	private FwOrg fwGroup;

	public FwRole(){

	}
	public FwRole(Long id){
		this.id = id;
	}

	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public FwOrg getFwGroup() {
		return fwGroup;
	}
	public void setFwGroup(FwOrg fwGroup) {
		this.fwGroup = fwGroup;
	}

	
}
