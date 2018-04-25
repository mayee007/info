package com.mine.info.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	// public static final String name = "name";
	// Can't pass this as a variable, getting this error
	// java element value must be a constant expression

	public static final String DEFAULT_VALUE = "World";
	public static final String URL = "/hello";
	private final String returnValue = "hello";
	private final String attributeName = "user";

    @GetMapping(URL)
    public String sayHello(
       @RequestParam(value = "name", required = false,
                     defaultValue = DEFAULT_VALUE) String name, Model model) {
        model.addAttribute(attributeName, name);
        return returnValue;
    }
}
