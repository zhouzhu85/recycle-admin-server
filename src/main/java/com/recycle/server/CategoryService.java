package com.recycle.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.recycle.model.Category;
import com.recycle.model.Users;
import com.recycle.model.vo.CategoryVo;

import java.util.List;

/**
 * @author zhouzhu
 * @date 2022/1/22
 */
public interface CategoryService {
    /**
     * 保存分类
     * @param category
     */
    void saveCategory(Category category);

    /**
     * 分页查询分类
     * @param categoryVo
     * @return
     */
    IPage<Users> findCategoryByPage(CategoryVo categoryVo);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    Category findCategoryById(String id);

    /**
     * 根据id删除分类
     * @param id
     */
    void deleteById(String id);

    /**
     * 批量删除分类
     * @param idsStr
     */
    void batchDeleteById(List<String> idsStr);

    /**
     * 查询所有分类
     * @return
     */
    List<Category> findAll();
}
