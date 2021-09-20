package com.recycle.controller;

import com.recycle.model.RecycleResult;
import com.recycle.model.Users;
import com.recycle.server.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("usersManagement")
public class UsersManagementController {

    @Autowired
    private UsersService usersService;

    @GetMapping("index")
    public ModelAndView indexPage(ModelAndView modelAndView){
        modelAndView.setViewName("/page/usersManagement");
        return modelAndView;
    }

    @GetMapping("add")
    public ModelAndView addPage(ModelAndView modelAndView){
        modelAndView.setViewName("/page/table/add");
        return modelAndView;
    }

    @GetMapping("list")
    @ResponseBody
    public RecycleResult getUsersList(){
        List<Users> allUsers = usersService.findAllUsers();
        Integer allUsersCount = usersService.findAllUsersCount();
        return RecycleResult.ok(allUsers,allUsersCount);
    }

    @PostMapping("save")
    @ResponseBody
    public RecycleResult saveUsers(Users users){
        usersService.insertUsers(users);
        return RecycleResult.ok("保存成功");
    }
}
