package com.huatek.busi.model.phicom.score;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: PhiScoreFlow
 * @Description:
 * @author: Ken Bai
 * @Email : Ken_Bai@huatek.com
 * @date: 2018-01-08 13:35:12
 * @version: 1.0
 */

@Entity
@Table(name = "phi_year_ledger")
public class PhiYearLedger extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "year_ledger_id", nullable = false)
	private Long id;

	@Column(name = "member_id", nullable = false)
	private Long memberId;

	@Column(name = "total_score", nullable = false)
	private Integer totalScore;

	@Column(name = "consume_score", nullable = false)
	private Integer consumeScoure;

	@Column(name = "remain_score", nullable = false)
	private Integer remainScore;

	@Column(name = "year", nullable = false)
	private Integer year;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getConsumeScoure() {
		return consumeScoure;
	}

	public void setConsumeScoure(Integer consumeScoure) {
		this.consumeScoure = consumeScoure;
	}

	public Integer getRemainScore() {
		return remainScore;
	}

	public void setRemainScore(Integer remainScore) {
		this.remainScore = remainScore;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	

}
