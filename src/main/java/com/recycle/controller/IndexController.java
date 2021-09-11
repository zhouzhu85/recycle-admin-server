package com.recycle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.addObject("userName","张三");
        modelAndView.setViewName("/index/index.html");
        return modelAndView;
    }
    @GetMapping("/welcome")
    public ModelAndView welcome(ModelAndView modelAndView){
        modelAndView.setViewName("/page/welcome.html");
        return modelAndView;
    }

}
