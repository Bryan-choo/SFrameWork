package com.ccw.sframework.weaver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccw.sframework.annotations.AutoAssembly;
import com.ccw.sframework.annotations.Controller;
import com.ccw.sframework.annotations.MethodMappingUrl;
import com.ccw.sframework.beans.ControllerBean;
import com.ccw.sframework.beans.SmartBean;

public class BeanInitializer {

	public static Map<Class<?>,SmartBean> initializedBeans = new HashMap<Class<?>, SmartBean>();
	public static Logger logger = LoggerFactory.getLogger(BeanInitializer.class);
	
//	public static void Initialize(Set<Class<?>> classset,Map<String,Object> initializedBeans){
//		
//		InitializeController
//	}
	
	/**
	 * 初始化ControllerBean
	 * @param classset
	 * @param controllerBeans
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void InitializeController(Set<Class<?>> classset,Map<Class<?>,ControllerBean>controllerBeans) throws InstantiationException, IllegalAccessException{
		
		for(Class<?>cls:classset){
			if(cls.isAnnotationPresent(Controller.class)){
//				classset.remove(cls);
				Map<String,Method> methods = new HashMap<String, Method>();
				
				Controller controller = cls.getAnnotation(Controller.class);
				String mappedurl = controller.mappedurl();
				Object controllerobject = cls.newInstance();
				
				Method[] allmethods = cls.getDeclaredMethods();
				for(Method method:allmethods){
					if(method.isAnnotationPresent(MethodMappingUrl.class)){
						MethodMappingUrl methodMappingUrl = method.getAnnotation(MethodMappingUrl.class);
						String methodMappedUrl = methodMappingUrl.methodMappedUrl();
						methods.put(methodMappedUrl, method);
					}
				}
				
				Field[] allfields = cls.getDeclaredFields();
				
				for(Field field:allfields){
					
					if(field.isAnnotationPresent(AutoAssembly.class)){
						
					}
				}
				ControllerBean controllerBean = new ControllerBean(mappedurl);
				controllerBean.setBeanIdentifier(mappedurl);
				controllerBean.setMethods(methods);
				controllerBean.setInitialbean(new Object[]{controllerobject});
				controllerBeans.put(cls, controllerBean);
				if(initializedBeans.containsKey(cls)){
					initializedBeans.remove(cls);
					initializedBeans.put(cls, controllerBean);
				}else{
					initializedBeans.put(cls,controllerBean);
				}
			}
		}
	}
	
	
	public static Object InitializeBean(Class<?> cls,Map<Class<?>,SmartBean>initializedBeans) throws InstantiationException, IllegalAccessException{
		
		if(initializedBeans.containsKey(cls)){
			SmartBean smartBean = initializedBeans.get(cls);
			if(smartBean.getInitialbean().length>1){
				logger.error("指定的Bean数量超过一个,无法选择 Bean: {}",cls.getName());
				return null;
			}else{
				return smartBean.getInitialbean()[0];
			}
		}else{
			Object obj = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			for(Field field:fields){
				if(field.isAnnotationPresent(AutoAssembly.class)){
					Class<?> type = field.getType();
					Object arg;
					if(type.isPrimitive()){
						arg = 0;
					}else{
						arg = InitializeBean(type,initializedBeans);
					}
					field.set(obj, arg);
				}
			}
			return obj;
		}
	}
	
}
