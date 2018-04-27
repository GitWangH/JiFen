package com.huatek.busi.api.report;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.constants.Constant;
import com.huatek.busi.dto.report.ReporChartDto;
import com.huatek.busi.service.report.BusiReportService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiReportAction
 * @Description: 报表 后台控制器Action
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-15 13:55:35
 * @version: 1.0
 * @memuInfo [报表管理]
 * ------------------------------------------------------------------
 * 修改历史 
 * 序号       版本号          	    修改日期       			 修改人           	        修改原因 
 *  1	  1.0	2017-11-15 13:55:35	  mickey_meng	 Create New Class
 *  2
 *  ...
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_REPORT_API)
public class BusiReportAction {

    @Autowired
    private OperationService oprationService;//日志记录服务类
    
    @Autowired
    private BusiReportService busiReportService;
    

    /**
     * 报表统计API入口，根据reportType调用对应的Service接口
     * @param reportType
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "/getReportData/{reportType}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ReporChartDto> _getUnqualifiedStatisticsData(@PathVariable("reportType") String reportType, @RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	ReporChartDto reporChartDto = null;
    	if(StringUtils.isNotEmpty(reportType)){
    		switch (reportType) {
			case Constant.Report.UNQUALIFIED ://不合格统计
				reporChartDto = busiReportService.getUnqualifiedStatisticsData(null);
				break;
			case Constant.Report.MIXING_STATION ://拌合站
				reporChartDto = busiReportService.getOverStandardChartData(Constant.Report.MIXING_STATION, queryPage);
				break;
			case Constant.Report.RAW_MATERIAL ://原材料
				reporChartDto = busiReportService.getOverStandardChartData(Constant.Report.RAW_MATERIAL, queryPage);
				break;
			case Constant.Report.LABORATORY ://实验室
				reporChartDto = busiReportService.getOverStandardChartData(Constant.Report.LABORATORY, queryPage);
				break;
			case Constant.Report.PAVEMENT_PRESSURE ://路面摊压
				reporChartDto = busiReportService.getOverStandardChartData(Constant.Report.PAVEMENT_PRESSURE, queryPage);
				break;
			case Constant.Report.TENSION_GROUTING ://张拉压浆
				reporChartDto = busiReportService.getOverStandardChartData(Constant.Report.TENSION_GROUTING, queryPage);
				break;
			case Constant.Report.QUALITY_INSPECTION ://质量巡检
				reporChartDto = busiReportService.getOverStandardChartData(Constant.Report.QUALITY_INSPECTION, queryPage);
				break;
			case Constant.Report.CONSTRUCTION_INSPECTION ://施工报检
				reporChartDto = busiReportService.getOverStandardChartData(Constant.Report.CONSTRUCTION_INSPECTION, queryPage);
				break;
			case Constant.Report.UNIT_ENGINEERING_INSPECTION_UNQUALIFIED : //单位工程报检不合格
				reporChartDto = busiReportService.getUnitEngineeringInspectionUnqualified(queryPage);
				break;
			case Constant.Report.QUALITY_SEVERITY : //质量问题严重程度情况 
				reporChartDto = busiReportService.getQualitySeverityData(queryPage);
				break;
			case Constant.Report.QUALITY_DISTRIBUTION_CHART : //质量分布图表
				reporChartDto = busiReportService.getQualityDistributionChart(queryPage);
				break;
			case Constant.Report.QUALITY_PROBLEM : //质量问题
				reporChartDto = busiReportService.getQualityProblem(queryPage);
				break;
			case Constant.Report.QUALITY_TRENDS : // 质量趋势图
				reporChartDto = busiReportService.getQualityTrends(queryPage);
				break;
			default:
				throw new BusinessRuntimeException("dataSource参数错误。 BusiReportAction._getUnqualifiedStatisticsData", "-1");
			}
    	}
        return new ResponseEntity<>(reporChartDto, HttpStatus.OK);
    }
    
    
}