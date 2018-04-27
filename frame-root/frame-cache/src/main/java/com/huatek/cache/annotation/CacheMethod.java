package com.huatek.cache.annotation;
import java.lang.annotation.Documented;  
import java.lang.annotation.ElementType;  
import java.lang.annotation.Inherited;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
 /***
  * 缓存存储的默认key都是类名，方法名，参数值的hash值. 
  * 这个注解主要用于缓存变化时本缓存被影响的因子的记录.
  * ID是和本缓存记录值直接相关的实体ID值。缓存只和该ID数据相关。
  * id不是必填项.factors和factor必须其中一个有值，否则缓存无法使用，日志提示错误。
  * @author winner pan.
  *
  */
@Target(ElementType.METHOD)     
@Retention(RetentionPolicy.RUNTIME)      
//@Documented      
@Inherited   
public @interface CacheMethod {
	Class<?> factor() default String.class;
	/***
	 * 实体缓存的ID.
	 * @return
	 */
	String id() default "-1";
	/***
	 * 缓存类名.
	 */
	 Class<?>[] factors() default String.class;
	 /***
	  * 过期时间.
	  * @return
	  */
	 long exp() default 1000;
}
