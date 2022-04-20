package com.recycle.controller;

import com.recycle.model.RecycleResult;
import com.recycle.model.TbCategory;
import com.recycle.model.TbUsers;
import com.recycle.model.vo.CategoryUserReportVo;
import com.recycle.server.CategoryService;
import com.recycle.server.OrderItemService;
import com.recycle.server.OrderService;
import com.recycle.server.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

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

    /**
     * 报表图时间
     * @return
     */
    @GetMapping("chartDate_data")
    @ResponseBody
    public RecycleResult getChartData(){
        List<String> thirtyDayList=new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        //最近一个月
        c.add(Calendar.MONTH, -1);
        //包含当天
        c.add(Calendar.DAY_OF_MONTH,1);
        int count = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < count; i++) {
            thirtyDayList.add(simpleDateFormat.format(c.getTime()));
            c.add(Calendar.DAY_OF_MONTH,1);
        }
        thirtyDayList.remove(0);
        return RecycleResult.ok("查询成功",thirtyDayList);
    }

    /**
     * 报表图的废品分类
     */
    @GetMapping("category_user_data")
    @ResponseBody
    public RecycleResult getCategoryData(){
        Map resultMap=new HashMap<>();
        List<TbCategory> categoryList = categoryService.findAll();
        List<Map> newCategoryList = new ArrayList<>();
        List<CategoryUserReportVo> categoryUserReportVoList=new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            TbCategory tbCategory = categoryList.get(i);
            Map<String,Object> categoryMap=new HashMap<>();
            categoryMap.put("name",tbCategory.getCategoryName());
            categoryMap.put("value",tbCategory.getId());
            if (i<=4){
                categoryMap.put("selected",true);
                CategoryUserReportVo categoryUserReportVo=new CategoryUserReportVo();
                categoryUserReportVo.setName(tbCategory.getCategoryName());
                List<Integer> categoryUserReportList=orderItemService.findCategoryWeightReport(tbCategory.getId(), null);
                categoryUserReportVo.setData(categoryUserReportList);
                categoryUserReportVoList.add(categoryUserReportVo);
            }
            newCategoryList.add(categoryMap);
        }
        resultMap.put("categoryData",newCategoryList);
        List<TbUsers> usersList = usersService.findAllUsers();
        List<Map> newUsersList=new ArrayList<>();
        for (TbUsers tbUsers : usersList) {
            Map<String,Object> userMap=new HashMap<>();
            userMap.put("name",tbUsers.getUserName());
            userMap.put("value",tbUsers.getId());
            newUsersList.add(userMap);
        }
        resultMap.put("usersData",newUsersList);
        resultMap.put("categoryReportData",categoryUserReportVoList);
        return RecycleResult.ok("查询成功",resultMap);
    }

    @GetMapping("getCategoryUserReport")
    @ResponseBody
    public RecycleResult getCategoryUserReport(String categoryId,String userId){
        List<TbCategory> tbCategoryList=categoryService.findCategoryListById(categoryId);
        List<CategoryUserReportVo> categoryUserReportVoList=new ArrayList<>();
        for (TbCategory tbCategory : tbCategoryList) {
            CategoryUserReportVo categoryUserReportVo=new CategoryUserReportVo();
            categoryUserReportVo.setName(tbCategory.getCategoryName());
            List<Integer> categoryUserReportList=orderItemService.findCategoryWeightReport(tbCategory.getId(),userId);
            categoryUserReportVo.setData(categoryUserReportList);
            categoryUserReportVoList.add(categoryUserReportVo);
        }
        return RecycleResult.ok("查询成功",categoryUserReportVoList);
    }
}
