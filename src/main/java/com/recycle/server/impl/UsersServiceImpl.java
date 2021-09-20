package com.recycle.server.impl;

import com.recycle.mapper.UsersMapper;
import com.recycle.model.Users;
import com.recycle.server.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public void insertUsers(Users users) {
        usersMapper.insertUsers(users);
    }

    @Override
    public List<Users> findAllUsers() {
        return usersMapper.findAllUsers();
    }

    @Override
    public Integer findAllUsersCount() {
        return usersMapper.findAllUsersCount();
    }
}
