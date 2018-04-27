package com.huatek.frame.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampUtil {
	public static String getLongTimeStamp(Date date) {
		String dateStr = "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (date != null) {
			dateStr = df.format(date);
		}
		return dateStr;
	}
	
	public static Date StrToDate(String str) {
		  
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		   Date date = null;
		   try {
		    date = format.parse(str);
		   } catch (ParseException e) {
		    e.printStackTrace();
		   }
		   return date;
		}
	
	public static int getIntTimeStamp(Date date){
		String dateStr = "";
		long stamp13bit=0;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (date != null) {
			dateStr = df.format(date);
		}
		try {
			stamp13bit=df.parse(dateStr).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Long(stamp13bit/1000).intValue();
		
	} 

}
