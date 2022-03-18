package com.recycle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecycleResult {
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
    private Long count;
    /**
     * 数据集合
     */
    private Object data;

    public RecycleResult(Integer code, String msg, Object data) {
        this.code=code;
        this.msg=msg;
        this.data= data;
    }

    public static RecycleResult ok(){
        return new RecycleResult(0,"查询成功",null);
    }
    public static RecycleResult ok(Object data,Long count){
        return new RecycleResult(0,"查询成功",count,data);
    }
    public static RecycleResult ok(String msg,Object data){
        return new RecycleResult(0,msg,data);
    }
    public static RecycleResult ok(String msg){
        return new RecycleResult(0,msg,null);
    }
    public static RecycleResult error(String msg){
        return new RecycleResult(-1,msg,null);
    }
}
