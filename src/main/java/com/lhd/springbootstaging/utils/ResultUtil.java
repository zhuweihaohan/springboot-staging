package com.lhd.springbootstaging.utils;


import com.lhd.springbootstaging.vo.Result;

/**
 * 对象模型工具类
 * author: 小宇宙
 * date: 2018/4/5
 */
public class ResultUtil {

    public static Result success(Object object){
        Result r = new Result();
        r.setCode(200);
        r.setMsg("ok");
        r.setData(object);
        return r;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code, String msg){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
