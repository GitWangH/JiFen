package com.huatek.busi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class Utils {
	 /**
     * 获取上一个月
     *
     * @return
     */
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }
    
    public static String getLastThreeMonth(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
    	 c.setTime(new Date());
         c.add(Calendar.MONTH, -3);
         Date m3 = c.getTime();
         String mon3 = format.format(m3);
         return mon3;
    }
    
    public static String getLastYear(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        String year = format.format(y);
        return year;
    }

    
    public static String getTime(String time){
    	if(StringUtils.isNoneBlank(time)){
			switch(time){
			case "all_date":
				time = "";
				break;
			case "nearly_one_month":
				time = Utils.getLastMonth();
				break;
			case "nearly_three_month":
				time = Utils.getLastThreeMonth();
				break;
			case "nearly_one_year":
				time = Utils.getLastYear();
				break;
			default:
				time = "";
				break;
			}
		}
    	return time;
    }
}
