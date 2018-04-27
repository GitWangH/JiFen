package com.huatek.busi.service.impl.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.contract.BusiContractSupervisorContractDetailDao;
import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.contract.BusiContractSupervisorContractDetailDto;
import com.huatek.busi.model.contract.BusiContractSupervisorContractDetail;
import com.huatek.busi.service.contract.BusiContractSupervisorContractDetailService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.frame.util.DateUtil;

/**
 * @ClassName: BusiContractSupervisorContractDetailServiceImpl
 * @Description: 安全(监理)清单管理服务层接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-31 20:16:35
 * @version: 1.0
 */
@Service("busiContractSupervisorContractDetailServiceImpl")
@Transactional
public class BusiContractSupervisorContractDetailServiceImpl implements BusiContractSupervisorContractDetailService {
	
	@Autowired
	private BusiContractSupervisorContractDetailDao busiContractSupervisorContractDetailDao;
	
	/**
	 * 机构对象的机构ID、机构名称转换至Dto对象的数据常量
	 */
	public static final Map<String,String> entityToFields = new HashMap<String,String>();
	static {
		entityToFields.put("busiFwOrg.name", "orgName");
		entityToFields.put("busiFwOrg.id", "orgId");
	}
	
	/**
	 * 查询安全清单列表树数据
	 */
	@Override
	public List<BusiContractSupervisorContractDetailDto> getAllTopLevelBusiContractSupervisorContractDetailDto(QueryPage queryPage) {
		List<QueryParam> paramList = queryPage.getQueryParamList();    	  
  	  	paramList.add(new QueryParam("groupLevel", "long", "=", "1"));
  	    paramList.add(new QueryParam("isDelete", "long", "=", String.valueOf(Constant.DELETE_STATUS_NOT_DELETED)));//未删除
		List<BusiContractSupervisorContractDetail> busiContractSupervisorContractDetailList = busiContractSupervisorContractDetailDao.findAllBusiContractSupervisorContractDetail(queryPage);
		return BeanCopy.getInstance().addFieldMaps(entityToFields).convertList(busiContractSupervisorContractDetailList, BusiContractSupervisorContractDetailDto.class);

	}

	/**
	 * 根据父节点查询子节点数据
	 */
	@Override
	public List<BusiContractSupervisorContractDetailDto> getChildBusiContractSupervisorContractDetailDtoByParentUUID(String parentUUID) {
		List<BusiContractSupervisorContractDetail> busiContractSupervisorContractDetailList = busiContractSupervisorContractDetailDao.findChildBusiContractSupervisorContractDetailDtoByParentUUID(parentUUID);
		return BeanCopy.getInstance().addFieldMaps(entityToFields).convertList(busiContractSupervisorContractDetailList, BusiContractSupervisorContractDetailDto.class);
	}

	/**
	 * 保存安全清单列表数据
	 * @param busiContractSupervisorContractDetailDtoList
	 */
	@Override
	public List<TreeGridAddIdAndUUIDDto> saveTreeGridData(Long orgId, List<BusiContractSupervisorContractDetailDto> busiContractSupervisorContractDetailDtoList)  {
		//根据页面传进来的值设置保存的值信息
				List<BusiContractSupervisorContractDetailDto> addDtoList = null;
				List<BusiContractSupervisorContractDetailDto> updateDtoList = null;
				List<BusiContractSupervisorContractDetailDto> deleteDtoList = null;
				List<BusiContractSupervisorContractDetail> addEntityList = null;
				if(null != busiContractSupervisorContractDetailDtoList && busiContractSupervisorContractDetailDtoList.size() > 0){
					addDtoList = new ArrayList<BusiContractSupervisorContractDetailDto>();
					updateDtoList = new ArrayList<BusiContractSupervisorContractDetailDto>();
					deleteDtoList = new ArrayList<BusiContractSupervisorContractDetailDto>();
					UserInfo currentUser = ThreadLocalClient.get().getOperator();
					for(BusiContractSupervisorContractDetailDto busiContractSupervisorContractDetailDto:busiContractSupervisorContractDetailDtoList){
						busiContractSupervisorContractDetailDto.setOrgId(orgId);
						if(busiContractSupervisorContractDetailDto.getOperation().equals(Constant.OPERATION_TYPE_ADD)){
							busiContractSupervisorContractDetailDto.setCreater(currentUser.getId());// 创建人id
							busiContractSupervisorContractDetailDto.setCreaterName(currentUser.getUserName());// 创建人姓名
							busiContractSupervisorContractDetailDto.setCreateTime(DateUtil.timeFormat.format(new Date()));// 创建时间
							busiContractSupervisorContractDetailDto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);//未删除
							addDtoList.add(busiContractSupervisorContractDetailDto);
						}else if(busiContractSupervisorContractDetailDto.getOperation().equals(Constant.OPERATION_TYPE_UPDATE)){
							
							busiContractSupervisorContractDetailDto.setModifer(currentUser.getId());// 修改人id
							busiContractSupervisorContractDetailDto.setModifierName(currentUser.getUserName());// 修改人姓名
							busiContractSupervisorContractDetailDto.setModifyTime(DateUtil.timeFormat.format(new Date()));// 修改时间
							updateDtoList.add(busiContractSupervisorContractDetailDto);
						}else if(busiContractSupervisorContractDetailDto.getOperation().equals(Constant.OPERATION_TYPE_DELETE)){
							busiContractSupervisorContractDetailDto.setIsDelete(Constant.DELETE_STATUS_DELETED);//已删除
							deleteDtoList.add(busiContractSupervisorContractDetailDto);
						}
					}
				}
				//批量保存
				if(null != addDtoList && addDtoList.size() > 0){//批量保存
					addEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(addDtoList, BusiContractSupervisorContractDetail.class);
					//进行持久化保存
					busiContractSupervisorContractDetailDao.batchSaveBusiContractSupervisorContractDetailList(addEntityList);
				}
				//批量更新
				if(null != updateDtoList && updateDtoList.size() > 0){//批量更新
					List<BusiContractSupervisorContractDetail> updateEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(updateDtoList, BusiContractSupervisorContractDetail.class);
					//进行持久化保存
					busiContractSupervisorContractDetailDao.batchUpdateBusiContractSupervisorContractDetailList(updateEntityList);
				}
				//批量删除
				if(null != deleteDtoList && deleteDtoList.size() > 0){//批量删除
					List<BusiContractSupervisorContractDetail> deleteEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(deleteDtoList, BusiContractSupervisorContractDetail.class);
					//进行持久化保存
					busiContractSupervisorContractDetailDao.batchDeleteBusiContractSupervisorContractDetailList(deleteEntityList);
				}
				if(addEntityList != null){
					List<TreeGridAddIdAndUUIDDto> list = new ArrayList<TreeGridAddIdAndUUIDDto>();
					for(BusiContractSupervisorContractDetail entity : addEntityList){
						TreeGridAddIdAndUUIDDto dto = new TreeGridAddIdAndUUIDDto();
						dto.setId(entity.getId());
						dto.setUuid(entity.getUUID());
						list.add(dto);
					}
					return list;
				} else {
					return null;
				}
		}
	
	/**
	 * 分页查询
	 */
	@Override
	public DataPage<BusiContractSupervisorContractDetailDto> getAllBusiContractSupervisorContractDetailPage(QueryPage queryPage) {
		DataPage<BusiContractSupervisorContractDetail> dataPage = busiContractSupervisorContractDetailDao.getAllBusiContractSupervisorContractDetail(queryPage);
		DataPage<BusiContractSupervisorContractDetailDto> datPageDto = BeanCopy.getInstance().addFieldMaps(BusiContractTendersContractServiceImpl.entityToFields).convertPage(dataPage, BusiContractSupervisorContractDetailDto.class);
		return datPageDto;
	}
	
}
