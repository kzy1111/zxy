package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.pojo.Category;

public interface CatagoryService extends IService<Category> {
    void remove(Long id);
}
