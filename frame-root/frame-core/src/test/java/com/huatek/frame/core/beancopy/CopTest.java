package com.huatek.frame.core.beancopy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.huatek.frame.core.page.DataPage;

public class CopTest {

	public static void main(String[] args) {
		// 对象转换
		test1();
		// 属性copy
		//test2();
		// 忽略属性copy
		//test3();
		// 集合对象转换
		//test4();
		// 数组属性copy
		//test5();
		// 深度copy，对象属性是一个集成BaseEntity的对象
		//test6();
		// copy  DataPage
		//test7();

	}

	/**
	 * 对象转换 将对象2转换成对象1 取得个性化BeanCopy实例，指定字符串与时间之间的转换格式，指定true和false的字符形式
	 * 特别指定不同属性名的对应关系 调用convert(source,targetClass)方法进行对象转换
	 * **/
	public static void test1() {
		Object2 source = new Object2();
		source.setValue1(new Date());
		source.setValue2(true);
		source.setValue3(3.3);
		Object target = BeanCopy.getInstance().addFieldPatten("value1", "yyyy/MM-dd HH:mm:ss")
				.addFieldMap("value3", "value4").convert(source, Object4.class);
		System.out.println("source:");
		System.out.println(source);
		System.out.println("target:");
		System.out.println(target);
	}

	/**
	 * 属性copy 对象之间属性copy 取得通用BeanCopy实例 特别指定不同属性名的对应关系
	 * 调用map(sourceObject,targetObject)方法进行属性copy
	 * 
	 * **/
	public static void test2() {
		Object1 source = new Object1();
		source.setValue1("2016-11-12 10:02:03");
		source.setValue2("1");
		source.setValue4("5.8");
		Object2 target = new Object2();
		BeanCopy.getInstance().addFieldMap("value4", "value3")
				.map(source, target);
		System.out.println("source:");
		System.out.println(source);
		System.out.println("target:");
		System.out.println(target);
	}

	/**
	 * 忽略属性copy 对象之间属性copy 取得通用BeanCopy实例 特别指定不同属性名的对应关系
	 * 调用addIgnoreField(ignoreField)添加要忽略的属性，忽略了value2属性
	 * 调用map(sourceObject,targetObject)方法进行属性copy
	 * 
	 * **/
	public static void test3() {
		Object1 source = new Object1();
		source.setValue1("2016-11-12 10:02:03");
		source.setValue2("1");
		source.setValue4("5.8");
		Object2 target = new Object2();
		BeanCopy.getInstance().addIgnoreField("value2")
				.addFieldMap("value4", "value3").map(source, target);
		System.out.println("source:");
		System.out.println(source);
		System.out.println("target:");
		System.out.println(target);
	}

	/**
	 * 集合对象转换 集合对象之间转换 取得通用BeanCopy实例
	 * 调用convertList(sourceList,targetClass)进行集合对象转换
	 * 
	 * **/
	public static void test4() {
		Object2 source1 = new Object2();
		source1.setValue1(new Date());
		source1.setValue2(false);
		source1.setValue3(4.3);
		Object2 source2 = new Object2();
		source2.setValue1(new Date());
		source2.setValue2(true);
		source2.setValue3(4.4);
		List<Object2> sourceList = new ArrayList<Object2>();
		sourceList.add(source1);
		sourceList.add(source2);
		List<Object1> targetList = BeanCopy.getInstance()
				.addFieldMap("value3", "value4")
				.convertList(sourceList, Object1.class);
		System.out.println("source:");
		System.out.println(sourceList);
		System.out.println("target:");
		System.out.println(targetList);
	}

	/**
	 * 数组copy,不用考虑数组的类型相同 调用map(sourceObject,targetObject)方法进行属性copy
	 * 
	 * **/
	public static void test5() {
		Object1 source = new Object1();
		source.setValue1("2016-11-12 10:02:03");
		source.setValue2("1");
		source.setValue4("5.8");
		source.setValue5(new String[] { "2", "3", "4" });
		Object2 target = new Object2();
		BeanCopy.getInstance().map(source, target);
		System.out.println("source:");
		System.out.println(source);
		System.out.println("target:");
		System.out.println(target);
	}
	
	/**
	 * 深度copy, 
	 * Object1中的属性value6为一个复杂对象Object3
	 * Object2中的属性value6为一个复杂对象Object4
	 * 只要源属性和目标属性有一个继承BaseEntity，会自动深度copy,否则要用addDepthField(String field)来指定
	 * 
	 * **/
	public static void test6() {
		Object1 source = new Object1();
		source.setValue1("2016-11-12 10:02:03");
		source.setValue2("1");
		source.setValue4("5.8");
		Object3 nestObject=new Object3();
		nestObject.setValue7("xxx1");
		nestObject.setValue8("xxx2");
		source.setValue6(nestObject);
		Object2 target = new Object2();
		BeanCopy.getInstance().map(source, target);
		
		
		System.out.println("source:");
		System.out.println(source);
		System.out.println("target:");
		//copy完后，从结果中可看出，target的value6中一个有 对象Object4
		System.out.println(target);
	}
	
	/**
	 * 集合对象转换 集合对象之间转换 
	 * 
	 * **/
	public static void test7() {
		Object2 source1 = new Object2();
		source1.setValue1(new Date());
		source1.setValue2(false);
		source1.setValue3(4.3);
		Object2 source2 = new Object2();
		source2.setValue1(new Date());
		source2.setValue2(true);
		source2.setValue3(4.4);
		List<Object2> sourceList = new ArrayList<Object2>();
		sourceList.add(source1);
		sourceList.add(source2);
		DataPage<Object2> dataPage=new DataPage<Object2>();
		dataPage.setContent(sourceList);
		dataPage.setPage(1);
		dataPage.setPageSize(10);
		DataPage<Object1> dataPageTarget = BeanCopy.getInstance().convertPage(dataPage, Object1.class);
		System.out.println(dataPageTarget);
		
	}
}
