package oracle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.huatek.frame.core.model.BaseEntity;
/***
 *数据权限管理
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)   
@Table(name="FW_DATA_ROLE")
public class FwDataRole extends BaseEntity {
	private static final long serialVersionUID = 914342528811025879L;
	@Id
	@Column(name="ROLE_ID", nullable=false)	
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
	@Column(name = "ROLE_NAME", nullable = false)
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}