package com.lhd.springbootstaging.service.impl;

import com.lhd.springbootstaging.entity.SysUser;
import com.lhd.springbootstaging.mapper.SysUserMapper;
import com.lhd.springbootstaging.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhd
 * @since 2021-10-13
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
@Resource
private SysUserMapper sysUserMapper;
    @Override
    public SysUser selUserByName(String username) {
        return sysUserMapper.selUserByName(username);
    }
}
