package com.ccw.sframework.controller;

import com.ccw.sframework.annotations.Controller;
import com.ccw.sframework.annotations.MethodMappingUrl;


@Controller(mappedurl="/")
public class FirstController {

	@MethodMappingUrl(methodMappedUrl="/demo")
	public void doMethod(){
		System.out.println("method map demo...");
	}
	
}
