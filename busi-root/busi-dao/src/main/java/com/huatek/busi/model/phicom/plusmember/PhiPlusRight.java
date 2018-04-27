package com.huatek.busi.model.phicom.plusmember;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.busi.model.phicom.score.PhiScoreConfigRule;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: PhiPlusRight
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-10 15:19:58
  * @version: 1.0
  */

@Entity
@Table(name = "phi_plus_right")
public class PhiPlusRight extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "plus_right_id", nullable = false)
	private Long id;
	
	/** @Fields lastoperationtime :最后操作时间*/ 
	@Column(name= "lastOperationTime", nullable = true, length=20)
    private Date lastoperationtime;
	
	/** @Fields operationperson :操作人*/ 
	@Column(name= "operationPerson", nullable = true, length=20)
    private String operationperson;
	
	/** @Fields showname :任务前台名称*/ 
	@Column(name= "showName", nullable = true, length=20)
    private String showname;
	
	/** @Fields taskname :权限名称*/ 
	@Column(name= "taskName", nullable = false, length=20)
    private String taskname;
	
	/** @Fields taskremark :权限说明*/ 
	@Column(name= "taskRemark", nullable = true, length=20)
    private String taskremark;
	
	/** @Fields taskswitch :任务是否开启（0ff：否；on：是）*/ 
	@Column(name= "taskSwitch", nullable = true, length=20)
    private String taskswitch;
	
	/** @Fields tasktimetype :任务时间类别(1为永久;2为带有起始时间,3为多任务时间)*/ 
	@Column(name= "taskTimeType", nullable = true, length=20)
    private String tasktimetype;
	
	
	/** @Fields tasktype :任务类别  默认为1
	 * 任务类别,分为一下大类
	 * 1、消费类(forPayPoints):斐讯商城实际支付1元，获取1积分
	 * 2、签到类(forCheckinPoints):评论得积分，好评加晒图额外得分
	 * 3、评论类(forAppraisePoints):评论得积分，好评加晒图额外得分
	 * 4、个人资料类(forMInfoPoints):头像、昵称、生日、性别即得50积分
	 * 5、实名认证类(forAuthPoints):身份证实名认证
	 * 6、账号绑定类(forBindPoints):绑定微信/QQ/微博，每项得20积分
	 * 7、论坛活动类(forum):社区发帖得10积分，优质回复得5积分
	 * 8、商城分享类(forSharePoints):点击分享按钮即送积分，每日仅限2次
	 * 9、邀请注册类(forInviteePoints):成功邀请好友并注册，后续首次登录，推荐人都可获取积分 
	 * 10、首次开通plus会员（firstExclusive）专享礼包
	 * 11、每月专享（everyMothExclusive）:开通会员后次月开始发放每月专享礼包
	 * */ 
	@Column(name= "taskType", nullable = false, length=20)
    private String tasktype;

	/** @Fields plusId : PLUS会员编码ID */ 
	@Column(name= "plus_id", nullable = false, length=20)
	private Long plusId;
	
	//积分配置类
	@OneToOne(mappedBy = "phiPlusRight")
	private PhiPlusRightDetails plusRightDetail;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastoperationtime() {
		return lastoperationtime;
	}

	public void setLastoperationtime(Date lastoperationtime) {
		this.lastoperationtime = lastoperationtime;
	}

	public String getOperationperson() {
		return operationperson;
	}

	public void setOperationperson(String operationperson) {
		this.operationperson = operationperson;
	}

	public String getShowname() {
		return showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getTaskremark() {
		return taskremark;
	}

	public void setTaskremark(String taskremark) {
		this.taskremark = taskremark;
	}

	public String getTaskswitch() {
		return taskswitch;
	}

	public void setTaskswitch(String taskswitch) {
		this.taskswitch = taskswitch;
	}

	public String getTasktimetype() {
		return tasktimetype;
	}

	public void setTasktimetype(String tasktimetype) {
		this.tasktimetype = tasktimetype;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public Long getPlusId() {
		return plusId;
	}

	public void setPlusId(Long plusId) {
		this.plusId = plusId;
	}

	public PhiPlusRightDetails getPlusRightDetail() {
		return plusRightDetail;
	}

	public void setPlusRightDetail(PhiPlusRightDetails plusRightDetail) {
		this.plusRightDetail = plusRightDetail;
	}

	
	
}
