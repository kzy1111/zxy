package com.itheima.reggie.handler;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.itheima.reggie.utils.ThreadLocalUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", ThreadLocalUtil.getCurrentId());
        metaObject.setValue("updateUser", ThreadLocalUtil.getCurrentId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", ThreadLocalUtil.getCurrentId());
    }
}
