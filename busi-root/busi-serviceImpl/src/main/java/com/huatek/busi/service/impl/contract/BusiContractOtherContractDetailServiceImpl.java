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
import com.huatek.busi.dao.contract.BusiContractOtherContractDetailDao;
import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.contract.BusiContractOtherContractDetailDto;
import com.huatek.busi.model.contract.BusiContractOtherContractDetail;
import com.huatek.busi.service.contract.BusiContractOtherContractDetailService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.frame.util.DateUtil;

/**
 * @ClassName: BusiContractOtherContractDetailServiceImpl
 * @Description: 其它合同清单管理服务层接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-31 17:20:35
 * @version: 1.0
 */
@Service("busiContractOtherContractDetailServiceImpl")
@Transactional
public class BusiContractOtherContractDetailServiceImpl implements BusiContractOtherContractDetailService {
	
	@Autowired
	private BusiContractOtherContractDetailDao busiContractOtherContractDetailDao;
	
	/**
	 * 机构对象的机构ID、机构名称转换至Dto对象的数据常量
	 */
	public static final Map<String,String> entityToFields = new HashMap<String,String>();
	static {
		entityToFields.put("busiFwOrg.name", "orgName");
		entityToFields.put("busiFwOrg.id", "orgId");
	}
	
	@Override
	public DataPage<BusiContractOtherContractDetailDto> getAllBusiContractOtherContractDetailPage(QueryPage queryPage) {
		DataPage<BusiContractOtherContractDetail> dataPage = busiContractOtherContractDetailDao.getAllBusiContractOtherContractDetail(queryPage);
		DataPage<BusiContractOtherContractDetailDto> datPageDto = BeanCopy.getInstance().addFieldMaps(BusiContractTendersContractServiceImpl.entityToFields).convertPage(dataPage, BusiContractOtherContractDetailDto.class);
		return datPageDto;
	}
	
	/**
	 * 查询所有顶级节点
	 * @param queryPage
	 * @return
	 */
	@Override
	public List<BusiContractOtherContractDetailDto> getAllTopLevelBusiContractOtherContractDetailDto(QueryPage queryPage){
		List<QueryParam> paramList = queryPage.getQueryParamList();    	  
  	  	paramList.add(new QueryParam("groupLevel", "long", "=", "1"));
  	    paramList.add(new QueryParam("isDelete", "long", "=", String.valueOf(Constant.DELETE_STATUS_NOT_DELETED)));//未删除
		List<BusiContractOtherContractDetail> busiContractOtherContractDetailList = busiContractOtherContractDetailDao.findAllBusiContractOtherContractDetail(queryPage);
		return BeanCopy.getInstance().addFieldMaps(entityToFields).convertList(busiContractOtherContractDetailList, BusiContractOtherContractDetailDto.class);
	}
	
	/**
	 * 根据父节点的UUID查询子节点数据
	 * @param parentUUID
	 * @return
	 */
	@Override
	public List<BusiContractOtherContractDetailDto> getChildBusiContractOtherContractDetailDtoByParentUUID(String parentUUID){
		List<BusiContractOtherContractDetail> busiContractOtherContractDetailList = busiContractOtherContractDetailDao.findChildBusiContractOtherContractDetailByParentUUID(parentUUID);
		return BeanCopy.getInstance().addFieldMaps(entityToFields).convertList(busiContractOtherContractDetailList, BusiContractOtherContractDetailDto.class);
	}
	/**
	 * 保存其它合同清单列表数据
	 * @param busiContractOtherContractDetailDtoList
	 */
	@Override
	public List<TreeGridAddIdAndUUIDDto> saveTreeGridData(Long orgId, List<BusiContractOtherContractDetailDto> busiContractOtherContractDetailDtoList){
		//根据页面传进来的值设置保存的值信息
		List<BusiContractOtherContractDetailDto> addDtoList = null;
		List<BusiContractOtherContractDetailDto> updateDtoList = null;
		List<BusiContractOtherContractDetailDto> deleteDtoList = null;
		List<BusiContractOtherContractDetail> addEntityList = null;
		if(null != busiContractOtherContractDetailDtoList && busiContractOtherContractDetailDtoList.size() > 0){
			addDtoList = new ArrayList<BusiContractOtherContractDetailDto>();
			updateDtoList = new ArrayList<BusiContractOtherContractDetailDto>();
			deleteDtoList = new ArrayList<BusiContractOtherContractDetailDto>();
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			for(BusiContractOtherContractDetailDto busiContractOtherContractDetailDto:busiContractOtherContractDetailDtoList){
				busiContractOtherContractDetailDto.setOrgId(orgId);
				if(busiContractOtherContractDetailDto.getOperation().equals(Constant.OPERATION_TYPE_ADD)){
					busiContractOtherContractDetailDto.setCreater(currentUser.getId());// 创建人id
					busiContractOtherContractDetailDto.setCreaterName(currentUser.getUserName());// 创建人姓名
					busiContractOtherContractDetailDto.setCreateTime(DateUtil.timeFormat.format(new Date()));// 创建时间
					busiContractOtherContractDetailDto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);//未删除
					addDtoList.add(busiContractOtherContractDetailDto);
				}else if(busiContractOtherContractDetailDto.getOperation().equals(Constant.OPERATION_TYPE_UPDATE)){
					busiContractOtherContractDetailDto.setModifer(currentUser.getId());// 修改人id
					busiContractOtherContractDetailDto.setModifierName(currentUser.getUserName());// 修改人姓名
					busiContractOtherContractDetailDto.setModifyTime(DateUtil.timeFormat.format(new Date()));// 修改时间
					updateDtoList.add(busiContractOtherContractDetailDto);
				}else if(busiContractOtherContractDetailDto.getOperation().equals(Constant.OPERATION_TYPE_DELETE)){
					busiContractOtherContractDetailDto.setIsDelete(Constant.DELETE_STATUS_DELETED);//已删除
					deleteDtoList.add(busiContractOtherContractDetailDto);
				}
			}
		}
		//批量保存
		if(null != addDtoList && addDtoList.size() > 0){//批量保存
			addEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(addDtoList, BusiContractOtherContractDetail.class);
			//进行持久化保存
			busiContractOtherContractDetailDao.batchSaveBusiContractOtherContractDetailList(addEntityList);
		}
		//批量更新
		if(null != updateDtoList && updateDtoList.size() > 0){//批量更新
			List<BusiContractOtherContractDetail> updateEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(updateDtoList, BusiContractOtherContractDetail.class);
			//进行持久化保存
			busiContractOtherContractDetailDao.batchUpdateBusiContractOtherContractDetailList(updateEntityList);
		}
		//批量删除
		if(null != deleteDtoList && deleteDtoList.size() > 0){//批量删除
			List<BusiContractOtherContractDetail> deleteEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(deleteDtoList, BusiContractOtherContractDetail.class);
			//进行持久化保存
			busiContractOtherContractDetailDao.batchDeleteBusiContractOtherContractDetailList(deleteEntityList);
		}
		
		if(addEntityList != null){
			List<TreeGridAddIdAndUUIDDto> list = new ArrayList<TreeGridAddIdAndUUIDDto>();
			for(BusiContractOtherContractDetail entity : addEntityList){
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
}
