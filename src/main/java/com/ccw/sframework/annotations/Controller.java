package com.ccw.sframework.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Todo Controllerע��
 * @author a
 * @Date 2018��2��6�� ����12:55:33
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SmartComponent
public @interface Controller {

	/**
	 * controllerӳ��url
	 * @return
	 */
	String mappedurl() default "/";
}
