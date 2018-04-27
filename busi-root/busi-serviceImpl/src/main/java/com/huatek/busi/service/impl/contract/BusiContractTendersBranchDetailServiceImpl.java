package com.huatek.busi.service.impl.contract;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.contract.BusiContractTendersBranchDetailDao;
import com.huatek.busi.dto.contract.BusiContractTendersBranchDetailDto;
import com.huatek.busi.model.contract.BusiContractTendersBranchDetail;
import com.huatek.busi.service.contract.BusiContractTendersBranchDetailService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractTendersBranchDetailServiceImpl
 * @Description: 分部分项维护服务层接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-08 16:44:00
 * @version: 1.0
 */
@Service("busiContractTendersBranchDetailServiceImpl")
@Transactional
public class BusiContractTendersBranchDetailServiceImpl implements BusiContractTendersBranchDetailService {
	
	@Autowired
	private BusiContractTendersBranchDetailDao busiContractTendersBranchDetailDao;
	
	@Override
	public DataPage<BusiContractTendersBranchDetailDto> getAllBusiContractTendersBranchDetailPage(QueryPage queryPage) {
		DataPage<BusiContractTendersBranchDetail> dataPage = busiContractTendersBranchDetailDao.getAllBusiContractTendersBranchDetail(queryPage);
		DataPage<BusiContractTendersBranchDetailDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiContractTendersBranchDetailDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiContractTendersBranchDetailDto> getAllBusiContractTendersBranchDetailDto() {
		List<BusiContractTendersBranchDetail> entityList = busiContractTendersBranchDetailDao.findAllBusiContractTendersBranchDetail();
		List<BusiContractTendersBranchDetailDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiContractTendersBranchDetailDto.class);
		return dtos;
	}
}