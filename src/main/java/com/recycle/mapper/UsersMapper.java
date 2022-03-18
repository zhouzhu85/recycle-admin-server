package com.recycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recycle.model.Users;

import java.util.List;

public interface UsersMapper extends BaseMapper<Users> {
    void insertUsers(Users users);
    List<Users> findAllUsers();
    Integer findAllUsersCount();
}
