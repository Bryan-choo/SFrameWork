package com.ccw.sframework.classcontainer;

import java.util.List;
import java.util.Set;

public interface SmartClassContainer {
	
	Set<Class<?>> getClassSet();
	
	void loadClasses(List<String> paths);
}
