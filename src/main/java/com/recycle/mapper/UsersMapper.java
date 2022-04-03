package com.recycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recycle.model.TbUsers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UsersMapper extends BaseMapper<TbUsers> {
    void insertUsers(TbUsers users);
    List<TbUsers> findAllUsers();
    Integer findAllUsersCount();
}
