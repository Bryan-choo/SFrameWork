package com.ccw.sframework.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Todo Controller注解
 * @author a
 * @Date 2018年2月6日 上午12:55:33
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SmartComponent
public @interface Controller {

	/**
	 * controller映射url
	 * @return
	 */
	String mappedurl() default "/";
}
