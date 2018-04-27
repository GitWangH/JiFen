package com.huatek.frame.core.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//@Component
// 表明可被 Spring 扫描
public @interface DataAuthName {

	  String name() default "";
}
