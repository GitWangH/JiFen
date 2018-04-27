package com.huatek.frame.util;

import java.util.Arrays;
import java.util.List;

public class Number2ChineseUtil {

	// num 表示数字，lower表示小写，upper表示大写
	private static final String[] num_lower = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
	private static final String[] num_upper = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

	// unit 表示单位权值，lower表示小写，upper表示大写
	private static final String[] unit_lower = { "", "十", "百", "千" };
	private static final String[] unit_upper = { "", "拾", "佰", "仟" };
	private static final String[] unit_common = { "", "万", "亿", "兆", "京", "垓", "秭", "穰", "沟", "涧", "正", "载" };

	// 允许的格式
	private static final List<String> promissTypes = Arrays.asList("INTEGER", "INT", "LONG", "DECIMAL", "FLOAT", "DOUBLE", "STRING", "BYTE", "TYPE", "SHORT");

	/**
	 * 数字转化为小写的汉字
	 * 
	 * @param num
	 *            将要转化的数字
	 * @return
	 */
	public static String toChineseLower(Object num) {
		return format(num, num_lower, unit_lower,false);
	}

	/**
	 * 数字转化为大写的汉字
	 * 
	 * @param num
	 *            将要转化的数字
	 * @param isMoney
	 * 			     转换是否为金钱         
	 * @return
	 */
	public static String toChineseUpper(Object num,boolean isMoney) {
		if(num.toString().contains("-"))
			return "负"+format(num, num_upper, unit_upper,isMoney);
		else 
			return format(num, num_upper, unit_upper,isMoney);
	}

	/**
	 * 格式化数字
	 * 
	 * @param num
	 *            原数字
	 * @param numArray
	 *            数字大小写数组
	 * @param unit
	 *            单位权值
	 * @param isMoney
	 *            是否为金钱类转换
	 * @return
	 */
	private static String format(Object num, String[] numArray, String[] unit,boolean isMoney) {
		if (!promissTypes.contains(num.getClass().getSimpleName().toUpperCase())) {
			throw new RuntimeException("不支持的格式类型");
		}
		// 获取整数部分
		String intnum = getInt(String.valueOf(num));
		// 获取小数部分
		String decimal = getFraction(String.valueOf(num));
		// 格式化整数部分
		String result = formatIntPart(intnum, numArray, unit);
		
		return joinNum(result,decimal,numArray,isMoney);
	}

	/**
	 * 拼接整数和小数
	 * @param result 拼接结果
	 * @param decimal 小数部分
	 * @param numArray 数字大小写数组
	 * @param isMoney 是否金钱转换
	 * @return
	 */
	private static String joinNum(String result,String decimal,String[] numArray,boolean isMoney){
		if(isMoney){
			if (!"".equals(decimal)) {// 小数部分不为空
				// 格式化小数
				result += "元" + formatFractionalPart(decimal, numArray,isMoney);
			}else{
				result += "元整";
			}
		}else{
			if (!"".equals(decimal)) {// 小数部分不为空
				// 格式化小数
				result += formatFractionalPart(decimal, numArray,isMoney);
			}
		}
		return result;
	}
	
	/**
	 * 格式化整数部分
	 * 
	 * @param num
	 *            整数部分
	 * @param numArray
	 *            数字大小写数组
	 * @return
	 */
	private static String formatIntPart(String num, String[] numArray, String[] unit) {

		// 按4位分割成不同的组（不足四位的前面补0）
		Integer[] intnums = split2IntArray(num);
		
		boolean zero = false;
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < intnums.length; i++) {
			/******************** rocky添加 开始  *********/
			if(intnums[i]==0 && i==intnums.length-1){
				sb.append("零");
				return sb.toString();
			}
			/******************** 结束  *********/
			// 格式化当前4位
			String r = formatInt(intnums[i], numArray, unit);
			if ("".equals(r)) {//
				if ((i + 1) == intnums.length) {
					sb.append(numArray[0]);// 结果中追加“零”
				} else {
					zero = true;
				}
			} else {// 当前4位格式化结果不为空（即不为0）
				if (zero || (i > 0 && intnums[i] < 1000)) {// 如果前4位为0，当前4位不为0
					sb.append(numArray[0]);// 结果中追加“零”
				}
				sb.append(r);
				sb.append(unit_common[intnums.length - 1 - i]);// 在结果中添加权值
				zero = false;
			}
		}
		return sb.toString();
	}
	
	
	
	/**
	 * 分割数字，每4位一组
	 * 
	 * @param num
	 * @return
	 */
	private static Integer[] split2IntArray(String num){
		String prev = num.substring(0,num.length() % 4);
		String stuff = num.substring(num.length() % 4);
		if(!"".equals(prev)){
			num = String.format("%04d",Integer.valueOf(prev))+stuff;
		}
		Integer[] ints = new Integer[num.length()/4];
		int idx=0;
		for(int i=0;i<num.length();i+=4){
			String n = num.substring(i,i+4);
			ints[idx++]=Integer.valueOf(n);
		}
		return ints;
	}
	
	

	/**
	 * 格式化4位整数
	 * 
	 * @param num
	 * @param numArray
	 * @return
	 */
	private static String formatInt(int num,String[] numArray,String[] unit){
		char[] val = String.valueOf(num).toCharArray();
		int len = val.length;
		StringBuilder sb = new StringBuilder();
		boolean isZero = false;
		for (int i = 0; i < len; i++) {
			int n = Integer.valueOf(val[i] + "");//获取当前位的数值
			if (n==0) {
				isZero = true;
			} else {
				if (isZero) {
					sb.append(numArray[Integer.valueOf(val[i-1] + "")]);
				}
				sb.append(numArray[n]);
				sb.append(unit[(len - 1) - i]);
				isZero=false;
			}
		}
		return sb.toString();
	}

	/**
	 * 格式化小数部分
	 * 
	 * @param decimal
	 *            小数部分
	 * @param numArray
	 *            数字大小写数组
	 * @return
	 */
	private static String formatFractionalPart(String decimal, String[] numArray,boolean isMoney) {
		char[] val = String.valueOf(decimal).toCharArray();
		int len = val.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			int n = Integer.valueOf(val[i] + "");
			if(i==0){//角
				sb.append(isMoney ? numArray[n]+"角" : numArray[n]);
			}else if(i==1){
				sb.append(isMoney ? numArray[n]+"分" : numArray[n]);
			}else{
				break;
			}
		}
		return sb.toString();
	}

	// 拆分整数和小数的方法在这里：

	/**
	 * 获取整数部分
	 * 
	 * @param num
	 * @return
	 */
	private static String getInt(String num) {
		// 检查格式
		checkNum(num);

		char[] val = String.valueOf(num).toCharArray();
		StringBuffer sb = new StringBuffer();
		int t, s = 0;
		for (int i = 0; i < val.length; i++) {
			if (val[i] == '.') {
				break;
			}
			if(val[i] == '-'){
				continue;
			}
			t = Integer.parseInt(val[i] + "", 16);
			if (s + t == 0) {
				continue;
			}
			sb.append(t);
			s += t;
		}
		return (sb.length() == 0 ? "0" : sb.toString());
	}

	/**
	 * 获取小数部分
	 * 
	 * @param num
	 * @return
	 */
	private static String getFraction(String num) {
		int i = num.lastIndexOf(".");
		int j = num.indexOf("-");
		if (num.indexOf(".") != i) {
			throw new RuntimeException("数字格式不正确，最多只能有一位小数点！");
		}
		if(j!=-1){
			num = num.substring(j+1);
		}
		String fraction = "";
		if (i >= 0) {
			fraction = getInt(new StringBuffer(num).reverse().toString());
			if (fraction.equals("0")) {
				return "";
			}
		}
		return new StringBuffer(fraction).reverse().toString();
	}

	/**
	 * 检查数字格式
	 * 
	 * @param num
	 */
	private static void checkNum(String num) {
		if (num.indexOf(".") != num.lastIndexOf(".")) {
			throw new RuntimeException("数字[" + num + "]格式不正确!");
		}
		if (num.indexOf("-") != num.lastIndexOf("-") || num.lastIndexOf("-") > 0) {
			throw new RuntimeException("数字[" + num + "]格式不正确！");
		}
		if (num.indexOf("+") != num.lastIndexOf("+")) {
			throw new RuntimeException("数字[" + num + "]格式不正确！");
		}
		if (num.indexOf("+") != num.lastIndexOf("+")) {
			throw new RuntimeException("数字[" + num + "]格式不正确！");
		}
		if (num.replaceAll("[\\d|\\.|\\-|\\+]", "").length() > 0) {
			throw new RuntimeException("数字[" + num + "]格式不正确！");
		}
	}

	// 通过这种分而治之的思路，处理起来就简单多了。写个main函数调用一下：

	public static void main(String[] args) {
		short s = 10;
		byte b = 10;
		char c = 'A';
		Object[] nums = { s, b, c, 0, 1001, 100100001L, 21., 205.23F, 205.23D, "01000010", "1000000100105.0123", ".142", "20.00", "1..2", true };
		System.out.println("将任意数字转化为汉字(包括整数、小数以及各种类型的数字)");
		System.out.println("--------------------------------------------");
		for (Object num : nums) {
			try {
				System.out.print("[" + num.getClass().getSimpleName() + "]" + num);
				for (int i = 0; i < 25 - String.valueOf(num + num.getClass().getSimpleName()).length(); i += 4) {
					System.out.print("\t");
				}
				// 调用转化为小写和大写
				System.out.print(" format:" + toChineseLower(num));
//				System.out.println("【" + toChineseUpper(num) + "】");
			} catch (Exception e) {
				System.out.println(" 错误信息：" + e.getMessage());
			}
		}
	}

}
