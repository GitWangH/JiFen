package com.huatek.busi.model.phicom.game;

import java.math.BigDecimal;

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
  * @ClassName: PhiGameConfig
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-02-08 18:16:04
  * @version: 1.0
  */

@Entity
@Table(name = "phi_game_config")
public class PhiGameConfig extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3001691165405248721L;
	
	//gameConfigId
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "game_config_id", nullable = false)
    private Long id;
	
	/**@Fields gameId : 游戏列表id */
	@Column(name = "game_id", nullable = false, length = 100)
    private Long gameId;
    
	/**@Fields location : 位置 */
	@Column(name = "location", nullable = true, length = 100)
    private String location;
	
	/**@Fields score : 积分 */
	@Column(name = "score", nullable = true, length = 100)
    private BigDecimal score;
	
	/**@Fields clientShow : 客户端显示文字 */
	@Column(name = "client_show", nullable = true, length = 100)
    private String clientShow;
	
	/**@Fields prizeRate : 中奖比例 */
	@Column(name = "prize_rate", nullable = true, length = 100)
    private BigDecimal prizeRate;
	
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public String getClientShow() {
		return clientShow;
	}
	public void setClientShow(String clientShow) {
		this.clientShow = clientShow;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public BigDecimal getPrizeRate() {
		return prizeRate;
	}
	public void setPrizeRate(BigDecimal prizeRate) {
		this.prizeRate = prizeRate;
	}
	
}
