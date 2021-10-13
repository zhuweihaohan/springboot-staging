package com.lhd.springbootstaging.service;

import com.lhd.springbootstaging.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhd
 * @since 2021-10-13
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser selUserByName(String username);
}
