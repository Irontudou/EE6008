package com.onlineLibrary.exception;

import com.onlineLibrary.result.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {
    /*
    @ExceptionHandler
    public Result handleException(Exception e) {
        return Result.error("程序出错了，请联系管理员~");
    }
     */
    //所有异常

    @ExceptionHandler
    public Result handleDuplicateException(DuplicateKeyException e) {
        String message = e.getMessage();             //获取异常
        int i = message.indexOf("Duplicate entry");  //异常位置
        String mess = message.substring(i);          //截取
        String[] arr = mess.split(" ");        //分割
        return Result.error(arr[2]+"已存在");    //取值
    }
    //唯一字段重复异常


}
