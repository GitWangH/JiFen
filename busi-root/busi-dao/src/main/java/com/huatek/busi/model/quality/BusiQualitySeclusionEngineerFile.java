package com.huatek.busi.model.quality;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.contract.BusiContractTendersBranch;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 隐蔽工程文件上传实体
 * 
 * @ClassName: BusiQualitySeclusionEngineerFile
 * @Description:隐蔽工程文件上传实体
 * @author: rocky_wei
 * @Email :
 * @date: 2017-11-09 14:14:21
 * @version: Windows 7
 */
@Entity
@Table(name = "busi_quality_seclusion_engineer_file")
public class BusiQualitySeclusionEngineerFile extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "SECLUSION_FILE_ID", nullable = true)
	private Long id;

	/** @Fields fileId : 附件uuid */
	@Column(name = "FILE_ID", nullable = false, length = 100)
	private String fileId;

	/** @Fields fileSuffix : 文件后缀名 */
	@Column(name = "FILE_SUFFIX", nullable = false, length = 20)
	private String fileSuffix;

	/** @Fields tendersBranch : 项目分部分项 */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "TENDERS_BRANCH_ID")
	private BusiContractTendersBranch tendersBranch;

	/** @Fields fileName : 文件名称 */
	@Column(name = "FILE_NAME", nullable = false, length = 100)
	private String fileName;

	/** @Fields modifyTime : 上传时间 */
	@Column(name = "MODIFY_TIME", nullable = false)
	private Date modifyTime;

	/** @Fields modifer : 上传人ID */
	@Column(name = "MODIFER", nullable = false)
	private Long modifer;

	/** @Fields modifierName : 上传人姓名 */
	@Column(name = "MODIFIER_NAME", nullable = false, length = 100)
	private String modifierName;

	/** @Fields remarks : 备注 */
	@Column(name = "REMARKS", nullable = false, length = 255)
	private String remarks;

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getModifer() {
		return modifer;
	}

	public void setModifer(Long modifer) {
		this.modifer = modifer;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileId() {
		return this.fileId;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFileSuffix() {
		return this.fileSuffix;
	}

	public BusiContractTendersBranch getTendersBranch() {
		return tendersBranch;
	}

	public void setTendersBranch(BusiContractTendersBranch tendersBranch) {
		this.tendersBranch = tendersBranch;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
