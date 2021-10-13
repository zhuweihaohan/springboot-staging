package com.lhd.springbootstaging.exception;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice(annotations = {Controller.class, RestController.class})
public class GlobalExceptionHandler {
	/**配置全局异常类捕获文件太大异常**/
	@ExceptionHandler(MultipartException.class)
    public JSONObject uploadExcepttion(MultipartException e){
		e.printStackTrace();
	    JSONObject result=new JSONObject();
	    result.put("code",201);
	    result.put("msg","支持最大上传20M文件！");
	    return result;
    }

}
