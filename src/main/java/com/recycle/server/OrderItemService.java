package com.recycle.server;

import com.recycle.model.TbOrderItem;

import java.util.List;

/**
 * @author: zhouzhu
 * @date: 2022/3/31 17:09
 * @description: 子订单
 */
public interface OrderItemService {
    void batchSaveOrderItem(List<TbOrderItem> orderItemList);
}
