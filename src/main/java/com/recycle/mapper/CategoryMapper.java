package com.recycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recycle.model.TbCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhouzhu
 * @date 2022/1/22
 */
@Mapper
public interface CategoryMapper extends BaseMapper<TbCategory> {
}
