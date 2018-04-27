package com.huatek.busi.dto.phicom.game;

import java.math.BigDecimal;



public class PhiGameConfigDto {
	//gameConfigId
    private Long id;
    private Long gameId;
    private String location;
    private BigDecimal score;
    private String clientShow;
    private BigDecimal prizeRate;
    
    /**
	 * 默认构造器
	 */
	public PhiGameConfigDto(){}
	
	
	 
     /**
	 * 生成getter，setter 访问器
	 */
   /* public void setGameConfigId(Long gameConfigId){
        this.gameConfigId = gameConfigId;
    }
      
    public Long getGameConfigId(){
        return this.gameConfigId;
    }*/
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public void setGameId(Long gameId){
        this.gameId = gameId;
    }
      
	public Long getGameId(){
        return this.gameId;
    }
      
    public void setLocation(String location){
        this.location = location;
    }
      
    public String getLocation(){
        return this.location;
    }
      
  
    public void setClientShow(String clientShow){
        this.clientShow = clientShow;
    }
      
    public String getClientShow(){
        return this.clientShow;
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