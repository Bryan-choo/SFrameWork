package com.ccw.sframework.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IocContainer {

	private ControllerBeanContainer controllerBeanContainer;

	private static Logger logger = LoggerFactory.getLogger(IocContainer.class);
	
	public IocContainer(){
		logger.info("³õÊ¼»¯IOCÈÝÆ÷...");
		controllerBeanContainer = new ControllerBeanContainer();
		
	}
	
	public ControllerBeanContainer getControllerBeancontainer() {
		return controllerBeanContainer;
	}

	public void setControllerBeancontainer(
			ControllerBeanContainer controllerBeancontainer) {
		this.controllerBeanContainer = controllerBeancontainer;
	}
	
	
}
