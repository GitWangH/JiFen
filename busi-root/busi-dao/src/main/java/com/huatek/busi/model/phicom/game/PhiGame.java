package com.huatek.busi.model.phicom.game;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBagDetails;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: PhiGame
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-02-09 10:32:01
  * @version: 1.0
  */

@Entity
@Table(name = "phi_game")
public class PhiGame extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
		@Column(name = "game_id", nullable = false)
	    private Long id;
		
		/**@Fields game_type : 游戏类型*/
		@Column(name = "game_type", nullable = false, length = 40)
	    private String gameType;
	    
		/**@Fields game_name : 游戏名称 */
		@Column(name = "game_name", nullable = true, length = 40)
	    private String gameName;
		
		/** @Fields lastOperationTime :上次操作时间*/
		@Column(name = "lastOperationTime", nullable = true)
		private Date lastOperationTime;
		
		/**@Fields task_switch : 是否开启 off否，on是 */
		@Column(name = "task_switch", nullable = true, length = 10)
	    private String taskSwitch;
		
		/**@Fields remark : 游戏说明 */
		@Column(name = "remark", nullable = true, length = 200)
	    private String remark;
		
		/**@Fields free_times_day : 每日免费次数 */
		@Column(name = "free_times_day", nullable = true)
	    private BigDecimal freeTimesDay;
		
		
		/**@Fields one_time_score : 单次消耗积分 */
		@Column(name = "one_time_score", nullable = true)
	    private BigDecimal oneTimeScore;
		
		//操作人
		@Column(name= "operationPerson", nullable = true,length=20)
		private String operationPerson;
		
		//单日抽奖次数上线
		@Column(name= "draw_max", nullable = true,length=11)
		private  int drawMax;
		
		/**一个游戏对应多个积分配置*/
		@OneToMany(cascade={CascadeType.ALL},mappedBy="gameId", fetch = FetchType.EAGER)
		private List<PhiGameConfig> phiGameConfigs;

		/**@Fields type : 类型(bigWheel大转盘  ninePlace九宫格)*/
		@Column(name = "type", nullable = false, length = 40)
	    private String type;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getGameType() {
			return gameType;
		}

		public void setGameType(String gameType) {
			this.gameType = gameType;
		}

		public String getGameName() {
			return gameName;
		}

		public void setGameName(String gameName) {
			this.gameName = gameName;
		}

		public Date getLastOperationTime() {
			return lastOperationTime;
		}

		public void setLastOperationTime(Date lastOperationTime) {
			this.lastOperationTime = lastOperationTime;
		}

		public String getOperationPerson() {
			return operationPerson;
		}

		public void setOperationPerson(String operationPerson) {
			this.operationPerson = operationPerson;
		}

		public String getTaskSwitch() {
			return taskSwitch;
		}

		public void setTaskSwitch(String taskSwitch) {
			this.taskSwitch = taskSwitch;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public BigDecimal getFreeTimesDay() {
			return freeTimesDay;
		}

		public void setFreeTimesDay(BigDecimal freeTimesDay) {
			this.freeTimesDay = freeTimesDay;
		}

		public BigDecimal getOneTimeScore() {
			return oneTimeScore;
		}

		public void setOneTimeScore(BigDecimal oneTimeScore) {
			this.oneTimeScore = oneTimeScore;
		}

		public int getDrawMax() {
			return drawMax;
		}

		public void setDrawMax(int drawMax) {
			this.drawMax = drawMax;
		}
		
		public List<PhiGameConfig> getPhiGameConfigs() {
			return phiGameConfigs;
		}

		public String getType() {
			return type;
		}

		public void setPhiGameConfigs(List<PhiGameConfig> phiGameConfigs) {
			this.phiGameConfigs = phiGameConfigs;
		}
		
		public void setType(String type) {
			this.type = type;
		}


}
