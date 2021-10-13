package com.lhd.springbootstaging.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhd.springbootstaging.entity.SysUser;
import com.lhd.springbootstaging.enums.REnum;
import com.lhd.springbootstaging.mapper.SysUserMapper;
import com.lhd.springbootstaging.utils.Assert;
import com.lhd.springbootstaging.utils.ResultUtil;
import com.lhd.springbootstaging.utils.ShiroUtil;
import com.lhd.springbootstaging.vo.Result;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhd
 * @since 2021-10-13
 */
@RestController
@RequestMapping("/springbootstaging/sys-user")
@Slf4j
public class SysUserController {
 @Resource
 private SysUserMapper sysUserMapper;

    @GetMapping("/getAllUser")
    public Result<Integer> getAllUser(){
        QueryWrapper<SysUser> wrapper=new QueryWrapper<>();
        Integer number=sysUserMapper.selectCount(wrapper);
        log.info("记录数"+number);
        return ResultUtil.success(number);
    }
    @RequestMapping("/Noauth")
    @ResponseBody
    public Result<String> unauthorized(){
        return ResultUtil.error(301,"暂无权限查看此页");
    }


}
