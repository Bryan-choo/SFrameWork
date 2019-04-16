package com.ccw.sframework.beans;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Todo ControllerBean
 * @author a
 * @Date 2018��2��5�� ����10:48:26
 */
public class ControllerBean extends SmartBean {

	/**
	 * Controllerӳ��url
	 */
	private String mappedurl;
	
	/**
	 * Controllerӳ�䴦����
	 */
	private Map<String,Method> methods;
	
	public void seteMappedurl(String mappedurl){
		this.mappedurl = mappedurl;
	}
	
	public String getMappedurl(){
		return this.mappedurl;
	}
	
	public ControllerBean(String mappedurl){
		this.mappedurl = mappedurl;
	}

	public Map<String, Method> getMethods() {
		return methods;
	}

	public void setMethods(Map<String, Method> methods) {
		this.methods = methods;
	}
	
	
}
