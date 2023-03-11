package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.pojo.Category;
import com.itheima.reggie.service.CatagoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RestController
@RequestMapping("/category")
public class CatagoryController {
    @Resource
    private CatagoryService catagoryService;
    @GetMapping("/page")
    public R<Page<Category>> catagoryList(Integer page, Integer pageSize){
        Page<Category> catagoryPage=new Page<>(page,pageSize);
        LambdaQueryWrapper<Category> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Category::getSort);
        catagoryService.page(catagoryPage,lambdaQueryWrapper);
        return R.success(catagoryPage);
    }
    @PostMapping
    public R<String> insertCategory(@RequestBody Category catagory){
        catagoryService.save(catagory);
        return R.success("新增成功");
    }
    @DeleteMapping
    public R<String> removeCategory(Long ids){
        catagoryService.remove(ids);
        return R.success("删除成功");
    }

    @PutMapping
    public R<String> updateCategory(@RequestBody Category category){
        catagoryService.updateById(category);
        return R.success("修改成功");
    }

    @GetMapping("/list")
    public R<List<Category>> selectType(Integer type){
        LambdaQueryWrapper<Category> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Category::getType,type).orderByDesc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> categories = catagoryService.list(lambdaQueryWrapper);
        return R.success(categories);
    }
}
