package com.recycle.server.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.recycle.mapper.OrderItemMapper;
import com.recycle.model.TbOrderItem;
import com.recycle.server.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: zhouzhu
 * @date: 2022/3/31 17:10
 * @description: 子订单
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private Snowflake snowflake;

    @Override
    public void batchSaveOrderItem(List<TbOrderItem> orderItemList) {
        for (TbOrderItem orderItem : orderItemList) {
            if (orderItem.getOrderItemNo()==null){
                String uuid = snowflake.nextIdStr();
                orderItem.setOrderItemNo(uuid);
                orderItem.setCreateDate(new Date());
                orderItem.setUpdateDate(new Date());
                orderItemMapper.insert(orderItem);
            }else {
                QueryWrapper wrapper=new QueryWrapper();
                wrapper.eq("order_item_no",orderItem.getOrderItemNo());
                orderItem.setUpdateDate(new Date());
                orderItemMapper.update(orderItem,wrapper);
            }
        }
    }

    @Override
    public List<TbOrderItem> findListByOrderNo(String orderNo) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("order_no",orderNo);
        return orderItemMapper.selectList(wrapper);
    }
}
