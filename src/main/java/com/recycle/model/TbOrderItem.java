package com.recycle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: zhouzhu
 * @date: 2022/3/31 14:35
 * @description: 子订单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbOrderItem {
    /**
     * 子订单号
     */
    private String orderItemNo;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 分类id
     */
    private String categoryId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 斤数
     */
    private Long cattyNumber;
    /**
     * 回收金额
     */
    private BigDecimal amount;
    /**
     * 出售金额
     */
    private BigDecimal saleAmount;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;

}
