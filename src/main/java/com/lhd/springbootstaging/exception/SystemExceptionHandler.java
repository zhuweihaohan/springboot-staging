package com.lhd.springbootstaging.exception;

import com.lhd.springbootstaging.enums.REnum;
import com.lhd.springbootstaging.utils.ResultUtil;
import com.lhd.springbootstaging.vo.Result;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * date: 2018/4/7
 */
@Slf4j
@RestControllerAdvice
public class SystemExceptionHandler {

    /**
     * 缺少权限异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(AuthorizationException e){
        log.error(REnum.NOT_PERMSSION.getMessage());
        return ResultUtil.error(REnum.NOT_PERMSSION.getCode(),REnum.NOT_PERMSSION.getMessage());
    }
}
