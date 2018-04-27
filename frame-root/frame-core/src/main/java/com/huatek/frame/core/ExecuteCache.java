package com.huatek.frame.core;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 /***
  * 该注解用于记录当前service需要保存的实体类.
  * @author winner pan.
  *
  */
@Target(ElementType.METHOD)     
@Retention(RetentionPolicy.RUNTIME)         
@Inherited   
public @interface ExecuteCache {
	String value() default "executeCache";
}
