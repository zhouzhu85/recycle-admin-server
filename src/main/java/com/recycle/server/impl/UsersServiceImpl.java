package com.recycle.server.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.recycle.mapper.UsersMapper;
import com.recycle.model.Users;
import com.recycle.model.vo.UsersVo;
import com.recycle.server.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private Snowflake snowflake;

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

    @Override
    public IPage<Users> findUsersByPage(UsersVo usersVo) {
        Page page=new Page();
        page.setCurrent(usersVo.getPage());
        page.setSize(usersVo.getLimit());
        QueryWrapper queryWrapper=new QueryWrapper();
        if (StringUtils.isNotEmpty(usersVo.getUserName())) {
            queryWrapper.like("user_name", usersVo.getUserName());
        }
        if (StringUtils.isNotEmpty(usersVo.getPhone())){
            queryWrapper.eq("phone",usersVo.getPhone());
        }
        IPage<Users> usersIPage = usersMapper.selectPage(page, queryWrapper);
        return usersIPage;
    }

    @Override
    public void saveOrUpdate(Users users) {
        String id = users.getId();
        if (id==null){
            String uuid = snowflake.nextIdStr();
            users.setId(uuid);
            usersMapper.insert(users);
        }else {
            QueryWrapper<Users> wrapper=new QueryWrapper<>();
            wrapper.eq("id",users.getId());
            usersMapper.update(users,wrapper);
        }
    }

    @Override
    public Users findUsersById(Long id) {
        return usersMapper.selectById(id);
    }

    @Override
    public void deleteUsersById(List<String> idList) {
        usersMapper.deleteBatchIds(idList);
    }
}
