package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.pojo.Dish;
import com.itheima.reggie.pojo.dto.DishDto;

public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);

    DishDto getDishById(Long id);

    void updateDish(DishDto dishDto);

    void deleteDishs(Long ids[]);
}
