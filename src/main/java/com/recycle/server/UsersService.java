package com.recycle.server;

import com.recycle.model.Users;

import java.util.List;

public interface UsersService {
    void insertUsers(Users users);
    List<Users> findAllUsers();
    Integer findAllUsersCount();
}
