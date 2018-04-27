package com.huatek.busi.dto.phicom.member;



public class PhiMemberDetailDto {
	/* member_detail_id  会员详情id*/
    private Long id;
    private Long memberId;
    private String job;
    private String registerEmail;
    private String micro;
    private String qq;
    private String wechat;
    
    /**
	 * 默认构造器
	 */
	public PhiMemberDetailDto(){}
	
/*	/**
	 * 使用model数据库模型，构造DTO
	 * 
	 * @param model
	 *//*
	public PhiMemberDetailDto(PhiMemberDetail model) {
			this.memberDetailId = model.getMemberDetailId();
			this.memberId = model.getMemberId();
			this.job = model.getJob();
			this.registerEmail = model.getRegisterEmail();
			this.micro = model.getMicro();
			this.qq = model.getQq();
			this.wechat = model.getWechat();
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
	
    public void setMemberId(Long memberId){
        this.memberId = memberId;
    }
      
    public Long getMemberId(){
        return this.memberId;
    }
      
    public void setJob(String job){
        this.job = job;
    }
      
    public String getJob(){
        return this.job;
    }
      
    public void setRegisterEmail(String registerEmail){
        this.registerEmail = registerEmail;
    }
      
    public String getRegisterEmail(){
        return this.registerEmail;
    }
      
    public void setMicro(String micro){
        this.micro = micro;
    }
      
    public String getMicro(){
        return this.micro;
    }
      
    public void setQq(String qq){
        this.qq = qq;
    }
      
    public String getQq(){
        return this.qq;
    }
      
    public void setWechat(String wechat){
        this.wechat = wechat;
    }
      
    public String getWechat(){
        return this.wechat;
    }

    
  /*  *//**
	 * 
	 * @param 构造分页
	 * @return
	 *//*
	public static DataPage<PhiMemberDetailDto> transToDtoPage(DataPage<PhiMemberDetail> dataPage) {
		List<PhiMemberDetailDto> dtos = new ArrayList<PhiMemberDetailDto>();
		if (dataPage != null && dataPage.getContent() != null
				&& dataPage.getContent().size() > 0) {
			for (PhiMemberDetail m : dataPage.getContent()) {
				dtos.add(new PhiMemberDetailDto(m));
			}
		}
		DataPage<PhiMemberDetailDto> dtoPage = new DataPage<PhiMemberDetailDto>();
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
	public static List<PhiMemberDetailDto> transToDtoList(List<PhiMemberDetail> datas) {
		List<PhiMemberDetailDto> dtos =new ArrayList<PhiMemberDetailDto>();
		for(PhiMemberDetail newDatas : datas){
			PhiMemberDetailDto dto = new PhiMemberDetailDto(newDatas);
			dtos.add(dto);
		}
		return dtos;
	}*/
}