package com.onlineLibrary.aspect;


import com.onlineLibrary.annotation.AutoFill;
import com.onlineLibrary.constant.AutoFillConstant;
import com.onlineLibrary.context.BaseContext;
import com.onlineLibrary.enumeration.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 切面 实现公共字段(共性操作)逻辑处理
 */
@Aspect
@Component
public class AutoFillAspect {

    //切入点
    @Pointcut("execution(* com.onlineLibrary.mapper.*.*(..)) && @annotation(com.onlineLibrary.annotation.AutoFill)")
    public void autoFillPointCut() {
    }

    //通知
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {

        //获取被拦截方法上的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); //方法签名对象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获得注解
        OperationType operationType = autoFill.value(); //获得操作类型
        //获取拦截对象实体
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        Object entity = args[0];

        //准备赋值数据
        LocalDateTime now = LocalDateTime.now();
        Integer currentId = BaseContext.getCurrentId();

        //根据类型赋值 插入赋值4条 更新赋值两条
        if (operationType == OperationType.INSERT) {
            try {
                //拦截对象添加方法
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME,LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER,Integer.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME,LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER,Integer.class);
                //赋值
                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (operationType == OperationType.UPDATE) {
            try {
                //拦截对象添加方法
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME,LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER,Integer.class);
                //赋值
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);}
        }



    }
}
