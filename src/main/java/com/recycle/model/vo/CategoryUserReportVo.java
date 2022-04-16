package com.recycle.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分类-回收客-报表图
 * @author zhouzhu
 * @date 2022/4/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUserReportVo {
    /**
     * 数据名称
     */
    private String name;
    /**
     * 数据类型
     */
    private String type="line";
    /**
     * 数据堆叠
     */
    private String stack="总量";
    /**
     * 数据
     */
    private List<Integer> data;
}
