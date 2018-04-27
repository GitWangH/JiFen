/**
 * 
 */
package com.huatek.frame.core.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @Description:应用于：一条格数据，需要转变为多列的情况
 * @author caojun1@hisense.com
 * @date 2016年2月1日 下午12:38:17
 * @version V1.0
 */
public interface CellToRowMultiColProcessor extends CellToRowProcessor {

	/**
	 * 格数据需要转换为多列，对应属性的集合
	 * 
	 * @return
	 */
	public List<String> getPropertyNames();

	/**
	 * 格数据需要转换为多列，对应属性的值的集合
	 * 
	 * @return
	 */
	public Map<String, Object> getPropertyValues();

	public void setManualMultiColsValue(DynamicBeanUtil row, Entry<String, List<CellToRowMultiColProcessor>> rowEntry);
}
