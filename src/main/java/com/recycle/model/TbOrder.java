package com.recycle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: zhouzhu
 * @date: 2022/3/31 14:29
 * @description: 订单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbOrder {
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 收据时间
     */
    private Date receiptDate;
    /**
     * 总计金额
     */
    private BigDecimal allAmount;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;

}
