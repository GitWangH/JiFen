package com.huatek.busi.model.phicom.score;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 
* @ClassName: ScoreTaskConfig 
* @Description: 积分获取任务配置
* @author eden_sun
* @date Jan 5, 2018 2:51:39 PM 
*
 */
@Entity
@Table(name = "phi_score_task_config")
public class PhiScoreTaskConfig extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	/** @Fields orderId :订单id*/ 
	@Column(name= "stc_id", nullable = false, length=20)
	private Long id;
	
	//任务项
	@Column(name= "taskName", nullable = false,length=100)
	private String taskName;
	
	//任务前台名称
	@Column(name= "showName", nullable = false,length=100)
	private String showName;
	
	//任务说明
	@Column(name= "taskRemark", nullable = false,length=200)
	private String taskRemark;
	
	/**
	 * 任务类别,分为一下大类
	 * 1、消费类(forPayPoints):斐讯商城实际支付1元，获取1积分
	 * 2、签到类(forCheckinPoints):评论得积分，好评加晒图额外得分
	 * 3、评论类(forAppraisePoints):评论得积分，好评加晒图额外得分
	 * 4、个人资料类(forMInfoPoints):头像、昵称、生日、性别即得50积分
	 * 5、实名认证类(forAuthPoints):身份证实名认证
	 * 6、账号绑定类(forBindPoints):绑定微信/QQ/微博，每项得20积分
	 * 7、论坛活动类(forum):社区发帖得10积分，优质回复得5积分
	 * 7.1、发帖奖励(forPostedPoints)
	 * 7.2、加精奖励(forEssencePoints)
	 * 7.3、回复奖励(forReplyPoints)
	 * 7.4、参与问卷调研用户奖励(forSurveyPoints)
	 * 8、商城分享类(forSharePoints):点击分享按钮即送积分，每日仅限2次
	 * 9、邀请注册类(forInviteePoints):成功邀请好友并注册，后续首次登录，推荐人都可获取积分
	 */
	@Column(name= "taskType", nullable = false,length=20)
	private String taskType;	
	
	//任务时间类别,1为永久;2为带有起始时间,3为多任务时间
	@Column(name= "taskTimeType", nullable = false,length=100)
	private int taskTimeType;
	
	//任务是否开启,on为开,off为关
	@Column(name= "taskSwitch", nullable = false,length=10)
	private String taskSwitch="off";
	
	//操作人
	@Column(name= "operationPerson", nullable = true,length=20)
	private String operationPerson;
	
	//最后操作时间
	@Column(name= "lastOperationTime", nullable = true,length=30)
	private Date lastOperationTime;
	
	//积分配置类
	@OneToMany(cascade={CascadeType.ALL},mappedBy="scoreTaskConfig", fetch = FetchType.EAGER)
	private Set<PhiScoreConfigRule> scoreConfigRule;
	
	/** @Fields image : 图片*/ 
	@Column(name= "image",  length=100 )
    private String image;
	
	/** @Fields src : app链接*/ 
	@Column(name= "src",  length=100 )
    private String src;
	
	/** @Fields src : pc链接*/ 
	@Column(name= "pcSrc",  length=100 )
    private String pcSrc;
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getTaskRemark() {
		return taskRemark;
	}

	public void setTaskRemark(String taskRemark) {
		this.taskRemark = taskRemark;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public int getTaskTimeType() {
		return taskTimeType;
	}

	public void setTaskTimeType(int taskTimeType) {
		this.taskTimeType = taskTimeType;
	}

	public String getTaskSwitch() {
		return taskSwitch;
	}

	public void setTaskSwitch(String taskSwitch) {
		this.taskSwitch = taskSwitch;
	}

	public String getOperationPerson() {
		return operationPerson;
	}

	public void setOperationPerson(String operationPerson) {
		this.operationPerson = operationPerson;
	}

	public Date getLastOperationTime() {
		return lastOperationTime;
	}

	public void setLastOperationTime(Date lastOperationTime) {
		this.lastOperationTime = lastOperationTime;
	}

	public Set<PhiScoreConfigRule> getScoreConfigRule() {
		return scoreConfigRule;
	}

	public void setScoreConfigRule(Set<PhiScoreConfigRule> scoreConfigRule) {
		this.scoreConfigRule = scoreConfigRule;
	}

	public String getPcSrc() {
		return pcSrc;
	}

	public void setPcSrc(String pcSrc) {
		this.pcSrc = pcSrc;
	}



}
