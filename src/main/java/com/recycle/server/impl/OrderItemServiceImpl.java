package com.recycle.server.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.recycle.mapper.OrderItemMapper;
import com.recycle.model.TbOrderItem;
import com.recycle.model.vo.CategoryUserYearReportVo;
import com.recycle.server.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void deleteOrderItem(String orderNoStr) {
        List<String> orderNoStrList = Arrays.asList(orderNoStr.split(","));
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.in("order_no",orderNoStrList);
        orderItemMapper.delete(wrapper);
    }

    @Override
    public List<Integer> findCategoryWeightReport(String categoryId, String userId) {
        userId="".equals(userId)?null:userId;
        return orderItemMapper.findCategoryWeightReport(categoryId,userId);
    }

    @Override
    public List<CategoryUserYearReportVo> findCategoryWeightYearReport(String categoryId, String userId) {
        userId="".equals(userId)?null:userId;
        return orderItemMapper.findCategoryWeightYearReport(categoryId,userId);
    }

    @Override
    public BigDecimal getAllCattyNumber() {
        QueryWrapper<TbOrderItem> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("sum(catty_number) as catty_number");
        TbOrderItem tbOrderItem = orderItemMapper.selectOne(queryWrapper);
        Long cattyNumber = tbOrderItem.getCattyNumber();
        //斤转成吨
        BigDecimal divide = new BigDecimal(cattyNumber).divide(new BigDecimal(2000));
        return divide;
    }
}
