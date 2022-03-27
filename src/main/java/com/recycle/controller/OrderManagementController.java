package com.recycle.controller;

import com.recycle.model.Category;
import com.recycle.server.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("orderManagement")
public class OrderManagementController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("index")
    public ModelAndView indexPage(ModelAndView modelAndView){
        modelAndView.setViewName("/page/order/orderManagement");
        return modelAndView;
    }
    @GetMapping("addEdit")
    public ModelAndView addEdit(ModelAndView modelAndView){
        List<Category> categoryList=categoryService.findAll();
        List<Object> newCategoryList=new ArrayList();
        for (Category category : categoryList) {
            Map<String,Object> categoryMap=new HashMap();
            categoryMap.put("unitValue",category.getUnitValue());
            categoryMap.put("unitName",(category.getCategoryName()+"（"+category.getUnitValue()+"/"+category.getUnitName()+"）"));
            newCategoryList.add(categoryMap);
        }
        modelAndView.addObject("newCategoryList",newCategoryList);
        modelAndView.setViewName("/page/order/addEdit");
        return modelAndView;
    }
}
