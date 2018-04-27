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
import javax.validation.constraints.NotNull;

import com.huatek.frame.core.model.BaseEntity;


/***
 * 这是一个用户和角色的中间关联表.
 * 一个用户可分配多个角色
 * 一个角色可以分配给多个用户.
 * 该实体作为关联不用于管理.
 * @author winner pan
 *
 */
@Entity
@Table(name="FW_ACCOUNT_ROLE")
public class FwAccountRole extends BaseEntity 	{

	/**
	 *
	 */
	private static final long serialVersionUID = 56432278631030039L;
	@Id
	@Column(name="acct_role_id", nullable=false)	
	@SequenceGenerator(name="my_seq", sequenceName="S_FRAMEWORK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	/***
	 * 当前用户对应的角色。
	 */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "role_id")
	private FwRole fwRole;

	/**
	 * 用户数据.
	 * */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "acct_id")
	private FwAccount fwAccount;
	
	public FwAccountRole(){

	}

	/** full constructor */
	public FwAccountRole(FwRole fwRole,
			FwAccount fwAccount) {
		this.fwRole = fwRole;
		this.fwAccount = fwAccount;
	}




	public FwRole getFwRole() {
		return fwRole;
	}
	public void setFwRole(FwRole fwRole) {
		this.fwRole = fwRole;
	}
	public FwAccount getFwAccount() {
		return this.fwAccount;
	}

	public void setFwAccount(FwAccount fwAccount) {
		this.fwAccount = fwAccount;
	}

}
