package com.recycle.server;

import com.recycle.model.TbOrder;

/**
 * @author: zhouzhu
 * @date: 2022/3/31 15:22
 * @description: 订单service
 */
public interface OrderService {
    void saveOrder(TbOrder order);
}
