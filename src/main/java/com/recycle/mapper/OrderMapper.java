package com.recycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recycle.model.TbOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zhouzhu
 * @date: 2022/3/31 15:21
 * @description: 订单mapper
 */
@Mapper
public interface OrderMapper extends BaseMapper<TbOrder> {
}
