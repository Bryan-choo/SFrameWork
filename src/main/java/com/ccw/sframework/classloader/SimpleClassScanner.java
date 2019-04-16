package com.ccw.sframework.classloader;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleClassScanner implements ClassScanner {

	public static Logger logger = LoggerFactory.getLogger(SimpleClassScanner.class);
	private DiskClassLoader diskclassloader;
	
	public SimpleClassScanner() {
		diskclassloader = new DiskClassLoader();
	}

	public Set<Class<?>> scanClassFromExternalDisk(String path) {

		Set<Class<?>> classset = new HashSet<Class<?>>();
		try {
			addClassbydisk(classset,path);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return classset;
	}

	public Set<Class<?>> scanClassFromPackage(String path) {
		Set<Class<?>> classset = new HashSet<Class<?>>();
		try {
			addClassbypackage(classset,path);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return classset;
	}

	
	public void addClassbydisk(Set<Class<?>> classset, String rootpath) throws ClassNotFoundException {
		if(null==rootpath)
			return;
		if(null==diskclassloader)
			return;
		File rootfile = new File(rootpath);
		if(rootfile.exists()){
			File[] files = rootfile.listFiles(new FileFilter(){
				public boolean accept(File filepath) {
					return (filepath.isFile()&&filepath.getName().endsWith(".class"))||filepath.isDirectory();
				}
			});
			
			for(File file:files){
				if(file.isFile()){
					String absolutefilepath = file.getAbsolutePath();
					Class<?> cls = diskclassloader.loadClass(absolutefilepath);
					if(!classset.contains(cls)){
						logger.info("成功添加类{}",cls.getName());
						classset.add(cls);
					}
				}else{
					addClassbydisk(classset,file.getAbsolutePath());
				}
			}
		}
	}
	
	public void addClassbypackage(Set<Class<?>> classset,String basepackage) throws IOException, ClassNotFoundException{
		if(null==basepackage)
			return;
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String basedir = basepackage.replace(".", "/");
		Enumeration<URL> resources = classloader.getResources(basedir);
		while(resources.hasMoreElements()){
			URL url = resources.nextElement();
			if(null!=url){
				String protocol = url.getProtocol();
				if("file".equals(protocol)){
					String packagepath = url.getPath().replaceAll("%20", "");
					addClassbydisk(classset,packagepath);
				}else if("jar".equals(protocol)){
					JarURLConnection jarconnection = (JarURLConnection) url.openConnection();
					String dir = url.getPath().substring(0,url.getPath().lastIndexOf(".jar"));
					if(null!=jarconnection){
						JarFile jarfile = jarconnection.getJarFile();
						if(null!=jarfile){
							Enumeration<JarEntry> entries = jarfile.entries();
							while(entries.hasMoreElements()){
								JarEntry jarentry = entries.nextElement();
								String jarname = jarentry.getName();
								if(jarname.endsWith(".class")){
									System.out.println(jarname);
									Class<?> cls = diskclassloader.loadClass(dir+File.separator+jarname);
									System.out.println(cls.getName());
								}
							}
						}
					}
				}
			}
		}
		
		
	}

}
