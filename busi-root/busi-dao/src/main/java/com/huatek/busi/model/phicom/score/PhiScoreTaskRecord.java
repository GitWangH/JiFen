package com.huatek.busi.model.phicom.score;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
* @ClassName: PhiScoreTaskRecord 
* @Description: 积分获取任务记录
* @author eden_sun
* @date Feb 28, 2018 9:48:59 AM 
*
 */
public class PhiScoreTaskRecord {
	@Id
	@GeneratedValue
	@Column(name = "member_id",nullable = false)
	private Long id;
	
//	private 

}
