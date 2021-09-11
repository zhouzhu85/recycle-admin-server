package com.recycle.model;

import java.util.List;

public class RecycleResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据总数
     */
    private Integer count;
    /**
     * 数据集合
     */
    private List<T> data;

}
