package com.recycle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 废品分类
 * @author zhouzhu
 * @date 2022/1/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbCategory {
    private String id;
    /**
     * 废品名称
     */
    private String categoryName;
    /**
     * 废品单位名称
     */
    private String unitName;
    /**
     * 回收单价
     */
    private BigDecimal unitValue;
    /**
     * 出售单价
     */
    private BigDecimal saleUnitValue;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
}
