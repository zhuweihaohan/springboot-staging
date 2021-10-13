package com.lhd.springbootstaging.utils;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lhd.springbootstaging.exception.SystemException;

/**
 * 数据校验工具类
 * date: 2018/4/7
 */
public class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new SystemException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new SystemException(message);
        }
    }
}
