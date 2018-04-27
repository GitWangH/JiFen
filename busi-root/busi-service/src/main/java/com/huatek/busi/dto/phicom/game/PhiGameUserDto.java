package com.huatek.busi.dto.phicom.game;

import java.math.BigDecimal;
import java.util.Date;

public class PhiGameUserDto {
private Long id;
	
    private Long gameId;
	
	
	/**@Fields game_id : 游戏类型名*/
    private String gameType;
    
	/**@Fields usr_name : 用户名称 */
    private String usrName;
	
	/** @Fields tel : 手机号 */
	private String tel;
	
	
	/** @Fields member_grade : 会员等级 */
	private String memberGrade;
	
	/**@Fields score : 消耗积分 */
    private BigDecimal score;
	
	/**@Fields score : 游戏获取积分 */
    private BigDecimal getscore;
	
	/** @Fields createTime :游戏参与时间*/
	private Date createTime;
    /**
	 * 默认构造器
	 */
	public PhiGameUserDto(){}
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