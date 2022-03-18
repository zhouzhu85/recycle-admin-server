package com.recycle.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersVo extends BaseVo{
    private Long id;
    private String userName;
    private String phone;
    private String sex;
}
