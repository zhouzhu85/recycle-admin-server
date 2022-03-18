package com.recycle.server.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.recycle.mapper.CategoryMapper;
import com.recycle.model.Category;
import com.recycle.model.Users;
import com.recycle.model.vo.CategoryVo;
import com.recycle.server.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhouzhu
 * @date 2022/1/22
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private Snowflake snowflake;

    @Override
    public void saveCategory(Category category) {
        if (StringUtils.isEmpty(category.getId())){
            category.setId(snowflake.nextIdStr());
            categoryMapper.insert(category);
        }else{
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("id",category.getId());
            categoryMapper.update(category,queryWrapper);
        }
    }

    @Override
    public IPage<Users> findCategoryByPage(CategoryVo categoryVo) {
        Page page=new Page();
        page.setCurrent(categoryVo.getPage());
        page.setSize(categoryVo.getLimit());
        QueryWrapper queryWrapper=new QueryWrapper();
        if (StringUtils.isNotEmpty(categoryVo.getCategoryName())) {
            queryWrapper.like("category_name", categoryVo.getCategoryName());
        }
        IPage<Users> usersIPage = categoryMapper.selectPage(page, queryWrapper);
        return usersIPage;
    }
}
