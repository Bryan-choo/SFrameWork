package com.ccw.sframework.classloader;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Todo ɨ�貢���ذ��е�������
 * @author a
 * @Date 2018��2��5�� ����4:52:53
 */
public class PackageClassScanner {

	public static Logger logger = LoggerFactory.getLogger(PackageClassScanner.class);
	public PackageClassScanner(){};

	/**
	 * ɨ�������package������jar��
	 * @param packagepaths
	 * @return
	 */
	public Set<Class<?>> scanClasses(String[] packagepaths){
		
		Set<Class<?>>classset;
		
		if(packagepaths!=null){
			classset = new HashSet<Class<?>>();
			for(String path:packagepaths){
				try {
					Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path.replace(".", "/"));
					while(resources.hasMoreElements()){
						URL resource = resources.nextElement();
						if("file".equals(resource.getProtocol())){
//							if(resource.getPath().endsWith(".class")){
								addClass(path,resource.getPath(),classset);
//							}
						}
//							else if("jar".equals(resource.getProtocol())){
//							JarURLConnection jarconnection = (JarURLConnection) resource.openConnection();
//							
//							JarFile jarfile = jarconnection.getJarFile();
//							
//							Enumeration<JarEntry> entries = jarfile.entries();
//							
//							while(entries.hasMoreElements()){
//								JarEntry jarentry = entries.nextElement();
//								String name = jarentry.getName();
//								if(name.charAt(0)=='/'){
//									name = name.substring(1);
//								}
//							}
//						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return classset;
		}else{
			return null;
		}
	}
	
	
	/**
	 * ���ݸ����İ�����ȫ·���ݹ������
	 * @param packagename
	 * @param path
	 * @param classset
	 */
	public void addClass(String packagename,String path,Set<Class<?>>classset){
		File file = new File(path);
		if(!file.exists()){
			logger.error("·�� {} ������",path);
			return;
		}
		File[] files = file.listFiles(new FileFilter(){
			public boolean accept(File f) {
				return (f.isFile()&&f.getName().endsWith(".class"))||f.isDirectory();
			}
		});
		
		for(File subfile:files){
			//.class�ļ�
			if(subfile.isFile()){
				String filename = subfile.getName();
				filename = filename.substring(0, filename.lastIndexOf(".class"));
				String classname = packagename+"."+filename;
				doAddClass(classname,classset);
			}else{
				//��·�����ݹ����.class�ļ�
				String filename = subfile.getName();
				String subpackagename = packagename+"."+filename;
				addClass(subpackagename,subfile.getAbsolutePath(),classset);
			}
		}
	}
	
	
	/**
	 * ͨ��Class.forName(String...classname)���������
	 * @param classname
	 * @param classset
	 */
	public void doAddClass(String classname,Set<Class<?>> classset){
		if(null!=classname&&!("".equals(classname))){
			try {
				Class<?> cls = Class.forName(classname);
				if(!classset.contains(cls)){
					classset.add(cls);
					logger.info("�ɹ������� {}",classname);
				}
			} catch (ClassNotFoundException e) {
				logger.error("������ {} ʧ��,�಻����",classname);
				e.printStackTrace();
			}
		}
	}

}
