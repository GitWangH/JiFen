package com.huatek.cache.annotation;
import java.lang.annotation.Documented;  
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
@Target({ElementType.TYPE, ElementType.METHOD})     
@Retention(RetentionPolicy.RUNTIME)      
//@Documented      
@Inherited   
public @interface CacheClass {
	/***
	 * 缓存类名.
	 */
	 Class<?> factor() default String.class;
	 /***
	  * 过期时间.
	  * @return
	  */
	 long exp() default 1000;
}
