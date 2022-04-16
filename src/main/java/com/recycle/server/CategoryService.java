package com.recycle.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.recycle.model.TbCategory;
import com.recycle.model.TbUsers;
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
    void saveCategory(TbCategory category);

    /**
     * 分页查询分类
     * @param categoryVo
     * @return
     */
    IPage<TbUsers> findCategoryByPage(CategoryVo categoryVo);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    TbCategory findCategoryById(String id);

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
    List<TbCategory> findAll();

    /**
     * 根据id字符串查询分类
     * @param categoryId
     * @return
     */
    List<TbCategory> findCategoryListById(String categoryId);

    /**
     * 根据分类id和回收客id查询一个月内的重量图表
     * @param categoryId
     * @param userId
     * @return
     */
    List<Integer> findCategoryUserReport(String categoryId, String userId);
}
