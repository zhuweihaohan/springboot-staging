package com.lhd.springbootstaging.config;

import com.lhd.springbootstaging.entity.SysUser;
import com.lhd.springbootstaging.enums.ForbiddenEnum;
import com.lhd.springbootstaging.enums.REnum;
import com.lhd.springbootstaging.service.impl.SysUserServiceImpl;
import com.lhd.springbootstaging.utils.Assert;
import com.lhd.springbootstaging.utils.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName UserRealm
 * @Description TODO
 * @Author luohongde
 * @Date 2021/10/1111:30 上午
 * @Version 1.0
 **/
//自定义的
    @Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SysUserServiceImpl userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");
        //拿到当前登录的用户
        Subject subject=SecurityUtils.getSubject();
        SysUser user = (SysUser)subject.getPrincipal();//拿到user对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("用户权限:"+user.getPerms());
        info.addStringPermission(user.getPerms());
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证");
         //用户名密码数据库取

        UsernamePasswordToken userToken= (UsernamePasswordToken) authenticationToken;
        //连接真实数据库
        SysUser user = userService.selUserByName(userToken.getUsername());
        if(user==null)
        {
            log.error(REnum.UNkNOWN_ACCOUNT.getMessage()+"SysUser = {}"+user);
            throw new UnknownAccountException();}
        //密码认证
        //密码可以加密    MD5盐值加密
        //user传递过去，授权时可得到
        if(ForbiddenEnum.DISABLE.getCode().toString().equals(user.getForbidden())){
            log.error(REnum.UNkNOWN_ACCOUNT.getMessage()+"SysUser = {}"+user);
            throw new DisabledAccountException();
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        return simpleAuthenticationInfo;
        //return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
    /**
     * 密码验证服务
     * @param credentialsMatcher
     */
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5HashedCredentialsMatcher = new HashedCredentialsMatcher();
        md5HashedCredentialsMatcher.setHashAlgorithmName(ShiroUtil.hashAlgorithmName);
        md5HashedCredentialsMatcher.setHashIterations(ShiroUtil.hashIterations);
        md5HashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        super.setCredentialsMatcher(md5HashedCredentialsMatcher);
    }
}
