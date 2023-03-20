package com.lxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxy.common.CustomException;
import com.lxy.entity.Category;
import com.lxy.entity.Dish;
import com.lxy.entity.Setmeal;
import com.lxy.mapper.CategoryMapper;
import com.lxy.service.CategoryService;
import com.lxy.service.DishService;
import com.lxy.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        dishQueryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(dishQueryWrapper);
        //查询当前分类是否关联了菜品，如果已经关联，抛出一个业务异常
        if (count>0){
            //已关联菜品，抛出一个业务异常
            throw new CustomException("当前分类关联了菜品，不能删除");
        }

        //查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        setmealQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count1 = setmealService.count(setmealQueryWrapper);
        if (count1>0){
            //已关联套餐，抛出一个业务异常
            throw new CustomException("当前分类关联了套餐，不能删除");
        }

        //正常删除分类
        super.removeById(id);

    }
}
