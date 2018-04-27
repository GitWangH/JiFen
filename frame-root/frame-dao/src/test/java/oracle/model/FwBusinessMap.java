package oracle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.huatek.frame.core.model.BaseEntity;
/***
 *数据权限业务模块管理
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)   
@Table(name="FW_BUSINESS_MAP")
public class FwBusinessMap extends BaseEntity {
	
	private static final long serialVersionUID = 1671410093926527437L;

	@Id
	@Column(name="BUSINESS_MAP_ID", nullable=false)	
	@SequenceGenerator(name="my_seq", sequenceName="S_FRAMEWORK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Size(min=5, max=50)
	@Column(name = "BUSINESS_MAP_NAME", nullable = false)
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	@JoinColumn(name = "SOURCE_ENTITY_ID")
	private FwSourceEntity fwSourceEntity;
	
	@ManyToOne
	@JoinColumn(name = "SOURCE_ID")
	private FwSource fwSourceObject;
	public FwSourceEntity getFwSourceEntity() {
		return fwSourceEntity;
	}
	public void setFwSourceEntity(FwSourceEntity fwSourceEntity) {
		this.fwSourceEntity = fwSourceEntity;
	}
	public FwSource getFwSourceObject() {
		return fwSourceObject;
	}
	public void setFwSourceObject(FwSource fwSourceObject) {
		this.fwSourceObject = fwSourceObject;
	}
}