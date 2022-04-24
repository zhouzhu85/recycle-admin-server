package com.recycle.server.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.recycle.mapper.OrderMapper;
import com.recycle.model.TbOrder;
import com.recycle.model.vo.OrderVo;
import com.recycle.server.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author: zhouzhu
 * @date: 2022/3/31 15:23
 * @description: 订单
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private Snowflake snowflake;

    @Override
    public void saveOrder(TbOrder order) {
        if (order.getOrderNo()==null){
            String uuid = snowflake.nextIdStr();
            order.setOrderNo(uuid);
            order.setCreateDate(new Date());
            order.setUpdateDate(new Date());
            orderMapper.insert(order);
        }else {
            QueryWrapper wrapper=new QueryWrapper();
            wrapper.eq("order_no",order.getOrderNo());
            order.setUpdateDate(new Date());
            orderMapper.update(order,wrapper);
        }
    }

    @Override
    public IPage<TbOrder> findOrderList(OrderVo orderVo) {
        Page page=new Page();
        page.setCurrent(orderVo.getPage());
        page.setSize(orderVo.getLimit());
        QueryWrapper queryWrapper=new QueryWrapper();
        if (StringUtils.isNotEmpty(orderVo.getOrderNo())){
            queryWrapper.eq("order_no",orderVo.getOrderNo());
        }
        if (StringUtils.isNotEmpty(orderVo.getUserName())){
            queryWrapper.like("user_name",orderVo.getUserName());
        }
        if (StringUtils.isNotEmpty(orderVo.getReceiptDate())){
            queryWrapper.eq("receipt_date",orderVo.getReceiptDate());
        }
        IPage<TbOrder> orderPage = orderMapper.selectPage(page, queryWrapper);
        return orderPage;
    }

    @Override
    public TbOrder findOneOrder(String orderNo) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("order_no",orderNo);
        return orderMapper.selectOne(wrapper);
    }

    @Override
    public void deleteOrder(String orderNoStr) {
        List<String> orderNoStrList = Arrays.asList(orderNoStr.split(","));
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.in("order_no",orderNoStrList);
        orderMapper.delete(wrapper);
    }

    @Override
    public BigDecimal getOrderAllAmount() {
        QueryWrapper<TbOrder> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("SUM(all_amount) as all_amount");
        TbOrder order = orderMapper.selectOne(queryWrapper);
        return order.getAllAmount();
    }
}
