package com.mine.info.controller;

import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(
       @RequestParam(value = "name", required = false,
                          defaultValue = "World") String name, Model model) {
        model.addAttribute("user", name);
        return "hello";
    }
}