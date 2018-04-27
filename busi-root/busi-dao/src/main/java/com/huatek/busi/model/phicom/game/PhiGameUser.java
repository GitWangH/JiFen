package com.huatek.busi.model.phicom.game;

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
  * @ClassName: PhiGameUser
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-02-09 14:48:27
  * @version: 1.0
  */

@Entity
@Table(name = "phi_game_user")
public class PhiGameUser extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "activity_id", nullable = false)
    private Long id;
	
	@Column(name = "game_id", nullable = false)
    private Long gameId;
	
	
	/**@Fields game_id : 游戏类型名*/
	@Column(name = "game_type", nullable = false, length = 40)
    private String gameType;
    
	/**@Fields usr_name : 用户名称 */
	@Column(name = "user_name", nullable = true, length = 40)
    private String usrName;
	
	/** @Fields tel : 手机号 */
	@Column(name = "tel", nullable = false, length = 100)
	private String tel;
	
	
	/** @Fields member_grade : 会员等级 */
	@Column(name = "member_grade", nullable = false, length = 100)
	private String memberGrade;
	
	/**@Fields score : 消耗积分 */
	@Column(name = "score", nullable = true)
    private BigDecimal score;
	
	/**@Fields score : 游戏获取积分 */
	@Column(name = "getscore", nullable = true)
    private BigDecimal getscore;
	
	/** @Fields createTime :游戏参与时间*/
	@Column(name = "createTime", nullable = true)
	private Date createTime;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public BigDecimal getGetscore() {
		return getscore;
	}

	public void setGetscore(BigDecimal getscore) {
		this.getscore = getscore;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	

}
