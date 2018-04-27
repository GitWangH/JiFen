package com.huatek.busi.dto.phicom.member;

import java.util.Date;

public class PhiMemberAddressDto {
	
	/*会员收货地址id*/
	private Long id;
	
	/** @Fields member_id : 会员id *//*
	private PhiMemberDto phiMember;*/
  
	private Long memberId;

	private String country;
    private String province;
    private String city;
    private String area;
    private String street;
    private String addressDetail;
    private String tel;
    private String name;
    private String zipCode;
    private String defaultState;
    private String day;
    private String time;
    private String  createTime;
	/**@Fields  UId:  斐讯云账户id */
	private int  UId;
	
	private String HarvestAddress;
	
	
    public String getHarvestAddress() {
		return HarvestAddress;
	}


	public void setHarvestAddress(String harvestAddress) {
		HarvestAddress = harvestAddress;
	}


	/**
	 * 默认构造器
	 */
	public PhiMemberAddressDto(){}
	
	
/*	/**
	 * 使用model数据库模型，构造DTO
	 * 
	 * @param model
	 *//*
	public PhiMemberAddressDto(PhiMemberAddress model) {
			this.memberId = model.getMemberId();
			this.addressId = model.getAddressId();
			this.country = model.getCountry();
			this.province = model.getProvince();
			this.city = model.getCity();
			this.area = model.getArea();
			this.street = model.getStreet();
			this.addressDetail = model.getAddressDetail();
			this.tel = model.getTel();
			this.name = model.getName();
			this.zipCode = model.getZipCode();
			this.defaultState = model.getDefaultState();
			this.day = model.getDay();
			this.time = model.getTime();
	}*/
	 
     public int getUId() {
		return UId;
	}


	public void setUId(int uId) {
		UId = uId;
	}

/**
	 * 生成getter，setter 访问器
	 */
	 public Long getId() {
			return id;
		}


	public void setId(Long id) {
		this.id = id;
	}

    public Long getMemberId() {
		return memberId;
	}


	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

/*    public void setMemberId(Long memberId){
        this.memberId = memberId;
    }

	public Long getMemberId(){
        return this.memberId;
    }*/
      
    public void setCountry(String country){
        this.country = country;
    }
      
    public String getCountry(){
        return this.country;
    }
      
    public void setProvince(String province){
        this.province = province;
    }
      
    public String getProvince(){
        return this.province;
    }
      
    public void setCity(String city){
        this.city = city;
    }
      
    public String getCity(){
        return this.city;
    }
      
    public void setArea(String area){
        this.area = area;
    }
      
    public String getArea(){
        return this.area;
    }
      
    public void setStreet(String street){
        this.street = street;
    }
      
    public String getStreet(){
        return this.street;
    }
      
    public void setAddressDetail(String addressDetail){
        this.addressDetail = addressDetail;
    }
      
    public String getAddressDetail(){
        return this.addressDetail;
    }
      
    public void setTel(String tel){
        this.tel = tel;
    }
      
    public String getTel(){
        return this.tel;
    }
      
    public void setName(String name){
        this.name = name;
    }
      
    public String getName(){
        return this.name;
    }
      
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
      
    public String getZipCode(){
        return this.zipCode;
    }
      

    public void setDay(String day){
        this.day = day;
    }
      
    public String getDay(){
        return this.day;
    }
      
    public void setTime(String time){
        this.time = time;
    }
      
    public String getTime(){
        return this.time;
    }


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getDefaultState() {
		return defaultState;
	}


	public void setDefaultState(String defaultState) {
		this.defaultState = defaultState;
	}




	

    
/*	public PhiMemberDto getPhiMember() {
		return phiMember;
	}


	public void setPhiMember(PhiMemberDto phiMember) {
		this.phiMember = phiMember;
	}
      */
    
/*    /**
	 * 
	 * @param 构造分页
	 * @return
	 *//*
	public static DataPage<PhiMemberAddressDto> transToDtoPage(DataPage<PhiMemberAddress> dataPage) {
		List<PhiMemberAddressDto> dtos = new ArrayList<PhiMemberAddressDto>();
		if (dataPage != null && dataPage.getContent() != null
				&& dataPage.getContent().size() > 0) {
			for (PhiMemberAddress m : dataPage.getContent()) {
				dtos.add(new PhiMemberAddressDto(m));
			}
		}
		DataPage<PhiMemberAddressDto> dtoPage = new DataPage<PhiMemberAddressDto>();
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
	public static List<PhiMemberAddressDto> transToDtoList(List<PhiMemberAddress> datas) {
		List<PhiMemberAddressDto> dtos =new ArrayList<PhiMemberAddressDto>();
		for(PhiMemberAddress newDatas : datas){
			PhiMemberAddressDto dto = new PhiMemberAddressDto(newDatas);
			dtos.add(dto);
		}
		return dtos;
	}*/
}