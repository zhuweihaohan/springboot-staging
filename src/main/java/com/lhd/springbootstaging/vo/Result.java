package com.lhd.springbootstaging.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Result
 * @Description 返回的对象
 * @Author luohongde
 * @Date 2021/10/1311:30 上午
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T>{

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体数据
     */
    private T data;
}
