package com.ccw.sframework.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccw.sframework.beans.ControllerBean;
import com.ccw.sframework.beans.ControllerBeanContainer;
import com.ccw.sframework.classcontainer.SimpleClassContainer;
import com.ccw.sframework.classloader.DiskClassLoader;
import com.ccw.sframework.classloader.PackageClassScanner;
import com.ccw.sframework.classloader.SimpleClassScanner;
import com.ccw.sframework.controller.FirstController;
import com.ccw.sframework.weaver.BeanDispatcher;
import com.ccw.sframework.weaver.BeanInitializer;

public class test {

	
	@Test
	public void doTest() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException{
		List<String> paths = new ArrayList<String>();
		paths.add("com.ccw.sframework.controller");
	
		SimpleClassContainer scontainer = new SimpleClassContainer(paths);

		System.out.println(scontainer.getClassSet().size());
		ControllerBeanContainer controllerBeanContainer = new ControllerBeanContainer();
		
		
		BeanDispatcher.DispatchController(scontainer, controllerBeanContainer);
		
		Map<Class<?>, ControllerBean> controllerbeans = controllerBeanContainer.getControllerbeans();
		
		System.out.println(controllerbeans.size());
		
		ControllerBean firstcontrollerbean = controllerbeans.get(FirstController.class);
		
		FirstController firstcontroller = (FirstController) firstcontrollerbean.getInitialbean()[0];
		
		firstcontroller.doMethod();
		
	}
}
