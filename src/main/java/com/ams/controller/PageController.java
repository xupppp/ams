package com.ams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PageController {
	@RequestMapping("/{path}")
    public String intoJsp(@PathVariable String path){
        return path;
    }
    
	
}
