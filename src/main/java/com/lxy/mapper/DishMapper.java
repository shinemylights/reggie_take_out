package com.lxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
