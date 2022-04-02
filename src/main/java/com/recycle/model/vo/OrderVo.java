package com.recycle.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhouzhu
 * @date 2022/3/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVo extends BaseVo{
    private String orderNo;
    private String userName;
    private String receiptDate;
}
