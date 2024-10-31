package com.sky.aop;

import com.sky.annotation.AutoFillAnnotation;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect // 标识为切面类
@Component // 标识为Spring的组件
public class AutoFillAspect {

    // 切入点
    // execution(* com.sky.mapper.*.*(..)): 匹配 com.sky.mapper 包及其子包下的所有类的所有方法。
    // @annotation(com.sky.annotation.AutoFillAnnotation): 仅匹配那些带有 @AutoFillAnnotation 注解的方法。
    // 拦截 com.sky.mapper 包及其子包下所有带有 @AutoFillAnnotation 注解的方法。
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFillAnnotation)")
    public void autoPointCut() {
    }

    // 前置通知，在通知中进行公共字段赋值
    @Before("autoPointCut()")
    public void autoFill(JoinPoint joinPoint) {

        // 获取方法签名（包括方法的所有信息）
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获取方法上的注解对象
        AutoFillAnnotation autoFillAnnotation = methodSignature.getMethod().getAnnotation(AutoFillAnnotation.class);
        // 获取注解的值（操作类型）
        OperationType type = autoFillAnnotation.value();

        // 获取被拦截的方法的参数
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return; // 如果参数为空，直接结束方法
        }
        // 约定：第一个参数为实体类对象
        Object entity = joinPoint.getArgs()[0];

        // 准备要赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        switch (type) {
            case UPDATE:
                try {
                    Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                    Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                    // 调用赋值方法
                    setUpdateTime.invoke(entity, now);
                    setUpdateUser.invoke(entity, currentId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case INSERT:
                try {
                    Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                    Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                    Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                    Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                    // 调用赋值方法
                    setCreateTime.invoke(entity, now);
                    setUpdateTime.invoke(entity, now);
                    setUpdateUser.invoke(entity, currentId);
                    setCreateUser.invoke(entity, currentId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
