package com.huatek.cache.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
  
/** 
 * 用于删除缓存 
 * @author ajun 
 * @email zhaojun2066@gmail.com 
 * @blog http://blog.csdn.net/ajun_studio 
 * 2012-2-27 上午10:53:03 
 */  
@Target(ElementType.METHOD)     
@Retention(RetentionPolicy.RUNTIME)      
//@Documented      
@Inherited
public @interface CacheFlush {
	/***
	 * 影响的缓存实体类的ID.
	 */
	String id() default "-1";
	/***
	 * 影响的缓存实体类.
	 */
	Class<?>[] factor() default String.class;
	
}
