package com.recycle.mapper;

import com.recycle.model.Users;

import java.util.List;

public interface UsersMapper {
    void insertUsers(Users users);
    List<Users> findAllUsers();
    Integer findAllUsersCount();
}
