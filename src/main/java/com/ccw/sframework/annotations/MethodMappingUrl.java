package com.ccw.sframework.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Todo ��������urlӳ��
 * @author a
 * @Date 2018��2��6�� ����12:56:53
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodMappingUrl {

	/**
	 * �����url
	 * @return
	 */
	String methodMappedUrl() default "/";
}
