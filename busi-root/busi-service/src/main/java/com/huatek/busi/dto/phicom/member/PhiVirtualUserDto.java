package com.huatek.busi.dto.phicom.member;



public class PhiVirtualUserDto {
    private Long id;
    private String userName;
    private String mobile;
    
    /**
	 * 默认构造器
	 */
	public PhiVirtualUserDto(){}
	
	/**
	 * 使用model数据库模型，构造DTO
	 * 
	 * @param model
	 */
/*	public PhiVirtualUserDto(PhiVirtualUser model) {
			this.virtualUserId = model.getVirtualUserId();
			this.userName = model.getUserName();
			this.mobile = model.getMobile();
	}*/
	 
     /**
	 * 生成getter，setter 访问器
	 */
	
/*    public void setVirtualUserId(Long virtualUserId){
        this.virtualUserId = virtualUserId;
    }
      
    public Long getVirtualUserId(){
        return this.virtualUserId;
    }*/
      
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public void setUserName(String userName){
        this.userName = userName;
    }
      
	public String getUserName(){
        return this.userName;
    }
      
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
      
    public String getMobile(){
        return this.mobile;
    }
      
    
    /**
	 * 
	 * @param 构造分页
	 * @return
	 */
	/*public static DataPage<PhiVirtualUserDto> transToDtoPage(DataPage<PhiVirtualUser> dataPage) {
		List<PhiVirtualUserDto> dtos = new ArrayList<PhiVirtualUserDto>();
		if (dataPage != null && dataPage.getContent() != null
				&& dataPage.getContent().size() > 0) {
			for (PhiVirtualUser m : dataPage.getContent()) {
				dtos.add(new PhiVirtualUserDto(m));
			}
		}
		DataPage<PhiVirtualUserDto> dtoPage = new DataPage<PhiVirtualUserDto>();
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
	/*public static List<PhiVirtualUserDto> transToDtoList(List<PhiVirtualUser> datas) {
		List<PhiVirtualUserDto> dtos =new ArrayList<PhiVirtualUserDto>();
		for(PhiVirtualUser newDatas : datas){
			PhiVirtualUserDto dto = new PhiVirtualUserDto(newDatas);
			dtos.add(dto);
		}
		return dtos;
	}*/
}