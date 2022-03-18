package com.recycle.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.recycle.model.Category;
import com.recycle.model.RecycleResult;
import com.recycle.model.Users;
import com.recycle.model.vo.CategoryVo;
import com.recycle.model.vo.UsersVo;
import com.recycle.server.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("categoryManagement")
public class CategoryManagementController {

    @Resource
    private CategoryService categoryService;


    @GetMapping("index")
    public ModelAndView indexPage(ModelAndView modelAndView){
        modelAndView.setViewName("/page/category/categoryManagement");
        return modelAndView;
    }

    @GetMapping("addEdit")
    public ModelAndView addEditPage(ModelAndView modelAndView){
        modelAndView.setViewName("/page/category/addEdit");
        return modelAndView;
    }

    @PostMapping("save")
    @ResponseBody
    public RecycleResult save(Category category){
        categoryService.saveCategory(category);
        return RecycleResult.ok("保存成功");
    }
    @GetMapping("list")
    @ResponseBody
    public RecycleResult getUsersList(CategoryVo categoryVo){
        IPage<Users> categoryPageList = categoryService.findCategoryByPage(categoryVo);
        return RecycleResult.ok(categoryPageList.getRecords(),categoryPageList.getTotal());
    }
}
