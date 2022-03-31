package com.recycle.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.recycle.model.TbCategory;
import com.recycle.model.RecycleResult;
import com.recycle.model.TbUsers;
import com.recycle.model.vo.CategoryVo;
import com.recycle.server.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

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
    public ModelAndView addEditPage(ModelAndView modelAndView,String id){
        if (StringUtils.isNotEmpty(id)){
            TbCategory category=categoryService.findCategoryById(id);
            modelAndView.addObject("category",category);
        }
        modelAndView.setViewName("/page/category/addEdit");
        return modelAndView;
    }

    @PostMapping("save")
    @ResponseBody
    public RecycleResult save(TbCategory category){
        categoryService.saveCategory(category);
        return RecycleResult.ok("保存成功");
    }
    @GetMapping("list")
    @ResponseBody
    public RecycleResult getCategoryList(CategoryVo categoryVo){
        IPage<TbUsers> categoryPageList = categoryService.findCategoryByPage(categoryVo);
        return RecycleResult.ok(categoryPageList.getRecords(),categoryPageList.getTotal());
    }

    @GetMapping("delete")
    @ResponseBody
    public RecycleResult deleteCategory(String idsStr){
        if (StringUtils.isEmpty(idsStr)){
            return RecycleResult.error("该分类不存在");
        }
        List<String> idsList= Arrays.asList(idsStr.split(","));
        categoryService.batchDeleteById(idsList);
        return RecycleResult.ok();
    }
}
