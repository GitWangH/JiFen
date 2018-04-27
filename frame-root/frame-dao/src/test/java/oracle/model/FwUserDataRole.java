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

import com.huatek.frame.core.model.BaseEntity;
/**
 * 系统功能角色.
 * @author winner pan.
 *
 */
@Entity
@Table(name="FW_USER_DATA_ROLE")
public class FwUserDataRole extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 794085079830275098L;

	@Id
	@Column(name="USER_DATA_ROLE_ID", nullable=false)	
	@SequenceGenerator(name="my_seq", sequenceName="S_FRAMEWORK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private FwAccount fwAccount;
	
	@ManyToOne
	@JoinColumn(name = "DATA_ROLE_ID")
	private FwDataRole fwDataRole;
	public FwAccount getFwAccount() {
		return fwAccount;
	}
	public void setFwAccount(FwAccount fwAccount) {
		this.fwAccount = fwAccount;
	}
	public FwDataRole getFwDataRole() {
		return fwDataRole;
	}
	public void setFwDataRole(FwDataRole fwDataRole) {
		this.fwDataRole = fwDataRole;
	}
}
