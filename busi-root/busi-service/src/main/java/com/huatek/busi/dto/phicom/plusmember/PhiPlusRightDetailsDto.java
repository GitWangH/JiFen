package com.huatek.busi.dto.phicom.plusmember;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import com.huatek.frame.core.page.DataPage;


public class PhiPlusRightDetailsDto {
	
    private Long id;
    private String maxscorevalue;
    private String minscorevalue;
    private String scorevalue1;
    private String scorevalue1quantity;
    private String scorevalue2;
    private String scorevalue2quantity;
    private String scorevalue3;
    private String scorevalue3quantity;
    private String scorevalue4;
    private String scorevalue4quantity;
    private String scorevalue5;
    private String scorevalue5quantity;
    private String scorevalue6;
    private String scorevalue6quantity;
    private String scorevalue7;
    private String scorevalue7quantity;
    private String starttime;
    private int  scoreOrMutiply;
    private Long plusRightId;
    
    /**
	 * 默认构造器
	 */
	public PhiPlusRightDetailsDto(){}
	
	/**
	 * 使用model数据库模型，构造DTO
	 * 
	 * @param model
	 */
	/*public PhiPlusRightDetailsDto(PhiPlusRightDetails model) {
			this.plusRightDetailsId = model.getPlusRightDetailsId();
			this.maxscorevalue = model.getMaxscorevalue();
			this.minscorevalue = model.getMinscorevalue();
			this.scorevalue1 = model.getScorevalue1();
			this.scorevalue1quantity = model.getScorevalue1quantity();
			this.scorevalue2 = model.getScorevalue2();
			this.scorevalue2quantity = model.getScorevalue2quantity();
			this.scorevalue3 = model.getScorevalue3();
			this.scorevalue3quantity = model.getScorevalue3quantity();
			this.scorevalue4 = model.getScorevalue4();
			this.scorevalue4quantity = model.getScorevalue4quantity();
			this.scorevalue5 = model.getScorevalue5();
			this.scorevalue5quantity = model.getScorevalue5quantity();
			this.scorevalue6 = model.getScorevalue6();
			this.scorevalue6quantity = model.getScorevalue6quantity();
			this.scorevalue7 = model.getScorevalue7();
			this.scorevalue7quantity = model.getScorevalue7quantity();
			this.starttime = model.getStarttime();
			this.scoreOrMutiply = model.getScoreOrMutiply();
			this.plusRightId = model.getPlusRightId();
	}*/
	 
     /**
	 * 生成getter，setter 访问器
	 */

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    public void setMaxscorevalue(String maxscorevalue){
        this.maxscorevalue = maxscorevalue;
    }
      

	public String getMaxscorevalue(){
        return this.maxscorevalue;
    }
      
    public void setMinscorevalue(String minscorevalue){
        this.minscorevalue = minscorevalue;
    }
      
    public String getMinscorevalue(){
        return this.minscorevalue;
    }
      
    public void setScorevalue1(String scorevalue1){
        this.scorevalue1 = scorevalue1;
    }
      
    public String getScorevalue1(){
        return this.scorevalue1;
    }
      
    public void setScorevalue1quantity(String scorevalue1quantity){
        this.scorevalue1quantity = scorevalue1quantity;
    }
      
    public String getScorevalue1quantity(){
        return this.scorevalue1quantity;
    }
      
    public void setScorevalue2(String scorevalue2){
        this.scorevalue2 = scorevalue2;
    }
      
    public String getScorevalue2(){
        return this.scorevalue2;
    }
      
    public void setScorevalue2quantity(String scorevalue2quantity){
        this.scorevalue2quantity = scorevalue2quantity;
    }
      
    public String getScorevalue2quantity(){
        return this.scorevalue2quantity;
    }
      
    public void setScorevalue3(String scorevalue3){
        this.scorevalue3 = scorevalue3;
    }
      
    public String getScorevalue3(){
        return this.scorevalue3;
    }
      
    public void setScorevalue3quantity(String scorevalue3quantity){
        this.scorevalue3quantity = scorevalue3quantity;
    }
      
    public String getScorevalue3quantity(){
        return this.scorevalue3quantity;
    }
      
    public void setScorevalue4(String scorevalue4){
        this.scorevalue4 = scorevalue4;
    }
      
    public String getScorevalue4(){
        return this.scorevalue4;
    }
      
    public void setScorevalue4quantity(String scorevalue4quantity){
        this.scorevalue4quantity = scorevalue4quantity;
    }
      
    public String getScorevalue4quantity(){
        return this.scorevalue4quantity;
    }
      
    public void setScorevalue5(String scorevalue5){
        this.scorevalue5 = scorevalue5;
    }
      
    public String getScorevalue5(){
        return this.scorevalue5;
    }
      
    public void setScorevalue5quantity(String scorevalue5quantity){
        this.scorevalue5quantity = scorevalue5quantity;
    }
      
    public String getScorevalue5quantity(){
        return this.scorevalue5quantity;
    }
      
    public void setScorevalue6(String scorevalue6){
        this.scorevalue6 = scorevalue6;
    }
      
    public String getScorevalue6(){
        return this.scorevalue6;
    }
      
    public void setScorevalue6quantity(String scorevalue6quantity){
        this.scorevalue6quantity = scorevalue6quantity;
    }
      
    public String getScorevalue6quantity(){
        return this.scorevalue6quantity;
    }
      
    public void setScorevalue7(String scorevalue7){
        this.scorevalue7 = scorevalue7;
    }
      
    public String getScorevalue7(){
        return this.scorevalue7;
    }
      
    public void setScorevalue7quantity(String scorevalue7quantity){
        this.scorevalue7quantity = scorevalue7quantity;
    }
      
    public String getScorevalue7quantity(){
        return this.scorevalue7quantity;
    }
      
    public int getScoreOrMutiply() {
		return scoreOrMutiply;
	}

	public void setScoreOrMutiply(int scoreOrMutiply) {
		this.scoreOrMutiply = scoreOrMutiply;
	}

	public void setPlusRightId(Long plusRightId){
        this.plusRightId = plusRightId;
    }
      
    public Long getPlusRightId(){
        return this.plusRightId;
    }

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
    
      
    
    /**
	 * 
	 * @param 构造分页
	 * @return
	 */
	/*public static DataPage<PhiPlusRightDetailsDto> transToDtoPage(DataPage<PhiPlusRightDetails> dataPage) {
		List<PhiPlusRightDetailsDto> dtos = new ArrayList<PhiPlusRightDetailsDto>();
		if (dataPage != null && dataPage.getContent() != null
				&& dataPage.getContent().size() > 0) {
			for (PhiPlusRightDetails m : dataPage.getContent()) {
				dtos.add(new PhiPlusRightDetailsDto(m));
			}
		}
		DataPage<PhiPlusRightDetailsDto> dtoPage = new DataPage<PhiPlusRightDetailsDto>();
		dtoPage.setContent(dtos);
		dtoPage.setPage(dataPage.getPage());
		dtoPage.setPageSize(dataPage.getPageSize());
		dtoPage.setTotalPage(dataPage.getTotalPage());
		dtoPage.setTotalRows(dataPage.getTotalRows());
		return dtoPage;
	}*/
	
	/**
	 * 
	 * 将 model 集合转为 dto 集合
	 * @param 
	 * @return
	 */
	/*public static List<PhiPlusRightDetailsDto> transToDtoList(List<PhiPlusRightDetails> datas) {
		List<PhiPlusRightDetailsDto> dtos =new ArrayList<PhiPlusRightDetailsDto>();
		for(PhiPlusRightDetails newDatas : datas){
			PhiPlusRightDetailsDto dto = new PhiPlusRightDetailsDto(newDatas);
			dtos.add(dto);
		}
		return dtos;
	}*/
}