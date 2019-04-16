package com.ccw.sframework.beans;

public abstract class SmartBean {
	String beanIdentifier;
	String []beannames;
	Object[] initialbean;
	public String[] getBeannames() {
		return beannames;
	}
	public void setBeannames(String[] beannames) {
		this.beannames = beannames;
	}
	public String getBeanIdentifier() {
		return beanIdentifier;
	}
	public void setBeanIdentifier(String beanIdentifier) {
		this.beanIdentifier = beanIdentifier;
	}
	public Object[] getInitialbean() {
		return initialbean;
	}
	public void setInitialbean(Object[] initialbean) {
		this.initialbean = initialbean;
	}
	
	
}
