package com.huatek.busi.service.impl.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.report.BusiReportDao;
import com.huatek.busi.dto.report.ReporChartDto;
import com.huatek.busi.dto.report.SeriesData;
import com.huatek.busi.model.report.QualityDistributionChart;
import com.huatek.busi.service.report.BusiReportService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiReportAction
 * @Description: 报表 服务层接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-15 13:55:35
 * @version: 1.0
 * @memuInfo [报表管理]
 */
@Service("busiReportService")
@Transactional
public class BusiReportServiceImpl implements BusiReportService{
	
	@Autowired
	private BusiReportDao busiReportDao;
	
	/**
	 * 不合格数据统计
	 */
	@Override
	public ReporChartDto getUnqualifiedStatisticsData(QueryPage queryPage) {
		List<Map<String, Object>> resultDataMapList =  busiReportDao.findUnqualifiedStatisticsData(queryPage);
		ReporChartDto reporChartDto = null;
		if (resultDataMapList != null && resultDataMapList.size() > 0 && resultDataMapList.size() == 1) {
			reporChartDto = new ReporChartDto();
			reporChartDto.setLegendData(new String[]{"水稳拌合站","水泥拌合站","沥青拌合站","原材料","实验室","路面摊压","张拉压浆","质量巡检","施工报检"});
			Map<String, Object> resultDataMap = resultDataMapList.get(0);
			List<SeriesData> seriesDataList = new ArrayList<SeriesData>();
			seriesDataList.add(new SeriesData("水稳拌合站",conventNull(resultDataMap.get("WATER_STATION_UNQUALIFIED_COUNT"))));	
			seriesDataList.add(new SeriesData("水泥拌合站",conventNull(resultDataMap.get("CEMENT_STATION_UNQUALIFIED_COUNT"))));
			seriesDataList.add(new SeriesData("沥青拌合站",conventNull(resultDataMap.get("ASPHALT_STATION_UNQUALIFIED_COUNT"))));
			seriesDataList.add(new SeriesData("原材料",conventNull(resultDataMap.get("RAW_MATERIAL_UNQUALIFIED_COUNT"))));
			seriesDataList.add(new SeriesData("实验室",conventNull(resultDataMap.get("PRESS_MACHINE_UNQUALIFIED_COUNT"))));
			seriesDataList.add(new SeriesData("路面摊压",conventNull(resultDataMap.get("SPREADER_ROLLER_UNQUALIFIED_COUNT"))));
			seriesDataList.add(new SeriesData("张拉压浆",conventNull(resultDataMap.get("PRESTRESSED_TENSION_UNQUALIFIED_COUNT"))));
			seriesDataList.add(new SeriesData("质量巡检",conventNull(resultDataMap.get("ROUTING_INSPECTION_UNQUALIFIED_COUNT"))));
			seriesDataList.add(new SeriesData("施工报检",conventNull(resultDataMap.get("CONSTRUCTION_INSPECTION_UNQUALIFIED_COUNT"))));
			reporChartDto.setSeriesDataList(seriesDataList);
		}
		return reporChartDto;
	}
	
	@Override
	public ReporChartDto getOverStandardChartData(String dataSource, QueryPage queryPage) {
		List<Map<String, Object>> resultDataMapList = busiReportDao.findOverStandardChartData(queryPage, this.getConditionByDataSource(dataSource), UserUtil.getUser().getOrgId());
		ReporChartDto reporChartDto = null;
		if (resultDataMapList != null && resultDataMapList.size() > 0 && resultDataMapList.size() == 1) {
			reporChartDto = new ReporChartDto();
			reporChartDto.setLegendData(new String[]{"按时已解决","按时未解决","超时已解决","超时未解决"});
			Map<String, Object> resultDataMap = resultDataMapList.get(0);
			List<SeriesData> seriesDataList = new ArrayList<SeriesData>();
			seriesDataList.add(new SeriesData("按时已解决",conventNull(resultDataMap.get("RESOLVED_ON_TIME"))));	
			seriesDataList.add(new SeriesData("按时未解决",conventNull(resultDataMap.get("NOT_RESOLVED_ON_TIME"))));
			seriesDataList.add(new SeriesData("超时已解决",conventNull(resultDataMap.get("TIMEOUT_RESOLVED"))));
			seriesDataList.add(new SeriesData("超时未解决",conventNull(resultDataMap.get("TIMEOUT_NOT_RESOLVED"))));
			reporChartDto.setSeriesDataList(seriesDataList);
		}
		return reporChartDto;
	}
	
	
	@Override
	public ReporChartDto getUnitEngineeringInspectionUnqualified(QueryPage queryPage) {
		List<Map<String, Object>> resultDataMapList = busiReportDao.findUnitEngineeringInspectionUnqualified(queryPage, UserUtil.getUser().getOrgId());
		ReporChartDto reporChartDto = null;
		if (resultDataMapList != null && resultDataMapList.size() > 0 && resultDataMapList.size() == 1) {
			reporChartDto = new ReporChartDto();
			List<String> legendDataList = new ArrayList<String>();
			Map<String, Object> resultDataMap = resultDataMapList.get(0);
			String[] legendData = String.valueOf(resultDataMap.get("NAME")).split(",");
			String[] seriesDataArray = String.valueOf(resultDataMap.get("COUNT")).split(",");
			List<SeriesData> seriesDataList = new ArrayList<SeriesData>();
			for(int i = 0; i < legendData.length; i++){
				seriesDataList.add(new SeriesData(legendData[i], seriesDataArray[i]));
			}
			reporChartDto.setLegendData(legendData);
			reporChartDto.setSeriesDataList(seriesDataList);
		}
		return reporChartDto;
	}
	
	@Override
	public ReporChartDto getQualitySeverityData(QueryPage queryPage) {
		List<Map<String, Object>> resultDataMapList = busiReportDao.findQualitySeverityDataData(queryPage, UserUtil.getUser().getOrgId());
		ReporChartDto reporChartDto = null;
		List<Integer> emergency = null;//叠加图表 紧急
		List<Integer> general = null; //叠加图表 普通
		List<Integer> slight = null; // 轻微 
		if (resultDataMapList != null && resultDataMapList.size() > 0 && resultDataMapList.size() == 1) {
			reporChartDto = new ReporChartDto();
			emergency = new ArrayList<Integer>();
			general = new ArrayList<Integer>();
			slight = new ArrayList<Integer>();
			Map<String, Object> resultDataMap = resultDataMapList.get(0);
			slight.add(Integer.valueOf(resultDataMap.get("B0") == null ? "0" : resultDataMap.get("B0").toString()));
			slight.add(Integer.valueOf(resultDataMap.get("Z0") == null ? "0" : resultDataMap.get("Z0").toString()));
			slight.add(Integer.valueOf(resultDataMap.get("S0") == null ? "0" : resultDataMap.get("S0").toString()));
			general.add(Integer.valueOf(resultDataMap.get("B1") == null ? "0" : resultDataMap.get("B1").toString()));
			general.add(Integer.valueOf(resultDataMap.get("Z1") == null ? "0" : resultDataMap.get("Z1").toString()));
			general.add(Integer.valueOf(resultDataMap.get("S1") == null ? "0" : resultDataMap.get("S1").toString()));
			emergency.add(Integer.valueOf(resultDataMap.get("B2") == null ? "0" : resultDataMap.get("B2").toString()));
			emergency.add(Integer.valueOf(resultDataMap.get("Z2") == null ? "0" : resultDataMap.get("Z2").toString()));
			emergency.add(Integer.valueOf(resultDataMap.get("S2") == null ? "0" : resultDataMap.get("S2").toString()));
			reporChartDto.setEmergency(emergency);
			reporChartDto.setGeneral(general);
			reporChartDto.setSlight(slight);
		}
		return reporChartDto;
	}
	
	
	
	@Override
	public ReporChartDto getQualityDistributionChart(QueryPage queryPage) {
		ReporChartDto resultDto = null;
		List<QualityDistributionChart> yuanCaiLiao = busiReportDao.findRawMaterial(queryPage, UserUtil.getUser().getOrgId());
		List<QualityDistributionChart> banChengPin = busiReportDao.findPartiallyPreparedProducts(queryPage, UserUtil.getUser().getOrgId());
		List<QualityDistributionChart> shiGongBaoJian = busiReportDao.findConstructionProcess(queryPage, UserUtil.getUser().getOrgId());
		List<QualityDistributionChart> diSanFangJianCe = busiReportDao.findThirdParty(queryPage, UserUtil.getUser().getOrgId());
		Map<String, QualityDistributionChart> yuanCaiLiaoMap = null;
		Map<String, QualityDistributionChart> banChengPinMap = null;
		Map<String, QualityDistributionChart> shiGongBaoJianMap = null;
		Map<String, QualityDistributionChart> diSanFangJianCeMap = null;
		/**X轴数据*/
		List<String> xAxisData = getOrgNameByList(0,yuanCaiLiao, banChengPin, shiGongBaoJian, diSanFangJianCe);
		/**总数*/
		//List<Integer> seriesSum = null;
		/**原材料*/
		List<Integer> seriesRawMaterial = null;
		/**半成品*/
		List<Integer> seriesPartiallyPreparedProducts = null;
		/**施工过程*/
		List<Integer> seriesConstructionProcess = null;
		/**第三方检测*/
		List<Integer> seriesThirdParty = null;
//		int elementYuanCaiLiao = 0;
//		int elementBanchengPin = 0;
//		int elementShiGongJianCe = 0;
//		int elementDiSanFang = 0;
		if(null != xAxisData && xAxisData.size() != 0){
			//返回前台的dto
			resultDto = new ReporChartDto();
			yuanCaiLiaoMap = getQualityDistributionChartMap(yuanCaiLiao, 0);
			banChengPinMap = getQualityDistributionChartMap(banChengPin, 0);
			shiGongBaoJianMap = getQualityDistributionChartMap(shiGongBaoJian, 0);
			diSanFangJianCeMap = getQualityDistributionChartMap(diSanFangJianCe, 0);
			//seriesSum = new ArrayList<Integer>();
			seriesRawMaterial = new ArrayList<Integer>();
			seriesPartiallyPreparedProducts = new ArrayList<Integer>();
			seriesConstructionProcess = new ArrayList<Integer>();
			seriesThirdParty = new ArrayList<Integer>();
			for(int i = 0; i < xAxisData.size(); i++){
				//原材料
				seriesRawMaterial.add(getSumByMap(yuanCaiLiaoMap, xAxisData.get(i), 0));
				//半成品
				seriesPartiallyPreparedProducts.add(getSumByMap(banChengPinMap, xAxisData.get(i), 0));
				//施工过程
				seriesConstructionProcess.add(getSumByMap(shiGongBaoJianMap, xAxisData.get(i), 0));
				//第三方检测
				seriesThirdParty.add(getSumByMap(diSanFangJianCeMap, xAxisData.get(i), 0));
				//总数
				//seriesSum.add(elementYuanCaiLiao + elementBanchengPin + elementShiGongJianCe + elementDiSanFang);
				
			}
			resultDto.setxAxisData(xAxisData);
			//resultDto.setSeriesSum(seriesSum);
			resultDto.setSeriesRawMaterial(seriesRawMaterial);
			resultDto.setSeriesPartiallyPreparedProducts(seriesPartiallyPreparedProducts);
			resultDto.setSeriesConstructionProcess(seriesConstructionProcess);
			resultDto.setSeriesThirdParty(seriesThirdParty);
		}	
		return resultDto;
	}
	
	@Override
	public ReporChartDto getQualityProblem(QueryPage queryPage) {
		ReporChartDto resultDto = null;
		List<QualityDistributionChart> yuanCaiLiao = busiReportDao.findRawMaterialQualityProblemChart(queryPage, UserUtil.getUser().getOrgId());
		List<QualityDistributionChart> banChengPin = busiReportDao.findPartiallyPreparedProductsQualityProblemChart(queryPage, UserUtil.getUser().getOrgId());
		List<QualityDistributionChart> shiGongBaoJian = busiReportDao.findConstructionProcessQualityProblemChart(queryPage, UserUtil.getUser().getOrgId());
		List<QualityDistributionChart> diSanFangJianCe = busiReportDao.findThirdPartyQualityProblemChart(queryPage, UserUtil.getUser().getOrgId());
		Map<String, QualityDistributionChart> yuanCaiLiaoMap = null;
		Map<String, QualityDistributionChart> banChengPinMap = null;
		Map<String, QualityDistributionChart> shiGongBaoJianMap = null;
		Map<String, QualityDistributionChart> diSanFangJianCeMap = null;
		/**X轴数据*/
		List<String> xAxisData = getOrgNameByList(0,yuanCaiLiao, banChengPin, shiGongBaoJian, diSanFangJianCe);
		List<Integer> yijiejue = null;
		List<Integer> weijiejue = null;
		if(null != xAxisData && xAxisData.size() != 0){
			//返回前台的dto
			resultDto = new ReporChartDto();
			yuanCaiLiaoMap = getQualityDistributionChartMap(yuanCaiLiao, 0);
			banChengPinMap = getQualityDistributionChartMap(banChengPin, 0);
			shiGongBaoJianMap = getQualityDistributionChartMap(shiGongBaoJian, 0);
			diSanFangJianCeMap = getQualityDistributionChartMap(diSanFangJianCe, 0);
			yijiejue = new ArrayList<Integer>();
			weijiejue = new ArrayList<Integer>();
			
			for(int i = 0; i < xAxisData.size(); i++){
				yijiejue.add(getSumByMap(yuanCaiLiaoMap, xAxisData.get(i), 1) + getSumByMap(banChengPinMap, xAxisData.get(i), 1) + getSumByMap(shiGongBaoJianMap, xAxisData.get(i), 1)+ getSumByMap(diSanFangJianCeMap, xAxisData.get(i), 1) );
				weijiejue.add(getSumByMap(yuanCaiLiaoMap, xAxisData.get(i), 2) + getSumByMap(banChengPinMap, xAxisData.get(i), 2) + getSumByMap(shiGongBaoJianMap, xAxisData.get(i), 2)+ getSumByMap(diSanFangJianCeMap, xAxisData.get(i), 2) );
			}
		}
		resultDto.setxAxisData(xAxisData);
		resultDto.setYijiejue(yijiejue);
		resultDto.setWeijiejue(weijiejue);
		return resultDto;
	}
	
	@Override
	public ReporChartDto getQualityTrends(QueryPage queryPage) {
		ReporChartDto resultDto = null;
		List<QualityDistributionChart> data = busiReportDao.findQualityTrends(queryPage, UserUtil.getUser().getOrgId());
		Map<String, QualityDistributionChart> dataMap = null;
		/**X轴数据*/
		List<String> xAxisData = getOrgNameByList(1,data);
		List<String> xAxisData2 = getOrgNameByList(2,data);
		Map<String, Integer> orgCount = getOrgCount(data);
		Map<String, SeriesData> seriesDataMap = null;
		List<SeriesData> seriesDataList = new ArrayList<SeriesData>();;
		if(null != xAxisData && xAxisData.size() != 0){
			resultDto = new ReporChartDto();
			seriesDataMap = new HashMap<String, SeriesData>();
			dataMap = getQualityDistributionChartMap(data, 1);
			for(int i = 0; i < xAxisData.size(); i++){
				if(dataMap != null && dataMap.containsKey(xAxisData.get(i))){
					if(seriesDataMap.containsKey(dataMap.get(xAxisData.get(i)).getOrgName())){
						seriesDataMap.get(dataMap.get(xAxisData.get(i)).getOrgName()).getData().add(dataMap.get(xAxisData.get(i)).getSum());
					} else {
						SeriesData seriesData = new SeriesData();
						seriesData.setType("line");
						seriesData.setName(dataMap.get(xAxisData.get(i)).getOrgName());
						List<String> dataList = new ArrayList<String>();
						//给数据补充 0 
						if(orgCount.get(dataMap.get(xAxisData.get(i)).getOrgName()) < xAxisData2.size()){
							for(int j = 0; j < xAxisData2.size() - 1; j++){
								dataList.add("0");
							}
						}
						dataList.add(dataMap.get(xAxisData.get(i)).getSum());
						seriesData.setData(dataList);
						seriesDataMap.put(dataMap.get(xAxisData.get(i)).getOrgName(), seriesData);
					}
				}
			}
		}
		if(resultDto != null){
			resultDto.setxAxisData(xAxisData2);
			resultDto.setLegendData2(new ArrayList<String>(seriesDataMap.keySet()));
			if(seriesDataMap != null){
				for(String key : seriesDataMap.keySet()){
					seriesDataList.add(seriesDataMap.get(key));
				}
			}
			resultDto.setSeriesDataList(seriesDataList);
		}

		return resultDto;
	}
	
	/**
	 * 获取一个map里的sum值
	 * @param map
	 * @param key
	 * @param type 0 sum type 1 yijiejue type 2 weijiejue
	 * @return
	 */
	private int getSumByMap(Map<String, QualityDistributionChart> map, String key, int type){
		int i = 0;
		if(type == 0){
			if(map != null && map.containsKey(key)){
				i = Integer.valueOf(map.get(key).getSum());
			}
		}
		
		if(type == 1){
			if(map != null && map.containsKey(key)){
				i = Integer.valueOf(map.get(key).getYijiejue());
			}
		}
		
		if(type == 2){
			if(map != null && map.containsKey(key)){
				i = Integer.valueOf(map.get(key).getWeijiejue());
			}
		}

		return i;
	}
	
	/**
	 * 获取 质量分布图表 展示数据的map
	 * 包含
	 * 原材料
	 * 半成品
	 * 施工报检
	 * 第三方检测
	 * @param param
	 * @return
	 */
	private Map<String, QualityDistributionChart> getQualityDistributionChartMap(List<QualityDistributionChart> param, int type){
		Map<String, QualityDistributionChart> map = null;
		if(type == 0){
			if(param.size() != 0 ){
				map = new HashMap<String, QualityDistributionChart>();
				for(QualityDistributionChart q : param){
					map.put(q.getOrgName(), q);
				}
			}
		}
		if(type == 1){
			if(param.size() != 0 ){
				map = new HashMap<String, QualityDistributionChart>();
				for(QualityDistributionChart q : param){
					map.put(q.getDate() + q.getOrgName(), q);
				}
			}
		}

		return map;
	}
	/**
	 * 获取每个标段总共几个月有异常数据 key ： orgName value ：count
	 * @param data
	 * @return
	 */
	private Map<String, Integer> getOrgCount(List<QualityDistributionChart> data){
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(QualityDistributionChart entity : data){
			if(map.containsKey(entity.getOrgName())){
				int value = map.get(entity.getOrgName()).intValue() + 1;
				map.put(entity.getOrgName(), value);
			} else {
				map.put(entity.getOrgName(), 1);
			}
		}
		return map;
	}
	
	/**
	 * 从参数中获取组织机构名称
	 * @param params
	 * @return
	 */
	private List<String> getOrgNameByList(int type, List<QualityDistributionChart> ... params){
		Set<String> orgNameSet = new HashSet<>();
		if(type == 0){
			for(List<QualityDistributionChart> o : params){
				for(QualityDistributionChart dto : o){
					orgNameSet.add(dto.getOrgName());
				}
			}
		}
		
		if(type == 1){
			for(List<QualityDistributionChart> o : params){
				for(QualityDistributionChart dto : o){
					orgNameSet.add(dto.getDate() + dto.getOrgName());
				}
			}
		}
		
		if(type == 2){
			for(List<QualityDistributionChart> o : params){
				for(QualityDistributionChart dto : o){
					orgNameSet.add(dto.getDate());
				}
			}
		}
		
		return new ArrayList<String>(orgNameSet);
	}
	
	
	

	
	/**
	 * 根据dataSource 获取 超标图表中的过滤条件
	 * @param dataSource
	 * @return
	 */
	private static String getConditionByDataSource(String dataSource){
		return DataSourceEnum.getValueByName(dataSource).getValue();
	}
	
	/**
	 * 过滤 null
	 * @param object
	 * @return
	 */
	private static String conventNull(Object object){
		return object == null? null: object.toString();
	}
	
	
	/**
	 * 超标等图表的 查询条件 枚举
	 * @author eli_cui
	 *
	 */
	private enum DataSourceEnum{
		//拌合站
		mixingStation("'water_stable_mixing_station','cement_mixing_station_inspection','asphalt_mixing_station'", "mixingStation"),
		//原材料
		rawMaterial("'raw_material_inspection'",	"rawMaterial"),
		//实验室
		laboratory("'test_inspection'","laboratory"),
		//路面摊压
		pavementPressure("'spreader_roller'", "pavementPressure"),
		//张拉压浆
		tensionGrouting("'prestressed_tension_main_inspection'", "tensionGrouting"),
		//质量巡检
		qualityInspection("'routing_inspection'", "qualityInspection"),
		//施工报检
		constructionInspection("'construction_inspection'", "constructionInspection");
		
		private final String value;
		private final String name;
		
		private DataSourceEnum(String value, String name){
			this.value = value;
			this.name = name;
		}
		
		private static DataSourceEnum getValueByName(String name){
			for(DataSourceEnum e : DataSourceEnum.values()){
				if(e.name.equals(name)){
					return e;
				}
			}
			return null;
		}
		
		private String getValue(){
			return value;
		}
		
		private String getName(){
			return name;
		}
	}

	
}