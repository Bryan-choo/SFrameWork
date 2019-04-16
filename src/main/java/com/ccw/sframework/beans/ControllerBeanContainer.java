package com.ccw.sframework.beans;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Todo ControllerBeanContainer����
 * @author a
 * @Date 2018��2��6�� ����12:45:15
 */
public class ControllerBeanContainer extends BeanContainer {

	private static Logger logger = LoggerFactory.getLogger(ControllerBeanContainer.class);
	
	private Map<Class<?>,ControllerBean>controllerbeans;
	
	
	public ControllerBeanContainer(){
		this.controllerbeans = new HashMap<Class<?>,ControllerBean>();
		logger.info("��ʼ��ControllerBeanContainer ����...");
	}
	
	/**
	 * ��ȡ���������е�ControllerBean
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
			logger.warn("ControllerBeanContainer�������Ѵ���ָ����Bean:{}",controllerbean.getBeannames()[0]);
			return false;
		}
		controllerbeans.put(controllerbean.getClass(), controllerbean);
		return true;
	}
	
	@Override
	SmartBean getBean(String key) {
		ControllerBean controllerbean = controllerbeans.get(key);
		if(null==controllerbean)
			logger.warn("�Ҳ���ָ����ControllerBean ͨ��:{}",key);
		return controllerbean;
	}

	@Override
	boolean deleteBean(String key) {
		if(!controllerbeans.containsKey(key)){
			logger.warn("ɾ��ControllerBeanʧ��,�Ҳ���ָ����ControllerBean ͨ��:{}",key);
			return false;
		}
		ControllerBean controllerbean = controllerbeans.get(key);
		controllerbeans.remove(controllerbean);
		return true;
	}

	
	@Override
	boolean deleteBean(SmartBean smartbean) {
		if(!controllerbeans.containsKey(smartbean)){
			logger.warn("ɾ��ControllerBeanʧ��,�Ҳ���ָ����ControllerBean :{}",smartbean.getBeannames()[0]);
			return false;
		}
		controllerbeans.remove(smartbean);
		return true;
	}


}
