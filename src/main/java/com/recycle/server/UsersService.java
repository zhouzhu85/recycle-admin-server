package com.recycle.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.recycle.model.TbUsers;
import com.recycle.model.vo.UsersVo;

import java.util.List;

public interface UsersService{
    void insertUsers(TbUsers users);
    List<TbUsers> findAllUsers();
    Integer findAllUsersCount();
    IPage<TbUsers> findUsersByPage(UsersVo usersVo);
    void saveOrUpdate(TbUsers users);

    TbUsers findUsersById(Long id);

    void deleteUsersById(List<String> idList);
}
