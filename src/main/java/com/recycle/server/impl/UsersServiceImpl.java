package com.recycle.server.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.recycle.mapper.UsersMapper;
import com.recycle.model.TbUsers;
import com.recycle.model.vo.UsersVo;
import com.recycle.server.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private Snowflake snowflake;

    @Override
    public void insertUsers(TbUsers users) {
        usersMapper.insertUsers(users);
    }

    @Override
    public List<TbUsers> findAllUsers() {
        QueryWrapper wrapper=new QueryWrapper();
        return usersMapper.selectList(wrapper);
    }

    @Override
    public Integer findAllUsersCount() {
        return usersMapper.findAllUsersCount();
    }

    @Override
    public IPage<TbUsers> findUsersByPage(UsersVo usersVo) {
        Page page=new Page();
        page.setCurrent(usersVo.getPage());
        page.setSize(usersVo.getLimit());
        QueryWrapper queryWrapper=new QueryWrapper();
        if (StringUtils.isNotEmpty(usersVo.getUserName())) {
            queryWrapper.like("user_name", usersVo.getUserName());
        }
        if (StringUtils.isNotEmpty(usersVo.getPhone())){
            queryWrapper.like("phone",usersVo.getPhone());
        }
        IPage<TbUsers> usersIPage = usersMapper.selectPage(page, queryWrapper);
        return usersIPage;
    }

    @Override
    public void saveOrUpdate(TbUsers users) {
        String id = users.getId();
        if (id==null){
            String uuid = snowflake.nextIdStr();
            users.setId(uuid);
            users.setCreateDate(new Date());
            users.setUpdateDate(new Date());
            usersMapper.insert(users);
        }else {
            QueryWrapper<TbUsers> wrapper=new QueryWrapper<>();
            wrapper.eq("id",users.getId());
            users.setUpdateDate(new Date());
            usersMapper.update(users,wrapper);
        }
    }

    @Override
    public TbUsers findUsersById(Long id) {
        return usersMapper.selectById(id);
    }

    @Override
    public void deleteUsersById(List<String> idList) {
        usersMapper.deleteBatchIds(idList);
    }
}
