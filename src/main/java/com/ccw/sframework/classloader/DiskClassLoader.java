package com.ccw.sframework.classloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiskClassLoader extends ClassLoader{

	public static Logger logger = LoggerFactory.getLogger(DiskClassLoader.class);
//	private String path;
	
	
	@Override
	protected Class<?> findClass(String path) throws ClassNotFoundException {
		byte[] data = loadClassData(path);
		if(null!=data){
			return defineClass(null,data,0,data.length);
		}
		return super.findClass(path);
	}
	
	public byte[] loadClassData(String path){
		Path filepath = Paths.get(path);
		if(Files.exists(filepath)&&Files.isReadable(filepath))
			try {
				return Files.readAllBytes(filepath);
			} catch (IOException e) {
				logger.error("读取类{}失败",path);
				e.printStackTrace();
			}
		logger.error("类{}不存在",path);
		return null;
	}
	
}
