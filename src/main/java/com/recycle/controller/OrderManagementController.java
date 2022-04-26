package com.recycle.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.recycle.model.*;
import com.recycle.model.vo.OrderVo;
import com.recycle.server.CategoryService;
import com.recycle.server.OrderItemService;
import com.recycle.server.OrderService;
import com.recycle.server.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("orderManagement")
public class OrderManagementController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("index")
    public ModelAndView indexPage(ModelAndView modelAndView){
        modelAndView.setViewName("/page/order/orderManagement");
        return modelAndView;
    }
    @GetMapping("addEdit")
    public ModelAndView addEdit(ModelAndView modelAndView,String orderNo){
        List<TbUsers> usersList = usersService.findAllUsers();
        List<Object> newUsersList=new ArrayList<>();
        for (TbUsers users : usersList) {
            Map<String,Object> userMap=new HashMap<>();
            userMap.put("nameId",users.getUserName()+"_"+users.getId());
            userMap.put("userName",users.getUserName());
            newUsersList.add(userMap);
        }
        modelAndView.addObject("newUsersList",newUsersList);
        List<TbCategory> categoryList=categoryService.findAll();
        List<Object> newCategoryList=new ArrayList();
        for (TbCategory category : categoryList) {
            Map<String,Object> categoryMap=new HashMap();
            categoryMap.put("unitValue",category.getUnitValue()+"-"+category.getId());
            categoryMap.put("unitName",(category.getCategoryName()+"（"+category.getUnitValue()+"/"+category.getUnitName()+"）"));
            categoryMap.put("saleUnitValue",category.getSaleUnitValue());
            newCategoryList.add(categoryMap);
        }
        if (orderNo!=null){
            TbOrder order=orderService.findOneOrder(orderNo);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            List<TbOrderItem> orderItemList=orderItemService.findListByOrderNo(orderNo);
            modelAndView.addObject("orderReceiptDate",simpleDateFormat.format(order.getReceiptDate()));
            modelAndView.addObject("order",order);
            modelAndView.addObject("orderItemList",orderItemList);
        }
        modelAndView.addObject("newCategoryList",newCategoryList);
        modelAndView.setViewName("/page/order/addEdit");
        return modelAndView;
    }
    @PostMapping("save")
    @ResponseBody
    public RecycleResult saveOrder(String dataJson){
        try {
            Map<String,Object> paramMap = JSONUtil.toBean(dataJson, Map.class);
            TbOrder order=new TbOrder();
            String userNameId = paramMap.get("userNameId").toString();
            String[] userNameIdArray = userNameId.split("_");
            order.setUserName(userNameIdArray[0]);
            order.setUserId(userNameIdArray[1]);
            order.setAllAmount(new BigDecimal(paramMap.get("allAmount").toString()));
            order.setReceiptDate(new SimpleDateFormat("yyyy-MM-dd").parse(paramMap.get("receiptDate").toString()));
            String orderNo = paramMap.get("orderNo").toString();
            if (StringUtils.isNotEmpty(orderNo)){
                order.setOrderNo(orderNo);
            }
            orderService.saveOrder(order);
            List<TbOrderItem> orderItemList=new ArrayList<>();
            paramMap.forEach((key,value)->{
                if (key.contains("category")){
                    TbOrderItem orderItem=new TbOrderItem();
                    String categoryId = key.split("_")[1];
                    orderItem.setCategoryId(categoryId);
                    orderItem.setOrderNo(order.getOrderNo());
                    orderItem.setUserId(userNameIdArray[1]);
                    orderItemList.add(orderItem);
                }
            });
            for (TbOrderItem orderItem : orderItemList) {
                paramMap.forEach((key,value)->{
                    if (key.contains("amount") && key.contains(orderItem.getCategoryId())){
                        orderItem.setAmount(new BigDecimal(value.toString()));
                    }
                    if (key.contains("catty") && key.contains(orderItem.getCategoryId())){
                        TbCategory category = categoryService.findCategoryById(orderItem.getCategoryId());
                        orderItem.setCattyNumber(Long.parseLong(value.toString()));
                        orderItem.setSaleAmount(category.getSaleUnitValue().multiply(new BigDecimal(value.toString())));
                    }
                    if (key.contains("orderItemNo") && key.contains(orderItem.getCategoryId())){
                        orderItem.setOrderItemNo(value.toString());
                    }
                });
            }
            orderItemService.batchSaveOrderItem(orderItemList);
        } catch (Exception e) {
            e.printStackTrace();
            return RecycleResult.error("系统异常");
        }
        return RecycleResult.ok();
    }
    @GetMapping("list")
    @ResponseBody
    public RecycleResult getOrderList(OrderVo orderVo){
        IPage<TbOrder> orderIPage=orderService.findOrderList(orderVo);
        return RecycleResult.ok(orderIPage.getRecords(),orderIPage.getTotal());
    }
    @GetMapping("delete")
    @ResponseBody
    public RecycleResult deleteOrder(String orderNoStr){
        if (StringUtils.isEmpty(orderNoStr)){
            return RecycleResult.error("订单号为空");
        }
        orderService.deleteOrder(orderNoStr);
        orderItemService.deleteOrderItem(orderNoStr);
        return RecycleResult.ok("删除成功");
    }
}
