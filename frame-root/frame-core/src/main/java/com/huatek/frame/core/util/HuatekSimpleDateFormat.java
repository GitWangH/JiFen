package com.huatek.frame.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 解决各种*Utils工具中使用SimpleDateFormat静态变量 发生线程安全的问题
 * @author ken_bai
 * @email ken_bai@huatek.com
 * @date 2017年1月18日19:30:27
 *
 */
public class HuatekSimpleDateFormat  {

	private final String formatPatten;
	
	public HuatekSimpleDateFormat(String patten) {
		
		formatPatten=patten;
	}

	
	public Date parse(String source) throws ParseException {
		
		return new SimpleDateFormat(formatPatten).parse(source);
	}

	 public  String format(Date date){
		 return new SimpleDateFormat(formatPatten).format(date);
	 }
	
}
