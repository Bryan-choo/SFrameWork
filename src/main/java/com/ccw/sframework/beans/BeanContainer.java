package com.ccw.sframework.beans;

import java.util.Map;

public abstract class BeanContainer {

	abstract SmartBean getBean(String key);
	
	abstract boolean deleteBean(String key);
	
	abstract boolean deleteBean(SmartBean smartbean);
	
	
}
