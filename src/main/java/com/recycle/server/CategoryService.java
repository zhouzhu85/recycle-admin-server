package com.recycle.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.recycle.model.Category;
import com.recycle.model.Users;
import com.recycle.model.vo.CategoryVo;

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
}
