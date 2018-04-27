package com.huatek.busi.dto.phicom.member;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

public class PhiMemberGradeDto {

	/** id @Fields memberGradeId : 会员等级id */
	private Long id;

	// private Long memberGradeId;
	private String memberGradeCode;
	private String memberGrade;
	private String creatorId;
	private String createTime;
	private String remark;
	private String image;
	private BigDecimal scoreMax;
	private BigDecimal scoreMin;
	private String validState;

	private String conditionsMeet;

	private String scoreMultiple;// 消费返积分倍数(会员特权模块维护)

	/**
	 * 默认构造器
	 */
	public PhiMemberGradeDto() {
	}

	/*
	 * /** 使用model数据库模型，构造DTO
	 * 
	 * @param model
	 *//*
		 * public PhiMemberGradeDto(PhiMemberGrade model) { this.memberGradeId =
		 * model.getMemberGradeId(); this.memberGradeCode =
		 * model.getMemberGradeCode(); this.memberGrade =
		 * model.getMemberGrade(); this.creatorId = model.getCreatorId();
		 * this.createTime = model.getCreateTime(); this.remark =
		 * model.getRemark(); this.image = model.getImage(); this.scoreMax =
		 * model.getScoreMax(); this.scoreMin = model.getScoreMin();
		 * this.validState = model.getValidState(); }
		 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMemberGradeCode(String memberGradeCode) {
		this.memberGradeCode = memberGradeCode;
	}

	public String getMemberGradeCode() {
		return this.memberGradeCode;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public String getMemberGrade() {
		return this.memberGrade;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return this.image;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getValidState() {
		return validState;
	}

	public void setValidState(String validState) {
		this.validState = validState;
	}

	public BigDecimal getScoreMax() {
		return scoreMax;
	}

	public void setScoreMax(BigDecimal scoreMax) {
		this.scoreMax = scoreMax;
	}

	public BigDecimal getScoreMin() {
		return scoreMin;
	}

	public void setScoreMin(BigDecimal scoreMin) {
		this.scoreMin = scoreMin;
	}

	public String getConditionsMeet() {
		return conditionsMeet;
	}

	public void setConditionsMeet(String conditionsMeet) {
		this.conditionsMeet = conditionsMeet;
	}

	public String getScoreMultiple() {
		return scoreMultiple;
	}

	public void setScoreMultiple(String scoreMultiple) {
		this.scoreMultiple = scoreMultiple;
	}

	/*
	 * /**
	 * 
	 * @param 构造分页
	 * 
	 * @return
	 *//*
		 * public static DataPage<PhiMemberGradeDto>
		 * transToDtoPage(DataPage<PhiMemberGrade> dataPage) {
		 * List<PhiMemberGradeDto> dtos = new ArrayList<PhiMemberGradeDto>(); if
		 * (dataPage != null && dataPage.getContent() != null &&
		 * dataPage.getContent().size() > 0) { for (PhiMemberGrade m :
		 * dataPage.getContent()) { dtos.add(new PhiMemberGradeDto(m)); } }
		 * DataPage<PhiMemberGradeDto> dtoPage = new
		 * DataPage<PhiMemberGradeDto>(); dtoPage.setContent(dtos);
		 * dtoPage.setPage(dataPage.getPage());
		 * dtoPage.setPageSize(dataPage.getPageSize());
		 * dtoPage.setTotalPage(dataPage.getTotalPage());
		 * dtoPage.setTotalRows(dataPage.getTotalRows()); return dtoPage; }
		 *//**
	 * 
	 * 将 model 集合转为 dto 集合
	 * 
	 * @param
	 * @return
	 */
	/*
	 * public static List<PhiMemberGradeDto> transToDtoList(List<PhiMemberGrade>
	 * datas) { List<PhiMemberGradeDto> dtos =new
	 * ArrayList<PhiMemberGradeDto>(); for(PhiMemberGrade newDatas : datas){
	 * PhiMemberGradeDto dto = new PhiMemberGradeDto(newDatas); dtos.add(dto); }
	 * return dtos; }
	 */
}