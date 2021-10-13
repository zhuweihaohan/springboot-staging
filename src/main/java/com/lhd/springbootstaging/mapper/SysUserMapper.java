package com.lhd.springbootstaging.mapper;

import com.lhd.springbootstaging.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lhd
 * @since 2021-10-13
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selUserByName(String username);
}
