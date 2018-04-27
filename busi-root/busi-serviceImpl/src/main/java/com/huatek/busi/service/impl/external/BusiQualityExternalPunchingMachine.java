package com.huatek.busi.service.impl.external;

import java.util.Date;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.quality.BusiQualityPressMachineDao;
import com.huatek.busi.dao.quality.BusiQualityUniversalPressMachineParentDao;
import com.huatek.busi.dto.external.ExternalResponse;
import com.huatek.busi.dto.quality.BusiQualityPressMachineDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalMachineDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalPressMachineParentDto;
import com.huatek.busi.model.quality.BusiQualityPressMachine;
import com.huatek.busi.model.quality.BusiQualityUniversalPressMachineParent;
import com.huatek.busi.service.external.BusiQualityExternalService;
import com.huatek.busi.service.quality.BusiQualityPressMachineService;
import com.huatek.busi.service.quality.BusiQualityUniversalPressMachineParentService;
import com.huatek.cmd.dto.CmdMachineDto;
import com.huatek.cmd.service.CmdMachineService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.service.FwOrgService;

/**
 * 接口 压力机
 * @author eli_cui
 *
 */
@Service("busiQualityExternalPunchingMachine")
@Transactional
public class BusiQualityExternalPunchingMachine implements BusiQualityExternalService{
	@Autowired
	private BusiQualityPressMachineService pressService;
	
	@Autowired
	private BusiQualityPressMachineDao pressDao;
	
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
		//压力机 实体
		BusiQualityPressMachine pressEntity = null;
		//压力机 dto
		BusiQualityPressMachineDto pressDto = null;
		//万能机与压力机  - 父表 - 实体
		BusiQualityUniversalPressMachineParent parentEntity = null;
		//万能机与压力机  - 父表 - dto
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
			if(!ExternalUtil.checkFieldCountByNameAndCount("busiQualityExternalPunchingMachine", dataJson.size())){
				return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "接收参数，数量不符合规则。请检查原材料检测传输字段是否过多或过少。");
			}
		} catch (Exception e) {
			return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "参数解析失败！，" + e.toString());
		}
		
		//新增、修改、删除。
		try {
			switch (operateType) {
			// 新增
			case Constant.OPERATION_INT_TYPE_ADD :
				pressEntity = pressDao.findBusiQualityPressMachineByUkey(dataJson.getString("ukey"));
				if(pressEntity != null && pressEntity.getIsDelete().intValue() == Constant.DELETE_STATUS_NOT_DELETED){
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "压力机数据已存在不允许重复添加");
				} else {
					//租户id
					tenantId = orgService.getOrgById(cmdMachineDto.getOrgId()).getTenantId();
					pressDto = (BusiQualityPressMachineDto) dataJson.toBean(dataJson, BusiQualityPressMachineDto.class);
					pressDto.setOrgId(cmdMachineDto.getOrgId());
					pressDto.setTenantId(tenantId);
					pressDto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
					pressDto.setAppKey(appKey);
					pressDto.setIsQualitySupervisionBureau(Constant.Quality.QUALITY_SUPERVISION_BUREAU_0);
					//获取父表中需要存的数据
					parentDto = ExternalUtil.getBusiQualityUniversalPressMachineParentDtoBy(1, null, pressDto);
					//处理状态
					pressDto.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_NO));
					//保存主表，和父表的数据 
					pressService.saveBusiQualityPressMachineDto(pressDto, parentDto);
				}
				break;
			//修改
			case Constant.OPERATION_INT_TYPE_UPDATE :
				pressEntity = pressDao.findBusiQualityPressMachineByUkey(dataJson.getString("ukey"));
				if(pressEntity == null){
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "未查询到要修改的数据。");
				}
				
				
				parentEntity = parentDao.findBusiQualityUniversalPressMachineParentByBusiQualityPressMachineId(pressEntity.getId());//pressEntity.getBusiQualityUniversalPressMachineParent();
				
				
				if(Integer.valueOf(pressEntity.getDisposeState()).intValue() != Constant.Quality.STATUS_DEAL_NO ){
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "推送数据已在处理或处理完成，不允许修改。");
				}
				pressDto = (BusiQualityPressMachineDto) dataJson.toBean(dataJson, BusiQualityPressMachineDto.class);
				if(pressEntity.getIsQualitySupervisionBureau().intValue() == Constant.Quality.QUALITY_SUPERVISION_BUREAU_0 || pressEntity.getIsQualitySupervisionBureau() == null){
					BeanCopy.getInstance().mapIgnoreNull(pressDto, pressEntity);
					BeanCopy.getInstance().addOnlyFields(new String[]{"tenantId","sampleNumber","testPersion","testTime","descUrl","unqualifiedDescription","status"}).mapIgnoreNull(pressEntity, parentEntity);
				} else if (pressEntity.getIsQualitySupervisionBureau().intValue() == Constant.Quality.QUALITY_SUPERVISION_BUREAU_1){
					BeanCopy.getInstance().mapIgnoreNull(pressDto, pressEntity);
					BeanCopy.getInstance().addOnlyFields(new String[]{"tenantId","sampleNumber","testPersion","testTime","descUrl","unqualifiedDescription","status"}).mapIgnoreNull(pressEntity, parentEntity);
					pressEntity.setIsQualitySupervisionBureau(Constant.Quality.QUALITY_SUPERVISION_BUREAU_2);//2已传给质监局并做修改
				}
				pressDao.saveOrUpdateBusiQualityPressMachine(pressEntity);
				parentDao.saveOrUpdateBusiQualityUniversalPressMachineParent(parentEntity);
				break;
			//删除
			case Constant.OPERATION_INT_TYPE_DELETE :
				pressEntity = pressDao.findBusiQualityPressMachineByUkey(dataJson.getString("ukey"));
				if(pressEntity != null){
					if(Integer.valueOf(pressEntity.getDisposeState()).intValue() != Constant.Quality.STATUS_DEAL_NO ){
						return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "推送数据已在处理或处理完成，不允许删除。");
					} else {
						pressEntity.setIsDelete(1);
						pressDao.saveOrUpdateBusiQualityPressMachine(pressEntity);
						parentEntity = parentDao.findBusiQualityUniversalPressMachineParentByBusiQualityPressMachineId(pressEntity.getId());//pressEntity.getBusiQualityUniversalPressMachineParent();
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
		return "punchingMachine";
	}

}
