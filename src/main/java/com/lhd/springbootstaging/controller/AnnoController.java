package com.lhd.springbootstaging.controller;

import com.lhd.springbootstaging.enums.REnum;
import com.lhd.springbootstaging.utils.Assert;
import com.lhd.springbootstaging.utils.ResultUtil;
import com.lhd.springbootstaging.utils.ShiroUtil;
import com.lhd.springbootstaging.vo.Result;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 * date: 2018/4/7
 */
@RestController
@RequestMapping("/anno")
@Slf4j
public class AnnoController {

    @PostMapping("/login")
    public Result login(@RequestParam Map<String,String> map){

        Assert.isBlank(map.get("account"),"账号不能为空");
        Assert.isBlank(map.get("password"),"密码不能为空");


        try{
            Subject subject = ShiroUtil.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(map.get("account"),map.get("password"));
            subject.login(token);
        }catch (UnknownAccountException e) {
           return ResultUtil.error(REnum.USERNAME_OR_PASSWORD_ERROR.getCode(),REnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }catch (IncorrectCredentialsException e) {
           return ResultUtil.error(REnum.USERNAME_OR_PASSWORD_ERROR.getCode(),REnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }catch (DisabledAccountException e) {
           return ResultUtil.error(REnum.ACCOUNT_DISABLE.getCode(),REnum.ACCOUNT_DISABLE.getMessage());
        }catch (AuthenticationException e) {
           return ResultUtil.error(REnum.AUTH_ERROR.getCode(),REnum.AUTH_ERROR.getMessage());
        }

        return ResultUtil.success();
    }

    /**
     * 退出
     * @return
     */
    @GetMapping("/logout")
    public Result logout(){
        ShiroUtil.logout();
        return ResultUtil.success("安全退出！");
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/notLogin")
    public Result notLogin(){
       return ResultUtil.error(REnum.NOT_LOGIN.getCode(),REnum.NOT_LOGIN.getMessage());
    }
}
