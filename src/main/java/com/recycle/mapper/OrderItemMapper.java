package com.recycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recycle.model.TbOrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: zhouzhu
 * @date: 2022/3/31 17:04
 * @description: 子订单
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<TbOrderItem> {
    /**
     * 根据分类id和用户id查询一个月内的各个分类重量报表
     * @param categoryId
     * @param userId
     * @return
     */
    List<Integer> findCategoryWeightReport(String categoryId, String userId);
}
