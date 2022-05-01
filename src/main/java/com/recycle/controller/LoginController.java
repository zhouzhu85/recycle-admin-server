package com.recycle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhouzhu
 * @date 2022/5/1
 */
@Controller
public class LoginController {

    @RequestMapping("login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("/index/login");
        return modelAndView;
    }
}
