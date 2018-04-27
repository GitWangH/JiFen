package com.huatek.busi.dto.phicom.plusmember;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import com.huatek.frame.core.page.DataPage;


public class PhiPlusGradeDto {
    private Long plusId;
    private String plusCode;
    private String plusGrade;
    private Long creatorId;
    private Date createTime;
    private String rechargeMoney;
    private String image;
    private String remark;
    private Date validTime;
    
    /**
	 * 默认构造器
	 */
	public PhiPlusGradeDto(){}
	
	/**
	 * 使用model数据库模型，构造DTO
	 * 
	 * @param model
	 *//*
	public PhiPlusGradeDto(PhiPlusGrade model) {
			this.plusId = model.getPlusId();
			this.plusCode = model.getPlusCode();
			this.plusGrade = model.getPlusGrade();
			this.creatorId = model.getCreatorId();
			this.createTime = model.getCreateTime();
			this.rechargeMoney = model.getRechargeMoney();
			this.image = model.getImage();
			this.remark = model.getRemark();
			this.validTime = model.getValidTime();
	}
	 */
     /**
	 * 生成getter，setter 访问器
	 */
    public void setPlusId(Long plusId){
        this.plusId = plusId;
    }
      
    public Long getPlusId(){
        return this.plusId;
    }
      
    public void setPlusCode(String plusCode){
        this.plusCode = plusCode;
    }
      
    public String getPlusCode(){
        return this.plusCode;
    }
      
    public void setPlusGrade(String plusGrade){
        this.plusGrade = plusGrade;
    }
      
    public String getPlusGrade(){
        return this.plusGrade;
    }
      
    public void setCreatorId(Long creatorId){
        this.creatorId = creatorId;
    }
      
    public Long getCreatorId(){
        return this.creatorId;
    }
      
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
      
    public Date getCreateTime(){
        return this.createTime;
    }
      
    public void setRechargeMoney(String rechargeMoney){
        this.rechargeMoney = rechargeMoney;
    }
      
    public String getRechargeMoney(){
        return this.rechargeMoney;
    }
      
    public void setImage(String image){
        this.image = image;
    }
      
    public String getImage(){
        return this.image;
    }
      
    public void setRemark(String remark){
        this.remark = remark;
    }
      
    public String getRemark(){
        return this.remark;
    }
      
    public void setValidTime(Date validTime){
        this.validTime = validTime;
    }
      
    public Date getValidTime(){
        return this.validTime;
    }
      
/*    
    *//**
	 * 
	 * @param 构造分页
	 * @return
	 *//*
	public static DataPage<PhiPlusGradeDto> transToDtoPage(DataPage<PhiPlusGrade> dataPage) {
		List<PhiPlusGradeDto> dtos = new ArrayList<PhiPlusGradeDto>();
		if (dataPage != null && dataPage.getContent() != null
				&& dataPage.getContent().size() > 0) {
			for (PhiPlusGrade m : dataPage.getContent()) {
				dtos.add(new PhiPlusGradeDto(m));
			}
		}
		DataPage<PhiPlusGradeDto> dtoPage = new DataPage<PhiPlusGradeDto>();
		dtoPage.setContent(dtos);
		dtoPage.setPage(dataPage.getPage());
		dtoPage.setPageSize(dataPage.getPageSize());
		dtoPage.setTotalPage(dataPage.getTotalPage());
		dtoPage.setTotalRows(dataPage.getTotalRows());
		return dtoPage;
	}
	
	*//**
	 * 
	 * 将 model 集合转为 dto 集合
	 * @param 
	 * @return
	 *//*
	public static List<PhiPlusGradeDto> transToDtoList(List<PhiPlusGrade> datas) {
		List<PhiPlusGradeDto> dtos =new ArrayList<PhiPlusGradeDto>();
		for(PhiPlusGrade newDatas : datas){
			PhiPlusGradeDto dto = new PhiPlusGradeDto(newDatas);
			dtos.add(dto);
		}
		return dtos;
	}*/
}