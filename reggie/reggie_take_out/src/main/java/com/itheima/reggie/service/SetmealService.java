package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.pojo.Setmeal;
import com.itheima.reggie.pojo.dto.SetmealDto;

public interface SetmealService extends IService<Setmeal> {
    void saveSetmeal(SetmealDto setmealDto);

    SetmealDto getSetmealById(Long id);
}
