package com.huatek.busi.dto.phicom.plusmember;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.huatek.frame.core.page.DataPage;

public class PhiPlusRightDto {
    private Long id;
    
    private String lastoperationtime;
    private String operationperson;
    private String showname;
    private String taskname;
    private String taskremark;
    private String taskswitch;
    private String tasktimetype;
    private String tasktype;
    private Long plusId;
    private Set<PhiPlusRightDetailsDto> plusRightDetail ;
    
    /**
	 * 默认构造器
	 */
	public PhiPlusRightDto(){}
/*	
	/**
	 * 使用model数据库模型，构造DTO
	 * 
	 * @param model
	 *//*
	public PhiPlusRightDto(PhiPlusRight model) {
			this.plusRightId = model.getPlusRightId();
			this.lastoperationtime = model.getLastoperationtime();
			this.operationperson = model.getOperationperson();
			this.showname = model.getShowname();
			this.taskname = model.getTaskname();
			this.taskremark = model.getTaskremark();
			this.taskswitch = model.getTaskswitch();
			this.tasktimetype = model.getTasktimetype();
			this.tasktype = model.getTasktype();
	}*/
	 
     /**
	 * 生成getter，setter 访问器
	 */
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public String getLastoperationtime() {
		return lastoperationtime;
	}

	public void setLastoperationtime(String lastoperationtime) {
		this.lastoperationtime = lastoperationtime;
	}

	public void setOperationperson(String operationperson){
        this.operationperson = operationperson;
    }
      
    public String getOperationperson(){
        return this.operationperson;
    }
      
    public void setShowname(String showname){
        this.showname = showname;
    }
      
    public String getShowname(){
        return this.showname;
    }
      
    public void setTaskname(String taskname){
        this.taskname = taskname;
    }
      
    public String getTaskname(){
        return this.taskname;
    }
      
    public void setTaskremark(String taskremark){
        this.taskremark = taskremark;
    }
      
    public String getTaskremark(){
        return this.taskremark;
    }
      
    public void setTaskswitch(String taskswitch){
        this.taskswitch = taskswitch;
    }
      
    public String getTaskswitch(){
        return this.taskswitch;
    }
      
    public void setTasktimetype(String tasktimetype){
        this.tasktimetype = tasktimetype;
    }
      
    public String getTasktimetype(){
        return this.tasktimetype;
    }
      
    public void setTasktype(String tasktype){
        this.tasktype = tasktype;
    }
      
    public String getTasktype(){
        return this.tasktype;
    }

	public Long getPlusId() {
		return plusId;
	}

	public void setPlusId(Long plusId) {
		this.plusId = plusId;
	}

	public Set<PhiPlusRightDetailsDto> getPlusRightDetail() {
		return plusRightDetail;
	}

	public void setPlusRightDetail(Set<PhiPlusRightDetailsDto> plusRightDetail) {
		this.plusRightDetail = plusRightDetail;
	}
      
	
    
    /**
	 * 
	 * @param 构造分页
	 * @return
	 */
	/*public static DataPage<PhiPlusRightDto> transToDtoPage(DataPage<PhiPlusRight> dataPage) {
		List<PhiPlusRightDto> dtos = new ArrayList<PhiPlusRightDto>();
		if (dataPage != null && dataPage.getContent() != null
				&& dataPage.getContent().size() > 0) {
			for (PhiPlusRight m : dataPage.getContent()) {
				dtos.add(new PhiPlusRightDto(m));
			}
		}
		DataPage<PhiPlusRightDto> dtoPage = new DataPage<PhiPlusRightDto>();
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
	/*public static List<PhiPlusRightDto> transToDtoList(List<PhiPlusRight> datas) {
		List<PhiPlusRightDto> dtos =new ArrayList<PhiPlusRightDto>();
		for(PhiPlusRight newDatas : datas){
			PhiPlusRightDto dto = new PhiPlusRightDto(newDatas);
			dtos.add(dto);
		}
		return dtos;
	}*/
}