package com.mine.info.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	public static final String name = "name";
	public static final String defaultValue = "World"; 
	public static final String url = "/hello"; 
	final String returnValue = "hello"; 
	final String attributeName = "user"; 
	
    @GetMapping(url)
    public String sayHello(
       @RequestParam(value = "name", required = false,
                          defaultValue = defaultValue) String name, Model model) {
        model.addAttribute(attributeName, name);
        return returnValue;
    }
}
