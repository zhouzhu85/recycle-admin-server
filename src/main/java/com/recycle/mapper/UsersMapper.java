package com.recycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recycle.model.TbUsers;

import java.util.List;

public interface UsersMapper extends BaseMapper<TbUsers> {
    void insertUsers(TbUsers users);
    List<TbUsers> findAllUsers();
    Integer findAllUsersCount();
}
