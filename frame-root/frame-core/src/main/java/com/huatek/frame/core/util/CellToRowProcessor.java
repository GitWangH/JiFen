/**
 * 
 */
package com.huatek.frame.core.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @Description: TODO
 * @author caojun1@hisense.com
 * @date 2016年1月18日 上午10:31:00
 * @version V1.0
 */
public interface CellToRowProcessor {

	public String getRowId();

	public String getPropertyName();

	public Object getPropertyValue();

	/**
	 * 定位“数据范围”的标识（如，预测中是“版本-组织-物料”，附件需求中是“执行周度”）
	 * 
	 * @return
	 */
	public String getVersionId();

	/**
	 * 自定义的列
	 * 
	 * @return
	 */
	public Map getManualColsDef();

	/**
	 * 为自定义列赋值
	 * 
	 * @param row
	 *            动态bean（代表一行）
	 * @param rowEntry（格转为行之后的行数据）
	 */
	public void setManualColsValue(DynamicBeanUtil row, Entry<String, List<CellToRowProcessor>> rowEntry);
}
