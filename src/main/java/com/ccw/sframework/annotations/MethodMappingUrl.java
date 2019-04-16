package com.ccw.sframework.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Todo 将方法与url映射
 * @author a
 * @Date 2018年2月6日 上午12:56:53
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodMappingUrl {

	/**
	 * 请求的url
	 * @return
	 */
	String methodMappedUrl() default "/";
}
