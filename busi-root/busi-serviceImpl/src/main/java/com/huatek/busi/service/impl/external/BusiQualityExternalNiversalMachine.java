package com.huatek.busi.service.impl.external;

import java.util.Date;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.quality.BusiQualityUniversalMachineDao;
import com.huatek.busi.dao.quality.BusiQualityUniversalPressMachineParentDao;
import com.huatek.busi.dto.external.ExternalResponse;
import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalMachineDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalPressMachineParentDto;
import com.huatek.busi.model.quality.BusiQualityUniversalMachine;
import com.huatek.busi.model.quality.BusiQualityUniversalPressMachineParent;
import com.huatek.busi.service.external.BusiQualityExternalService;
import com.huatek.busi.service.quality.BusiQualityUniversalMachineService;
import com.huatek.busi.service.quality.BusiQualityUniversalPressMachineParentService;
import com.huatek.cmd.dto.CmdMachineDto;
import com.huatek.cmd.service.CmdMachineService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.service.FwOrgService;

/**
 * 接口 万能机
 * @author eli_cui
 *
 */
@Service("busiQualityExternalNiversalMachine")
@Transactional
public class BusiQualityExternalNiversalMachine implements BusiQualityExternalService{
	@Autowired
	private BusiQualityUniversalMachineService machinService;
	
	@Autowired
	private BusiQualityUniversalMachineDao machinDao;

	@Autowired
	private BusiQualityUniversalPressMachineParentService parentService;
	
	@Autowired
	private BusiQualityUniversalPressMachineParentDao parentDao;
	
	@Autowired
	private CmdMachineService cmdService;
	
	@Autowired
	private FwOrgService orgService;

	
	@Override
	public ExternalResponse receiveData(String busiType, String appKey,	String data, Date timestamp) {
		JSONObject dataJson = null;
		int operateType = 0;
		CmdMachineDto cmdMachineDto = null;
		Long tenantId = null;
		
		//万能机
		BusiQualityUniversalMachine machinEntity = null;
		BusiQualityUniversalMachineDto machinDto = null;
		//万能机与压力机  - 父表
		BusiQualityUniversalPressMachineParent parentEntity = null;
		BusiQualityUniversalPressMachineParentDto parentDto = null;
		
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
			if(!ExternalUtil.checkFieldCountByNameAndCount("busiQualityExternalNiversalMachine", dataJson.size())){
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
				machinEntity = machinDao.findBusiQualityUniversalMachineByUkey(dataJson.getString("ukey"));
				if(machinEntity != null && machinEntity.getIsDelete().intValue() == Constant.DELETE_STATUS_NOT_DELETED){
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "万能机数据已存在不允许重复添加");
				} else {
					//租户id
					tenantId = orgService.getOrgById(cmdMachineDto.getOrgId()).getTenantId();
					machinDto = (BusiQualityUniversalMachineDto) dataJson.toBean(dataJson, BusiQualityUniversalMachineDto.class);
					machinDto.setOrgId(cmdMachineDto.getOrgId());
					machinDto.setTenantId(tenantId);
					machinDto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
					machinDto.setAppKey(appKey);
					//获取父表中需要存的数据
					parentDto = ExternalUtil.getBusiQualityUniversalPressMachineParentDtoBy(0, machinDto, null);
					//处理状态
					machinDto.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_NO));
					machinDto.setIsQualitySupervisionBureau(Constant.Quality.QUALITY_SUPERVISION_BUREAU_0);
					//保存主表，和父表的数据 
					machinService.saveBusiQualityUniversalMachineDto(machinDto, parentDto);
				}
				break;
			//修改
			case Constant.OPERATION_INT_TYPE_UPDATE :
				machinEntity = machinDao.findBusiQualityUniversalMachineByUkey(dataJson.getString("ukey"));
				if(machinEntity == null){
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "未查询到要修改的数据。");
				}
				
				
				parentEntity =  parentDao.findBusiQualityUniversalPressMachineParentByUniversalMachineId(machinEntity.getId()); //machinEntity.getBusiQualityUniversalPressMachineParent();
				
				
				if(Integer.valueOf(machinEntity.getDisposeState()).intValue() != Constant.Quality.STATUS_DEAL_NO ){
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "推送数据已在处理或处理完成，不允许修改。");
				}
				machinDto = (BusiQualityUniversalMachineDto) dataJson.toBean(dataJson, BusiQualityUniversalMachineDto.class);
				if(machinEntity.getIsQualitySupervisionBureau().intValue() == Constant.Quality.QUALITY_SUPERVISION_BUREAU_0 || machinEntity.getIsQualitySupervisionBureau() == null){
					BeanCopy.getInstance().mapIgnoreNull(machinDto, machinEntity);
					BeanCopy.getInstance().addOnlyFields(new String[]{"tenantId","sampleNumber","testPersion","testTime","descUrl","unqualifiedDescription","status"}).mapIgnoreNull(machinEntity, parentEntity);
				} else if (machinEntity.getIsQualitySupervisionBureau().intValue() == Constant.Quality.QUALITY_SUPERVISION_BUREAU_1){
					BeanCopy.getInstance().mapIgnoreNull(machinDto, machinEntity);
					BeanCopy.getInstance().addOnlyFields(new String[]{"tenantId","sampleNumber","testPersion","testTime","descUrl","unqualifiedDescription","status"}).mapIgnoreNull(machinEntity, parentEntity);
					machinEntity.setIsQualitySupervisionBureau(Constant.Quality.QUALITY_SUPERVISION_BUREAU_2);//2已传给质监局并做修改
				}
				machinDao.saveOrUpdateBusiQualityUniversalMachine(machinEntity);
				parentDao.saveOrUpdateBusiQualityUniversalPressMachineParent(parentEntity);
				break;
			//删除
			case Constant.OPERATION_INT_TYPE_DELETE  :
				machinEntity = machinDao.findBusiQualityUniversalMachineByUkey(dataJson.getString("ukey"));
				if(machinEntity != null){
					if(Integer.valueOf(machinEntity.getDisposeState()).intValue() != Constant.Quality.STATUS_DEAL_NO ){
						return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "推送数据已在处理或处理完成，不允许删除。");
					} else {
						machinEntity.setIsDelete(1);
						machinDao.saveOrUpdateBusiQualityUniversalMachine(machinEntity);
						parentEntity =  parentDao.findBusiQualityUniversalPressMachineParentByUniversalMachineId(machinEntity.getId()); //machinEntity.getBusiQualityUniversalPressMachineParent();
						parentEntity.setIsDelete(1);
						parentDao.saveOrUpdateBusiQualityUniversalPressMachineParent(parentEntity);
					}
				} else {
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "未查询到要删除的数据。");
				}
				break;
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
		return "niversalMachine";
	}
}
