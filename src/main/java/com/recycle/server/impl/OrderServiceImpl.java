package com.recycle.server.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.recycle.mapper.OrderMapper;
import com.recycle.model.TbOrder;
import com.recycle.server.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
            orderMapper.update(order,wrapper);
        }
    }
}
