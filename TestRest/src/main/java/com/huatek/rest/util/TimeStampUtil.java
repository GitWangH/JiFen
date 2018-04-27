package com.huatek.rest.util;

import java.text.DateFormat;
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

}
