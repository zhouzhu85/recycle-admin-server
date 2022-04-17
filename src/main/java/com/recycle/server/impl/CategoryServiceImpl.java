package com.recycle.server.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.recycle.mapper.CategoryMapper;
import com.recycle.model.TbCategory;
import com.recycle.model.TbUsers;
import com.recycle.model.vo.CategoryVo;
import com.recycle.server.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    public void saveCategory(TbCategory category) {
        if (StringUtils.isEmpty(category.getId())){
            category.setId(snowflake.nextIdStr());
            category.setCreateDate(new Date());
            category.setUpdateDate(new Date());
            categoryMapper.insert(category);
        }else{
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("id",category.getId());
            categoryMapper.update(category,queryWrapper);
        }
    }

    @Override
    public IPage<TbUsers> findCategoryByPage(CategoryVo categoryVo) {
        Page page=new Page();
        page.setCurrent(categoryVo.getPage());
        page.setSize(categoryVo.getLimit());
        QueryWrapper queryWrapper=new QueryWrapper();
        if (StringUtils.isNotEmpty(categoryVo.getCategoryName())) {
            queryWrapper.like("category_name", categoryVo.getCategoryName());
        }
        IPage<TbUsers> usersIPage = categoryMapper.selectPage(page, queryWrapper);
        return usersIPage;
    }

    @Override
    public TbCategory findCategoryById(String id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public void deleteById(String id) {
        categoryMapper.deleteById(id);
    }

    @Override
    public void batchDeleteById(List<String> idsList) {
        categoryMapper.deleteBatchIds(idsList);
    }

    @Override
    public List<TbCategory> findAll() {
        QueryWrapper wrapper=new QueryWrapper();
        return categoryMapper.selectList(wrapper);
    }

    @Override
    public List<TbCategory> findCategoryListById(String categoryId) {
        String[] categoryIdArray =  categoryId.split(",");
        final List<TbCategory> tbCategoryList = categoryMapper.selectBatchIds(Arrays.asList(categoryIdArray));
        return tbCategoryList;
    }
}
