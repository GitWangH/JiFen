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
 * 业务模块应用配置
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)   
@Table(name="FW_APPLY_SCOPE")
public class FwApplyScope extends BaseEntity {
	
	private static final long serialVersionUID = -1232666272498003390L;

	@Id
	@Column(name="APPLY_ID", nullable=false)	
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
	 * 目标类
	 */
	@Size(min=1, max=50)
	@Column(name = "TARGET_CLASS", nullable = false)
	private String targetClass;
	
	/**
	 * 目标字段
	 */
	@Size(min=1, max=50)
	@Column(name = "TARGET_FIELD", nullable = false)
	private String targetField;
	
	/**
	 * 是否应用 {1应用,0不应用}
	 */
	@Column(name = "IS_ENABLE")
	private int isEnable;
	
	/**
	 * 业务模块外键
	 */
	@ManyToOne
	@JoinColumn(name = "MAP_ID")
	private FwBusinessMap fwBusinessMap;
	public String getTargetClass() {
		return targetClass;
	}
	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}
	public String getTargetField() {
		return targetField;
	}
	public void setTargetField(String targetField) {
		this.targetField = targetField;
	}
	public int getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	public FwBusinessMap getFwBusinessMap() {
		return fwBusinessMap;
	}
	public void setFwBusinessMap(FwBusinessMap fwBusinessMap) {
		this.fwBusinessMap = fwBusinessMap;
	}
	
}