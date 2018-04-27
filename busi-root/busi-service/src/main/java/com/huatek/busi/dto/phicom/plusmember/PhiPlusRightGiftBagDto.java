package com.huatek.busi.dto.phicom.plusmember;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.huatek.busi.dto.phicom.score.PhiScoreConfigRuleDto;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBagDetails;
import com.huatek.frame.core.page.DataPage;


public class PhiPlusRightGiftBagDto {
	
    private Long id;
    private String giftBagType;
    private String giftCategroy;
    private String rightName;
    private Date sendTime;
    private String giftPackageMoney;
    private String  isValidate;
    private String remark;
    private Date lastoperationtime;
    private String operationperson;
    private Long plusId;
    
    private Set<PhiPlusRightGiftBagDetailsDto> plusRightGiftBagDetails;
   
    /**
	 * 默认构造器
	 */
	public PhiPlusRightGiftBagDto(){}
	
	/**
	 * 使用model数据库模型，构造DTO
	 * 
	 * @param model
	 */
	/*public PhiPlusRightGiftBagDto(PhiPlusRightGiftBag model) {
			this.giftBagId = model.getGiftBagId();
			this.giftBagType = model.getGiftBagType();
			this.giftCategroy = model.getGiftCategroy();
			this.rightName = model.getRightName();
			this.sendTime = model.getSendTime();
			this.giftPackageMoney = model.getGiftPackageMoney();
			this.isValidate = model.getIsValidate();
			this.remark = model.getRemark();
			this.lastoperationtime = model.getLastoperationtime();
			this.operationperson = model.getOperationperson();
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

      
    public void setGiftBagType(String giftBagType){
        this.giftBagType = giftBagType;
    }
      

	public String getGiftBagType(){
        return this.giftBagType;
    }
      
    public void setGiftCategroy(String giftCategroy){
        this.giftCategroy = giftCategroy;
    }
      
    public String getGiftCategroy(){
        return this.giftCategroy;
    }
      
    public void setRightName(String rightName){
        this.rightName = rightName;
    }
      
    public String getRightName(){
        return this.rightName;
    }
      
    public void setSendTime(Date sendTime){
        this.sendTime = sendTime;
    }
      
    public Date getSendTime(){
        return this.sendTime;
    }
      
    public void setGiftPackageMoney(String giftPackageMoney){
        this.giftPackageMoney = giftPackageMoney;
    }
      
    public String getGiftPackageMoney(){
        return this.giftPackageMoney;
    }
      
    public String getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}

	public void setRemark(String remark){
        this.remark = remark;
    }
      
    public String getRemark(){
        return this.remark;
    }
      
    public void setLastoperationtime(Date lastoperationtime){
        this.lastoperationtime = lastoperationtime;
    }
      
    public Date getLastoperationtime(){
        return this.lastoperationtime;
    }
      
    public void setOperationperson(String operationperson){
        this.operationperson = operationperson;
    }
      
    public String getOperationperson(){
        return this.operationperson;
    }

	public Long getPlusId() {
		return plusId;
	}

	public void setPlusId(Long plusId) {
		this.plusId = plusId;
	}

	public Set<PhiPlusRightGiftBagDetailsDto> getPlusRightGiftBagDetails() {
		return plusRightGiftBagDetails;
	}

	public void setPlusRightGiftBagDetails(Set<PhiPlusRightGiftBagDetailsDto> plusRightGiftBagDetails) {
		this.plusRightGiftBagDetails = plusRightGiftBagDetails;
	}



    
    /**
	 * 
	 * @param 构造分页
	 * @return
	 */
/*	public static DataPage<PhiPlusRightGiftBagDto> transToDtoPage(DataPage<PhiPlusRightGiftBag> dataPage) {
		List<PhiPlusRightGiftBagDto> dtos = new ArrayList<PhiPlusRightGiftBagDto>();
		if (dataPage != null && dataPage.getContent() != null
				&& dataPage.getContent().size() > 0) {
			for (PhiPlusRightGiftBag m : dataPage.getContent()) {
				dtos.add(new PhiPlusRightGiftBagDto(m));
			}
		}
		DataPage<PhiPlusRightGiftBagDto> dtoPage = new DataPage<PhiPlusRightGiftBagDto>();
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
	/*public static List<PhiPlusRightGiftBagDto> transToDtoList(List<PhiPlusRightGiftBag> datas) {
		List<PhiPlusRightGiftBagDto> dtos =new ArrayList<PhiPlusRightGiftBagDto>();
		for(PhiPlusRightGiftBag newDatas : datas){
			PhiPlusRightGiftBagDto dto = new PhiPlusRightGiftBagDto(newDatas);
			dtos.add(dto);
		}
		return dtos;
	}*/
}