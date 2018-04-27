package com.huatek.frame.core.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.huatek.frame.core.model.TreeEntity;


/**
 * 该类是项目中常用到的一些公共方法.
 *
 * @author winner pan
 */
public class FrameUtil {

	/**
	 * 日志常量.
	 */
	private static final Logger LOGGER = Logger.getLogger(FrameUtil.class);


	/**
	 * 默认的私有的构造函数，不能生成新的实例.
	 * **/
	private FrameUtil() {

	}
	
	
	/**
	 * 根据对象获取字符串.
	 *
	 * @param obj
	 *            输入对象.
	 * @return String
	 */
	public static String getString(final Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString().trim();
	}

	/**
	 * 根据对象获取整数.
	 *
	 * @param obj
	 *            输入对象.
	 * @return Integer
	 */
	public static int getInteger(final Object obj) {
		if (obj == null || obj.equals("")) {
			return 0;
		}
		return Integer.valueOf(obj.toString()).intValue();
	}
	
	/**
	 * 根据对象获取Long.
	 *
	 * @param obj
	 *            输入对象.
	 * @return Long
	 */
	public static Long getLong(final Object obj) {
		if (obj == null || obj.equals("")) {
			return 0L;
		}
		return Long.valueOf(obj.toString()).longValue();
	}

	/**
	 * 根据对象获取大精度数据对象.
	 *
	 * @param obj
	 *            输入对象.
	 * @return Integer
	 */
	public static BigDecimal getBigDecimal(final Object obj) {
		if (obj == null|| obj.equals("")) {
			return new BigDecimal("0");
		}
		return new BigDecimal(obj.toString());
	}


	
	/**
	 * 判断参数是否是Number型
	 *
	 * @param str
	 *            参数
	 * @return 返回真和假
	 */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

	/**
	 * 判断字符串是否匹配正则表达式.
	 *
	 * @param aimStr
	 *            目标字符串
	 * @param regex
	 *            正则表达式
	 * @return 匹配结果(true:匹配, false:不匹配)
	 * **/
	public static boolean getMatchResult(final String aimStr, final String regex) {
  		 Pattern pattern = Pattern.compile(regex);
  		 Matcher matcher = pattern.matcher(aimStr);
  		 return matcher.matches();
	}


	/**
	 * 按指定长度截断字符串
	 *
	 * @param s
	 * @param size
	 * @return
	 */
    public static String substring(final String sourceStr, int size) {
        String s=sourceStr;
    	if (size <= 0) {
            return "";
        }
        else if (s == null || s.getBytes().length <= size) {
            return s;
        }

        int index = 0;
        byte[] bs = s.getBytes();
        if ((bs[size] >> 7) == 0x0 || ((bs[size] >> 6) & 0x03) == 0x03) {
            index = size;
        }
        else {
            for (int i = size - 1; i >= 0; i--) {
                if (((bs[i] >> 6) & 0x03) == 0x03) {
                    index = i;
                    break;
                }
            }
        }
        return new String(Arrays.copyOf(s.getBytes(), index));
    }
    
    public static boolean isSubGroup(final TreeEntity holdGroup, final TreeEntity compareGroup){
    	if(compareGroup.getOrgLevel()==1){
    		return holdGroup.getId().equals(compareGroup.getId());
    	}
    	if(compareGroup.getOrgLevel()==2){
    		return holdGroup.getLevel1()==compareGroup.getLevel1() &&
        			holdGroup.getLevel2()==compareGroup.getLevel2();
    	}
    	if(compareGroup.getOrgLevel()==3){
    		return  
        			holdGroup.getLevel1()==compareGroup.getLevel1() &&
        			holdGroup.getLevel2()==compareGroup.getLevel2() &&
        			holdGroup.getLevel3()==compareGroup.getLevel3();
    	}
    	if(compareGroup.getOrgLevel()==4){
    		return holdGroup.getLevel1()==compareGroup.getLevel1() &&
        			holdGroup.getLevel2()==compareGroup.getLevel2() &&
        			holdGroup.getLevel3()==compareGroup.getLevel3() &&
        			holdGroup.getLevel4()==compareGroup.getLevel4();
    	}
    	if(compareGroup.getOrgLevel()==5){
    		return holdGroup.getLevel1()==compareGroup.getLevel1() &&
        			holdGroup.getLevel2()==compareGroup.getLevel2() &&
        			holdGroup.getLevel3()==compareGroup.getLevel3() &&
        			holdGroup.getLevel4()==compareGroup.getLevel4()&&
        			holdGroup.getLevel5()==compareGroup.getLevel5();
    	}
    	if(compareGroup.getOrgLevel()==5){
    		return holdGroup.getLevel1()==compareGroup.getLevel1() &&
        			holdGroup.getLevel2()==compareGroup.getLevel2() &&
        			holdGroup.getLevel3()==compareGroup.getLevel3() &&
        			holdGroup.getLevel4()==compareGroup.getLevel4()&&
        			holdGroup.getLevel5()==compareGroup.getLevel5();
    	}
    	if(compareGroup.getOrgLevel()==6){
    		return holdGroup.getLevel1()==compareGroup.getLevel1() &&
        			holdGroup.getLevel2()==compareGroup.getLevel2() &&
        			holdGroup.getLevel3()==compareGroup.getLevel3() &&
        			holdGroup.getLevel4()==compareGroup.getLevel4()&&
        			holdGroup.getLevel5()==compareGroup.getLevel5() &&
        			holdGroup.getLevel6()==compareGroup.getLevel6();
    	}
    	if(compareGroup.getOrgLevel()==7){
    		return holdGroup.getLevel1()==compareGroup.getLevel1() &&
        			holdGroup.getLevel2()==compareGroup.getLevel2() &&
        			holdGroup.getLevel3()==compareGroup.getLevel3() &&
        			holdGroup.getLevel4()==compareGroup.getLevel4()&&
        			holdGroup.getLevel5()==compareGroup.getLevel5() &&
        			holdGroup.getLevel6()==compareGroup.getLevel6() &&
        			holdGroup.getLevel7()==compareGroup.getLevel7();
    	}
    	if(compareGroup.getOrgLevel()==8){
    		return holdGroup.getLevel1()==compareGroup.getLevel1() &&
        			holdGroup.getLevel2()==compareGroup.getLevel2() &&
        			holdGroup.getLevel3()==compareGroup.getLevel3() &&
        			holdGroup.getLevel4()==compareGroup.getLevel4() &&
        			holdGroup.getLevel5()==compareGroup.getLevel5() &&
        			holdGroup.getLevel6()==compareGroup.getLevel6() &&
        			holdGroup.getLevel7()==compareGroup.getLevel7() &&
        			holdGroup.getLevel8()==compareGroup.getLevel8();
    	}
    	if(compareGroup.getOrgLevel()==9){
    		return holdGroup.getLevel1()==compareGroup.getLevel1() &&
        			holdGroup.getLevel2()==compareGroup.getLevel2() &&
        			holdGroup.getLevel3()==compareGroup.getLevel3() &&
        			holdGroup.getLevel4()==compareGroup.getLevel4() &&
        			holdGroup.getLevel5()==compareGroup.getLevel5() &&
        			holdGroup.getLevel6()==compareGroup.getLevel6() &&
        			holdGroup.getLevel7()==compareGroup.getLevel7() &&
        			holdGroup.getLevel8()==compareGroup.getLevel8() &&
    				holdGroup.getLevel9()==compareGroup.getLevel9();
    	}
    	if(compareGroup.getOrgLevel()==10){
    		return holdGroup.getId().equals(compareGroup.getId());
    	}
    	return false;
    }

    /***
	 * 判断权限数据是否包含指定的ID数据.
	 *
	 * @param id
	 *            给定的ID.
	 * @param authorityIds
	 *            权限数据列表.
	 * @return 是否包含.
	 */
	public static boolean isContains(final String id, final String authorityIds) {
		String tempId = id;
		if (StringUtils.isEmpty(id)) {
			return true;
		}
		if (authorityIds.startsWith("'")) {
			tempId = "'" + tempId + "'";
		}
		if (authorityIds.startsWith(tempId + ",")
				|| authorityIds.indexOf("," + tempId) > 0
				|| authorityIds.endsWith("," + tempId)
				|| tempId.equals(authorityIds)) {
			return true;
		}
		return false;
	}

}
