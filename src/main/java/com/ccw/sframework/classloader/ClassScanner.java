package com.ccw.sframework.classloader;

import java.util.Set;

public interface ClassScanner {
	
	Set<Class<?>> scanClassFromExternalDisk(String path);
	
	Set<Class<?>> scanClassFromPackage(String path);
	
	
}
