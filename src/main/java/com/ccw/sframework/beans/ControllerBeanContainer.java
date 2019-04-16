package com.ccw.sframework.beans;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Todo ControllerBeanContainer容器
 * @author a
 * @Date 2018年2月6日 上午12:45:15
 */
public class ControllerBeanContainer extends BeanContainer {

	private static Logger logger = LoggerFactory.getLogger(ControllerBeanContainer.class);
	
	private Map<Class<?>,ControllerBean>controllerbeans;
	
	
	public ControllerBeanContainer(){
		this.controllerbeans = new HashMap<Class<?>,ControllerBean>();
		logger.info("初始化ControllerBeanContainer 容器...");
	}
	
	/**
	 * 获取容器中所有的ControllerBean
	 * @return
	 */
	public Map<Class<?>, ControllerBean> getControllerbeans() {
		return controllerbeans;
	}

	public void setControllerbeans(Map<Class<?>, ControllerBean> controllerbeans) {
		this.controllerbeans = controllerbeans;
	}

	
	boolean addBean(ControllerBean controllerbean){
		if(controllerbeans.containsValue(controllerbean)){
			logger.warn("ControllerBeanContainer容器中已存在指定的Bean:{}",controllerbean.getBeannames()[0]);
			return false;
		}
		controllerbeans.put(controllerbean.getClass(), controllerbean);
		return true;
	}
	
	@Override
	SmartBean getBean(String key) {
		ControllerBean controllerbean = controllerbeans.get(key);
		if(null==controllerbean)
			logger.warn("找不到指定的ControllerBean 通过:{}",key);
		return controllerbean;
	}

	@Override
	boolean deleteBean(String key) {
		if(!controllerbeans.containsKey(key)){
			logger.warn("删除ControllerBean失败,找不到指定的ControllerBean 通过:{}",key);
			return false;
		}
		ControllerBean controllerbean = controllerbeans.get(key);
		controllerbeans.remove(controllerbean);
		return true;
	}

	
	@Override
	boolean deleteBean(SmartBean smartbean) {
		if(!controllerbeans.containsKey(smartbean)){
			logger.warn("删除ControllerBean失败,找不到指定的ControllerBean :{}",smartbean.getBeannames()[0]);
			return false;
		}
		controllerbeans.remove(smartbean);
		return true;
	}


}
