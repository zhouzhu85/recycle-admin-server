package com.recycle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("orderManagement")
public class OrderManagementController {
    @GetMapping("index")
    public ModelAndView indexPage(ModelAndView modelAndView){
        modelAndView.setViewName("/page/order/orderManagement");
        return modelAndView;
    }
    @GetMapping("addEdit")
    public ModelAndView addEdit(ModelAndView modelAndView){
        modelAndView.setViewName("/page/order/addEdit");
        return modelAndView;
    }
}
