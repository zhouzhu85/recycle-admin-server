package com.recycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recycle.model.TbOrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zhouzhu
 * @date: 2022/3/31 17:04
 * @description: 子订单
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<TbOrderItem> {
}
