package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 用于描述注解的使用范围：方法上
@Retention(RetentionPolicy.RUNTIME) // 注解保留时间：运行时
public @interface AutoFillAnnotation {

    // 操作类型
    OperationType value();

}
