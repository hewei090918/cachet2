package com.cachet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index")
public class IndexController {

	/**
     * 主界面
     */
    private final String MAIN_URL = "/index";
    
    @RequestMapping(method=RequestMethod.GET)
    public String view(ModelMap model) {
    	model.addAttribute("message", "Hello World");
    	return MAIN_URL;
    }
}
