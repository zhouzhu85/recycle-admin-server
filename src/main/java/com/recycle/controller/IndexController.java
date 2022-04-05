package com.recycle.controller;

import com.recycle.model.RecycleResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    @GetMapping("chart_data")
    @ResponseBody
    public RecycleResult getChartData(){
        List<String> thirtyDayList=new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        //最近一个月
        c.add(Calendar.MONTH, -1);
        //包含当天
        c.add(Calendar.DAY_OF_MONTH,1);
        int count = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < count; i++) {
            thirtyDayList.add(simpleDateFormat.format(c.getTime())+"日");
            c.add(Calendar.DAY_OF_MONTH,1);
        }
        thirtyDayList.remove(0);
        return RecycleResult.ok("查询成功",thirtyDayList);
    }

}
