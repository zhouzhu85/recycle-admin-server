package com.recycle.server;

import com.recycle.model.TbOrderItem;

import java.util.List;

/**
 * @author: zhouzhu
 * @date: 2022/3/31 17:09
 * @description: 子订单
 */
public interface OrderItemService {
    /**
     * 批量保存子订单
     * @param orderItemList
     */
    void batchSaveOrderItem(List<TbOrderItem> orderItemList);

    /**
     * 根据订单号查询子订单
     * @param orderNo
     * @return
     */
    List<TbOrderItem> findListByOrderNo(String orderNo);
}
