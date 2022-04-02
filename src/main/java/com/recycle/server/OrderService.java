package com.recycle.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.recycle.model.TbOrder;
import com.recycle.model.vo.OrderVo;

/**
 * @author: zhouzhu
 * @date: 2022/3/31 15:22
 * @description: 订单service
 */
public interface OrderService {
    /**
     * 保存订单
     * @param order
     */
    void saveOrder(TbOrder order);

    /**
     * 查询订单
     * @param orderVo
     * @return
     */
    IPage<TbOrder> findOrderList(OrderVo orderVo);

    /**
     * 查询单个订单
     * @param orderNo
     * @return
     */
    TbOrder findOneOrder(String orderNo);

    /**
     * 批量删除订单
     * @param orderNoStr
     */
    void deleteOrder(String orderNoStr);
}
