package com.huatek.busi.dto.phicom.plusmember;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBag;
import com.huatek.frame.core.page.DataPage;


public class PhiPlusRightGiftBagDetailsDto {
	
    private Long detailId;
    //private Long giftBagId;
    private Long cpnsWayId;
    private String cpnsQuantity;
	private int cpnsMoney;
	private Long  giftbagId;
	/** @Fields cpnsName ： 优惠券名称*/
	private String cpnsName;
	
    public int getCpnsMoney() {
		return cpnsMoney;
	}

	public void setCpnsMoney(int cpnsMoney) {
		this.cpnsMoney = cpnsMoney;
	}

	/**
	 * 默认构造器
	 */
	public PhiPlusRightGiftBagDetailsDto(){}
	
	/**
	 * 使用model数据库模型，构造DTO
	 * 
	 * @param model
	 */
/*	public PhiPlusRightGiftBagDetailsDto(PhiPlusRightGiftBagDetails model) {
			this.plusRightGiftBagDetailsId = model.getPlusRightGiftBagDetailsId();
			this.giftBagId = model.getGiftBagId();
			this.cpnsWayId = model.getCpnsWayId();
			this.cpnsQuantity = model.getCpnsQuantity();
	}*/
	 
     /**
	 * 生成getter，setter 访问器
	 */
 
	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
      
/*    public void setGiftBagId(Long giftBagId){
        this.giftBagId = giftBagId;
    }
      
	public Long getGiftBagId(){
        return this.giftBagId;
    }*/
      
    public void setCpnsWayId(Long cpnsWayId){
        this.cpnsWayId = cpnsWayId;
    }
      
    

	public Long getCpnsWayId(){
        return this.cpnsWayId;
    }
      
    public void setCpnsQuantity(String cpnsQuantity){
        this.cpnsQuantity = cpnsQuantity;
    }
      
    public String getCpnsQuantity(){
        return this.cpnsQuantity;
    }

	public Long getGiftbagId() {
		return giftbagId;
	}

	public void setGiftbagId(Long giftbagId) {
		this.giftbagId = giftbagId;
	}

	public String getCpnsName() {
		return cpnsName;
	}

	public void setCpnsName(String cpnsName) {
		this.cpnsName = cpnsName;
	}
  
    
    /**
	 * 
	 * @param 构造分页
	 * @return
	 */
/*	public static DataPage<PhiPlusRightGiftBagDetailsDto> transToDtoPage(DataPage<PhiPlusRightGiftBagDetails> dataPage) {
		List<PhiPlusRightGiftBagDetailsDto> dtos = new ArrayList<PhiPlusRightGiftBagDetailsDto>();
		if (dataPage != null && dataPage.getContent() != null
				&& dataPage.getContent().size() > 0) {
			for (PhiPlusRightGiftBagDetails m : dataPage.getContent()) {
				dtos.add(new PhiPlusRightGiftBagDetailsDto(m));
			}
		}
		DataPage<PhiPlusRightGiftBagDetailsDto> dtoPage = new DataPage<PhiPlusRightGiftBagDetailsDto>();
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
/*	public static List<PhiPlusRightGiftBagDetailsDto> transToDtoList(List<PhiPlusRightGiftBagDetails> datas) {
		List<PhiPlusRightGiftBagDetailsDto> dtos =new ArrayList<PhiPlusRightGiftBagDetailsDto>();
		for(PhiPlusRightGiftBagDetails newDatas : datas){
			PhiPlusRightGiftBagDetailsDto dto = new PhiPlusRightGiftBagDetailsDto(newDatas);
			dtos.add(dto);
		}
		return dtos;
	}*/
}