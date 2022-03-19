package com.recycle.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.recycle.model.Users;
import com.recycle.model.vo.UsersVo;

import java.util.List;

public interface UsersService{
    void insertUsers(Users users);
    List<Users> findAllUsers();
    Integer findAllUsersCount();
    IPage<Users> findUsersByPage(UsersVo usersVo);
    void saveOrUpdate(Users users);

    Users findUsersById(Long id);

    void deleteUsersById(List<String> idList);
}
