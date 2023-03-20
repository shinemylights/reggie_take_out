package com.lxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxy.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
