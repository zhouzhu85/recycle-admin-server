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
     * 分类名称
     */
    private String categoryName;
    /**
     * 单位名称
     */
    private String unitName;
    /**
     * 单位值
     */
    private BigDecimal unitValue;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
}
