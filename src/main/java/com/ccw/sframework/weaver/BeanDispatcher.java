package com.ccw.sframework.weaver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccw.sframework.beans.ControllerBean;
import com.ccw.sframework.beans.ControllerBeanContainer;
import com.ccw.sframework.classcontainer.SimpleClassContainer;

/**
 * @Todo ���ڽ����ز�ʵ������ɺ��Bean���䵽ָ����������
 * @author a
 * @Date 2018��2��6�� ����1:06:59
 */
public class BeanDispatcher {

	public static Logger logger = LoggerFactory.getLogger(BeanDispatcher.class);
	
	public static void DispatchController(SimpleClassContainer simpleClassContainer,ControllerBeanContainer controllerBeanContainer) throws InstantiationException, IllegalAccessException{
		Set<Class<?>> classset = simpleClassContainer.getClassSet();
		
		for(Class<?> cls:classset){
			System.out.println(cls.getName());
		}
		
		Map<Class<?>,ControllerBean>controllerBeans = new HashMap<Class<?>, ControllerBean>();
		BeanInitializer.InitializeController(classset, controllerBeans);
		controllerBeanContainer.setControllerbeans(controllerBeans);
	}
}
