
package com.huatek.frame.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

import com.huatek.frame.core.exception.BusinessRuntimeException;

/**
 * 动态bean生成类
 * 
 * @author Maybe
 *
 */
public class DynamicBeanUtil {

	/**
	 * 实体Object
	 */
	private Object object = null;

	/**
	 * 属性map
	 */
	private BeanMap beanMap = null;

	public BeanMap getBeanMap() {
		return beanMap;
	}

	public void setBeanMap(BeanMap beanMap) {
		this.beanMap = beanMap;
	}

	public DynamicBeanUtil(Map<String, Class<?>> propertyMap) {
		this.object = generateBean(propertyMap);
		this.beanMap = BeanMap.create(this.object);
	}

	/**
	 * 给bean属性赋值
	 * 
	 * @param property
	 *            属性名
	 * @param value
	 *            值
	 */
	public void setValue(String property, Object value) {
		beanMap.put(property, value);
	}

	/**
	 * 通过属性名得到属性值
	 * 
	 * @param property
	 *            属性名
	 * @return 值
	 */
	public Object getValue(String property) {
		return beanMap.get(property);
	}

	/**
	 * 得到该实体bean对象
	 * 
	 * @return
	 */
	public Object getObject() {
		return this.object;
	}

	/**
	 * @param propertyMap
	 * @return
	 */
	private Object generateBean(Map<String, Class<?>> propertyMap) {
		BeanGenerator generator = new BeanGenerator();
		Set<String> keySet = propertyMap.keySet();
		for (Iterator<String> i = keySet.iterator(); i.hasNext();) {
			String key = i.next();
			generator.addProperty(key, propertyMap.get(key));
		}
		return generator.create();
	}

	/**
	 * 动态转换（格转行）
	 * 
	 * @param cells
	 * @return
	 */
	public static List<Object> cellToRow(List cells) {

		// 1rows TODO 按商品顺序排列
		Map<String, List<CellToRowProcessor>> rowMap = new HashMap();// TODO
																		// 一个最小预测单元一row
		for (int i = 0; i < cells.size(); i++) {
			CellToRowProcessor cell = (CellToRowProcessor) cells.get(i);
			// for (CellToRowProcessor cell : cells) {
			if (rowMap.containsKey(cell.getRowId())) {// TODO 一个最小预测单元一row
				rowMap.get(cell.getRowId()).add(cell);// TODO
														// 按周顺序add
			} else {
				List<CellToRowProcessor> l = new ArrayList<CellToRowProcessor>();
				l.add(cell);
				rowMap.put(cell.getRowId(), l);
			}
		}

		// 2组装动态bean
		Set<Entry<String, List<CellToRowProcessor>>> rowSet = rowMap.entrySet();
		Iterator<Entry<String, List<CellToRowProcessor>>> entryItr = rowSet.iterator();
		Entry<String, List<CellToRowProcessor>> en;

		// Set<String> keys = rowMap.keySet();
		// Iterator keyItr = keys.iterator();
		List<Object> result = new ArrayList<Object>();
		while (entryItr.hasNext()) {
			en = entryItr.next();

			// 2.1构造：动态bean定义（成员变量）
			String rowId = en.getKey();// (String) keyItr.next();
			List<CellToRowProcessor> rowCells = en.getValue();// rowMap.get(rowId);

			Map propertyMap = null;
			CellToRowProcessor rowCell;
			Class propertyClass = Object.class;
			for (int i = 0; i < rowCells.size(); i++) {
				rowCell = rowCells.get(i);
				if (i == 0) {
					propertyMap = rowCell.getManualColsDef();// （自定义列）首列
				}
				propertyMap.put(rowCell.getPropertyName(), propertyClass);// 其他列
			}
			DynamicBeanUtil row = new DynamicBeanUtil(propertyMap);

			/*
			 * debug Method[] fs =
			 * row.getObject().getClass().getDeclaredMethods(); for (int i = 0;
			 * i < fs.length; i++) { System.out.println(fs[i].getName()); }
			 */

			// 2.2动态bean赋值（成员变量赋值）
			Method m;
			try {

				CellToRowProcessor d;
				for (int i = 0; i < rowCells.size(); i++) {
					d = rowCells.get(i);

					// （自定义列）首列赋值
					if (i == 0) {
						d.setManualColsValue(row, en);
					}

					// 其他列赋值
					m = row.getClass().getMethod("setValue", new Class[] { String.class, Object.class });
					m.invoke(row, new Object[] { d.getPropertyName(), d.getPropertyValue() });
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				throw new BusinessRuntimeException("格转行出现异常", "TODO", e);
			}
			result.add(row.getObject());
		}

		return result;

	}

	/**
	 * 动态转换（格转行）（适用于一条格数据，可能需要转换为多列的情况）
	 * 
	 * @param cells
	 * @return
	 */
	public static List<Object> cellToRowMultiCol(List cells) {

		// 1rows TODO 按商品顺序排列
		Map<String, List<CellToRowMultiColProcessor>> rowMap = new HashMap();// TODO
		// 一个最小预测单元一row
		for (int i = 0; i < cells.size(); i++) {
			CellToRowMultiColProcessor cell = (CellToRowMultiColProcessor) cells.get(i);
			// for (CellToRowProcessor cell : cells) {
			if (rowMap.containsKey(cell.getRowId())) {// TODO 一个最小预测单元一row
				rowMap.get(cell.getRowId()).add(cell);// TODO
														// 按周顺序add
			} else {
				List<CellToRowMultiColProcessor> l = new ArrayList<CellToRowMultiColProcessor>();
				l.add(cell);
				rowMap.put(cell.getRowId(), l);
			}
		}

		// 2组装动态bean
		Set<Entry<String, List<CellToRowMultiColProcessor>>> rowSet = rowMap.entrySet();
		Iterator<Entry<String, List<CellToRowMultiColProcessor>>> entryItr = rowSet.iterator();
		Entry<String, List<CellToRowMultiColProcessor>> en;

		// Set<String> keys = rowMap.keySet();
		// Iterator keyItr = keys.iterator();
		List<Object> result = new ArrayList<Object>();
		
		/***/
		Map<String, String> map = new HashMap<String, String>();
		map.put("w3", "33");
		map.put("w4", "44");
		map.put("w5", "55");
		map.put("w6", "66");
		map.put("wr3", "3");
		map.put("wr4", "4");
		map.put("wr5", "5");
		map.put("wr6", "6");
		result.add(map);
		/***/
		
		
		int loop = 0;
		Map propertyMap = null;
		DynamicBeanUtil row;
		CellToRowMultiColProcessor rowCell;
		Class propertyClass = Object.class;
		List<CellToRowMultiColProcessor> rowCells;
		while (entryItr.hasNext()) {
			en = entryItr.next();
			rowCells = en.getValue();// rowMap.get(rowId);
			
			// 2.1构造：动态bean定义（成员变量）
			if (loop == 0) {
				String rowId = en.getKey();// (String) keyItr.next();
				

				for (int i = 0; i < rowCells.size(); i++) {
					rowCell = rowCells.get(i);
					if (i == 0) {
						propertyMap = rowCell.getManualColsDef();// （自定义列）首列
					}

					List<String> propertyNames = rowCell.getPropertyNames();// 一条格数据，会被转换为多列
					for (String propertyName : propertyNames) {
						propertyMap.put(propertyName, propertyClass);// 其他列
					}
				}
			}
			row = new DynamicBeanUtil(propertyMap);
			loop++;

			/*
			 * debug Method[] fs =
			 * row.getObject().getClass().getDeclaredMethods(); for (int i = 0;
			 * i < fs.length; i++) { System.out.println(fs[i].getName()); }
			 */

			// 2.2动态bean赋值（成员变量赋值）
			Method m;
			try {

				CellToRowMultiColProcessor d;
				for (int i = 0; i < rowCells.size(); i++) {
					d = rowCells.get(i);

					// （自定义列）首列赋值
					if (i == 0) {
						d.setManualMultiColsValue(row, en);
					}

					// 其他列赋值
					List<String> propertyNames = d.getPropertyNames();// 一条格数据，会被转换为多列
					m = row.getClass().getMethod("setValue", new Class[] { String.class, Object.class });
					for (String propertyName : propertyNames) {
						m.invoke(row, new Object[] { propertyName, d.getPropertyValues().get(propertyName) });
					}
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				throw new BusinessRuntimeException("格转行出现异常", "TODO", e);
			}
			result.add(row.getObject());
		}

		return result;

	}

}
