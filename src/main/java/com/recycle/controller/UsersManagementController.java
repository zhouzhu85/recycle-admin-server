package com.recycle.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.recycle.model.RecycleResult;
import com.recycle.model.Users;
import com.recycle.model.vo.UsersVo;
import com.recycle.server.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("usersManagement")
@CrossOrigin
public class UsersManagementController {

    @Autowired
    private UsersService usersService;

    @GetMapping("index")
    public ModelAndView indexPage(ModelAndView modelAndView){
        modelAndView.setViewName("/page/users/usersManagement");
        return modelAndView;
    }

    @GetMapping("add")
    public ModelAndView addPage(ModelAndView modelAndView){
        modelAndView.setViewName("/page/users/add");
        return modelAndView;
    }

    @GetMapping("edit")
    public ModelAndView editPage(ModelAndView modelAndView,UsersVo usersVo){
        Users users=usersService.findUsersById(usersVo.getId());
        modelAndView.addObject("users",users);
        modelAndView.setViewName("/page/users/edit");
        return modelAndView;
    }

    @GetMapping("list")
    @ResponseBody
    public RecycleResult getUsersList(UsersVo usersVo){
//        List<Users> allUsers = usersService.findAllUsers();
//        Integer allUsersCount = usersService.findAllUsersCount();
        IPage<Users> usersIPage = usersService.findUsersByPage(usersVo);
        return RecycleResult.ok(usersIPage.getRecords(),usersIPage.getTotal());
    }

    @PostMapping("save")
    @ResponseBody
    public RecycleResult saveUsers(Users users){
        usersService.saveOrUpdate(users);
        return RecycleResult.ok("保存成功");
    }

    @GetMapping("delete")
    @ResponseBody
    public RecycleResult deleteUsers(String idStr){
        if (StringUtils.isEmpty(idStr)){
            return RecycleResult.error("删除客户不存在");
        }
        List<Long> idList = Arrays.asList(idStr.split(",")).stream().map(id ->
                Long.valueOf(id)).collect(Collectors.toList());
        usersService.deleteUsersById(idList);
        return RecycleResult.ok();
    }
}
