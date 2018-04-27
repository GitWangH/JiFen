package com.huatek.frame.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {
	/**
     * 通过实体类与方法名和参数列表来反射调用方法
     * @param obj
     *          已经实例化的类
     * @param methodName
     *          方法名称
     * @param args
     *          参数数组
     */
    public static Object methodReflect(Object obj, String methodName, Object[] args) {

        Class[] argsClass = new Class[args.length];
        Object o=new Object();
        for(int i=0; i<args.length; i++) {
            argsClass[i] = args[i].getClass(); //获得每一个参数的实际类型
        }

        try {

            Method methodReflect = obj.getClass().getMethod(methodName, argsClass);//反射获得方法
            o=methodReflect.invoke(obj, args);    //调用此方法
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    	return o;
    }

    /**
     * 通过类的名称与方法名和参数列表来反射调用方法
     * @param className
     *              类所在的路径
     * @param methodName
     *              方法的名称
     * @param args
     *          参数的列表
     */
    public static Object methodReflect(String className, String methodName, Object[] args) {

        Class[] argsClass = new Class[args.length];
        Object o=new Object();
        for(int i=0; i<args.length; i++) {
            argsClass[i] = args[i].getClass();  //获得每一个参数的实际类型
        }

        try {

            Class c = Class.forName(className); //通过类名获得Class
            Object obj = c.newInstance();   //实例化类
            Method methodReflect = obj.getClass().getMethod(methodName, argsClass); //反射获得方法
            o= methodReflect.invoke(obj, args);    //调用此方法

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
		return o;
    }
}
