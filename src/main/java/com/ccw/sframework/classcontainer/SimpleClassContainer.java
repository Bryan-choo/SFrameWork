package com.ccw.sframework.classcontainer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccw.sframework.classloader.ClassScanner;
import com.ccw.sframework.classloader.PackageClassScanner;
import com.ccw.sframework.classloader.SimpleClassScanner;


/**
 * @Todo 简单类容器
 * @author a
 * @Date 2018年2月5日 下午5:07:10
 */
public class SimpleClassContainer implements SmartClassContainer {

	public static Logger logger = LoggerFactory.getLogger(SimpleClassContainer.class);
	private Set<Class<?>> classset;
	private Set<String> packages;
	private PackageClassScanner classsanner;
	
	/**
	 *构造方法,如果不指定packages,那packages将默认指定为"/";
	 * @param packages 指定的包
	 */
	public SimpleClassContainer(List<String>...packages){
		
		logger.info("初始化类容器...");
		this.packages = new HashSet<String>(); 
		this.classset = new HashSet<Class<?>>();
		this.classsanner = new PackageClassScanner();
		
		if(packages!=null&&packages.length!=0){
			for(List<String> subpackages:packages){
				this.packages.addAll(subpackages);
			}
		}else{
			this.packages.add("/");
		}
		
		loadClasses();
		
	}
	
	/**
	 * 获取当前容器中的类集合
	 */
	public Set<Class<?>> getClassSet() {
		return classset;
	}
	
	public void loadClasses() {
		String[] paths = packages.toArray(new String[packages.size()]);
		Set<Class<?>> scanClasses = this.classsanner.scanClasses(paths);
		this.classset = scanClasses;
	}

	public void loadClasses(List<String> paths) {
		// TODO Auto-generated method stub
		
	}

}
