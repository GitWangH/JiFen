package com.huatek.busi.service.impl.external;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dto.external.ExternalResponse;
import com.huatek.busi.dto.external.ExternalWithDataResponse;
import com.huatek.busi.dto.quality.BusiQualityPressMachineDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalMachineDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalPressMachineParentDto;

public class ExternalUtil {
	
	
	public static ExternalWithDataResponse getExternalWithDataResponse(int status, String msg,Object jsonData){
		ExternalWithDataResponse externalWithDataResponse = new ExternalWithDataResponse(status, msg,jsonData);
		return externalWithDataResponse;
	}
	/**
	 * 获取成功或失败状态
	 * @param status 状态 0 成功 1失败
	 * @param 
	 * @author eli_cui
	 * @return
	 */
	public static ExternalResponse getExternalResponse(int status, String msg){
		ExternalResponse ExternalResponse = new ExternalResponse(status, msg);
		return ExternalResponse;
	}
	
	
	/**
	 * 获取成功或失败状态
	 * @param status 状态 0 成功 1失败
	 * @param 
	 * @author eli_cui
	 * @return
	 */
	public static ExternalWithDataResponse getExternalWithDataResponse(int status, String msg){
		ExternalWithDataResponse externalWithDataResponse = new ExternalWithDataResponse(status, msg,"");
		return externalWithDataResponse;
	}
	
	/**
	 * 根据接口名称和count 确定接口接收的字段数量是否与字段数量枚举中的值相同
	 * 相同则返回true 不同返回false
	 * @param name
	 * @param count
	 * @return
	 */
	public static boolean checkFieldCountByNameAndCount(String name, int count){
		if(ExternalFieldCount.getCountByName(name).getCount() == count){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获取 BusiQualityUniversalPressMachineParentDto 
	 * 万能机 和 压力机的 父表
	 * @param type = 0 万能机 type == 1 压力机
	 * @return
	 */
	public static BusiQualityUniversalPressMachineParentDto getBusiQualityUniversalPressMachineParentDtoBy(int type, BusiQualityUniversalMachineDto universalDto, BusiQualityPressMachineDto pressDto){
		BusiQualityUniversalPressMachineParentDto dto = new BusiQualityUniversalPressMachineParentDto();
		switch (type) {
		case 0 :
			dto.setTenantId(universalDto.getTenantId());
			dto.setCreateTime(dateFormatString(new Date()));
			dto.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_NO));
			dto.setSampleNumber(universalDto.getSampleNumber());
			dto.setTestType("0");//实验类型 万能机
			dto.setTestPersion(universalDto.getTestPersion());
			dto.setTestTime(universalDto.getTestTime());
			dto.setDescUrl(universalDto.getDescUrl());
			dto.setUnqualifiedDescription(universalDto.getUnqualifiedDescription());
			dto.setStatus(universalDto.getStatus());
			break;
		case 1 :
			dto.setTenantId(pressDto.getTenantId());
			dto.setCreateTime(dateFormatString(new Date()));
			dto.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_NO));
			dto.setSampleNumber(pressDto.getSampleNumber());
			dto.setSampleName(pressDto.getSampleName());
			dto.setTestType("1");//实验类型 压力机
			dto.setTestTime(pressDto.getExperimentalDate());
			dto.setDescUrl(pressDto.getDescUrl());
			dto.setUnqualifiedDescription(pressDto.getDescription());
			dto.setStatus(pressDto.getStatus());
			break;
		default:
			break;
		}

		return dto;
	}
	
	
	public static String checkFieldValue(JSONObject dataJson){
		return null;
	}
	
	
	private static String dateFormatString(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String str=sdf.format(date); 
		return str;
	}
	
	/**
	 * 接口会传多少个字段 - 数量枚举
	 * 接口数据传输如果有调整 此枚举需要调整相应 count 值
	 * @author eli_cui
	 *
	 */
	 private enum ExternalFieldCount {
		//原材料检测报告
		busiQualityExternalRawMaterialInspection(26, "busiQualityExternalRawMaterialInspection"),
		//万能机
		busiQualityExternalNiversalMachine(65, "busiQualityExternalNiversalMachine"),
		//压力机
		busiQualityExternalPunchingMachine(37, "busiQualityExternalPunchingMachine"),
		//水泥拌合站
		busiQualityExternalCementMixingPlant(51, "busiQualityExternalCementMixingPlant"),
		//水泥拌合站超标
		busiQualityExternalCementMixingStationExceedingStandard(24, "busiQualityExternalCementMixingStationExceedingStandard");
		
		private final int count;
		private final String name;
		private ExternalFieldCount(int count,String name){
	        this.count = count;
			this.name = name;
		}
		private int getCount() {
			return count;
		}
		private String getName() {
			return name;
		}
		
		private static ExternalFieldCount getCountByName(String name){
			for(ExternalFieldCount e : ExternalFieldCount.values()){
				if(name.equals(e.getName())){
					return e;
				}
			}
			return null;
		}
	}
	 
}
