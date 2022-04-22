package com.recycle.server;

import com.recycle.model.TbOrderItem;
import com.recycle.model.vo.CategoryUserYearReportVo;

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

    /**
     * 批量删除子订单
     * @param orderNoStr
     */
    void deleteOrderItem(String orderNoStr);

    /**
     * 根据分类id和用户id查询一个月内的各个分类重量报表
     * @param categoryId
     * @param userId
     * @return
     */
    List<Integer> findCategoryWeightReport(String categoryId, String userId);

    /**
     * 根据分类id和用户id查询三年内的各个分类重量报表
     * @param categoryId
     * @param userId
     * @return
     */
    List<CategoryUserYearReportVo> findCategoryWeightYearReport(String categoryId, String userId);
}
