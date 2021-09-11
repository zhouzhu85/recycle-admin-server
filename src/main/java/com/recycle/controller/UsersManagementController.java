package com.recycle.controller;

import com.recycle.model.RecycleResult;
import com.recycle.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("usersManagement")
public class UsersManagementController {
    @GetMapping("index")
    public ModelAndView indexPage(ModelAndView modelAndView){
        modelAndView.setViewName("/page/usersManagement");
        return modelAndView;
    }

    @GetMapping("list")
    @ResponseBody
    public RecycleResult<Users> getUsersList(){

        return new RecycleResult<>();
    }
}
