package com.huatek.busi.dto.phicom.order;

import java.util.List;


public class PhiLogisticDto {
    private Long id;
    private Long orderId;
    private Long orderinfoId;
    private Long memberId;
    private String comname;
    private String com;
    private String nu;
    private String sender;
    private String sendPhone;
    private String sendTime;
    private String remark;
    //物流详情的dto
    List<Mylogisticdto> mylogisticdto;
    
    /**
	 * 默认构造器
	 */
	public PhiLogisticDto(){}
	
	 
     /**
	 * 生成getter，setter 访问器
	 */
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }
      
    public Long getOrderId(){
        return this.orderId;
    }
      
    public void setOrderinfoId(Long orderinfoId){
        this.orderinfoId = orderinfoId;
    }
      
    public Long getOrderinfoId(){
        return this.orderinfoId;
    }
      
    public void setMemberId(Long memberId){
        this.memberId = memberId;
    }
      
    public Long getMemberId(){
        return this.memberId;
    }
      
      
    public void setSender(String sender){
        this.sender = sender;
    }
      
    public String getSender(){
        return this.sender;
    }
      
    public void setSendPhone(String sendPhone){
        this.sendPhone = sendPhone;
    }
      
    public String getSendPhone(){
        return this.sendPhone;
    }
      
    public void setSendTime(String sendTime){
        this.sendTime = sendTime;
    }
      
    public String getSendTime(){
        return this.sendTime;
    }


	public String getComname() {
		return comname;
	}


	public void setComname(String comname) {
		this.comname = comname;
	}


	public String getCom() {
		return com;
	}


	public void setCom(String com) {
		this.com = com;
	}


	public String getNu() {
		return nu;
	}


	public void setNu(String nu) {
		this.nu = nu;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public List<Mylogisticdto> getMylogisticdto() {
		return mylogisticdto;
	}


	public void setMylogisticdto(List<Mylogisticdto> mylogisticdto) {
		this.mylogisticdto = mylogisticdto;
	}


    
	
	/**
	 * 
	 * 将 model 集合转为 dto 集合
	 * @param 
	 * @return
	 */
	/*public static List<PhiLogisticDto> transToDtoList(List<PhiLogistic> datas) {
		List<PhiLogisticDto> dtos =new ArrayList<PhiLogisticDto>();
		for(PhiLogistic newDatas : datas){
			PhiLogisticDto dto = new PhiLogisticDto(newDatas);
			dtos.add(dto);
		}
		return dtos;
	}*/
}