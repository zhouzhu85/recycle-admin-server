package com.recycle.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 废品分类
 * @author zhouzhu
 * @date 2022/1/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo extends BaseVo{
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
}
