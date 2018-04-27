package com.huatek.busi.service.impl.external;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.quality.BusiQualityRawMaterialInspectionDao;
import com.huatek.busi.dto.external.ExternalResponse;
import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.model.quality.BusiQualityRawMaterialInspection;
import com.huatek.busi.service.external.BusiQualityExternalService;
import com.huatek.busi.service.quality.BusiQualityRawMaterialInspectionService;
import com.huatek.cmd.dto.CmdMachineDto;
import com.huatek.cmd.service.CmdMachineService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.service.FwOrgService;

/**
 * 接口 原材料检测
 * @author eli_cui
 */
@Service("busiQualityExternalRawMaterialInspection")
@Transactional
public class BusiQualityExternalRawMaterialInspection implements BusiQualityExternalService{
	
	@Autowired
	private BusiQualityRawMaterialInspectionService service;
	
	@Autowired
	private BusiQualityRawMaterialInspectionDao dao;
	
	@Autowired
	private CmdMachineService cmdService;
	
	@Autowired
	private FwOrgService orgService;
	
	@Override
	public ExternalResponse receiveData(String busiType, String appKey, String data, Date timestamp) {
		JSONObject dataJson = null;
		int operateType = 0;
		CmdMachineDto cmdMachineDto = null;
		BusiQualityRawMaterialInspectionDto dto = null;
		BusiQualityRawMaterialInspection entity = null;
		Long tenantId = null;
		try {
			dataJson = JSONObject.fromObject(data); 
			operateType = dataJson.getInt("operateType");
			//设备注册信息
			cmdMachineDto = cmdService.getCmdMachineDtoByAppKey(appKey);
			//判断设备注册信息
			if(cmdMachineDto == null || cmdMachineDto.getOrgId() == null){
				return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "获取注册信息出错，请检查APPKey。");
			}
			//判断接收字段数量  
			if(!ExternalUtil.checkFieldCountByNameAndCount("busiQualityExternalRawMaterialInspection", dataJson.size())){
				return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "接收参数，数量不符合规则。请检查原材料检测传输字段是否过多或过少。");
			}
		} catch (Exception e) {
			return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "参数解析失败！，" + e.toString());
		}
		
		//新增、修改、删除。
		try {
			switch (operateType) {
			//新增
			case Constant.OPERATION_INT_TYPE_ADD :
				entity = dao.findBusiQualityRawMaterialInspectionByUkey(dataJson.getString("ukey"));
				if(entity != null && entity.getIsDelete().intValue() == Constant.DELETE_STATUS_NOT_DELETED){
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "原材料检测数据已存在不允许重复添加");
				} else {
					//租户id
					tenantId = orgService.getOrgById(cmdMachineDto.getOrgId()).getTenantId();
					dto = (BusiQualityRawMaterialInspectionDto) dataJson.toBean(dataJson, BusiQualityRawMaterialInspectionDto.class);
					dto.setOrgId(cmdMachineDto.getOrgId());
					dto.setTenantId(tenantId);
					dto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
					dto.setAppKey(appKey);
					dto.setIsQualitySupervisionBureau(Constant.Quality.QUALITY_SUPERVISION_BUREAU_0);
					dto.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_NO));
					service.saveBusiQualityRawMaterialInspectionDto(dto);
				}
				break;
			//修改
			case Constant.OPERATION_INT_TYPE_UPDATE :
				entity = dao.findBusiQualityRawMaterialInspectionByUkey(dataJson.getString("ukey"));
				if(entity == null){
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "未查询到要修改的数据。");
				}
				if(Integer.valueOf(entity.getDisposeState()).intValue() != Constant.Quality.STATUS_DEAL_NO ){
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "推送数据已在处理或处理完成，不允许修改。");
				}
				dto = (BusiQualityRawMaterialInspectionDto) dataJson.toBean(dataJson, BusiQualityRawMaterialInspectionDto.class);
				if(entity.getIsQualitySupervisionBureau().intValue() == Constant.Quality.QUALITY_SUPERVISION_BUREAU_0 || entity.getIsQualitySupervisionBureau() == null){
					BeanCopy.getInstance().mapIgnoreNull(dto, entity);
				} else if(entity.getIsQualitySupervisionBureau().intValue() == Constant.Quality.QUALITY_SUPERVISION_BUREAU_1){
					BeanCopy.getInstance().mapIgnoreNull(dto, entity);
					entity.setIsQualitySupervisionBureau(Constant.Quality.QUALITY_SUPERVISION_BUREAU_2);//2已传给质监局并做修改
				}
				dao.saveOrUpdateBusiQualityRawMaterialInspection(entity);
				break;
			//删除
			case Constant.OPERATION_INT_TYPE_DELETE :
				entity = dao.findBusiQualityRawMaterialInspectionByUkey(dataJson.getString("ukey"));
				if(entity != null){
					if(Integer.valueOf(entity.getDisposeState()).intValue() != Constant.Quality.STATUS_DEAL_NO ){
						return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "数据已在处理或处理完成，不允许删除");
					} else {
						entity.setIsDelete(1);
						dao.saveOrUpdateBusiQualityRawMaterialInspection(entity);
					}
				} else {
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "未查询到要删除的数据。");
				}
			default:
				return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "参数 \"operateType\" 校验出错。");
			}
		} catch (Exception e) {
			return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "数据保存出错。");
		}
		
		return ExternalUtil.getExternalResponse(Constant.externalStatusCode.SUCCESS, "");
	}
	
	@Override
	public String getType() {
		return "rawMaterialInspection";
	}
	
	public static void main(String[] args) {
//		Gson gson = new Gson();
//		BusiQualityRawMaterialInspectionDto dto = new BusiQualityRawMaterialInspectionDto();
//		String dataJsonStr = gson.toJson(dto);//【参见2.4】
//		
//		String timestamp = "2017-11-12 00:23:45";
//		
//		String busiParams = dataJsonStr;
//		Map<String,String>signMap = new TreeMap<String,String>();
//		signMap.put("app_key", "XXX_APP_XXX_KEY");//注册时获取的appKey
//	signMap.put("method", " rawMaterialInspection");//业务参数名【参见2.3】
//
//		signMap.put("timestamp", timestamp);
//		signMap.put("data", busiParams);
//		String sign = "2";
//		Map<String,String>requestParamMap = new HashMap<String,String>();
//		requestParamMap.put("timestamp", timestamp);
//		requestParamMap.put("method", "rawMaterialInspection");//业务参数名【参见2.3】
//		requestParamMap.put("app_key", "XXX_APP_XXX_KEY");//注册时获取的APPKEY
//		requestParamMap.put("sign", sign != null ? sign : "");
//		requestParamMap.put("data", busiParams);
		
		Map a = new HashMap<String, String>();
		a.put("timestamp", "2017-11-12 00:23:45");
		a.put("33333", "2017-11-12 00:23:45");
		System.out.println(JSONObject.fromObject(a));
	}
}
