package com.lhd.springbootstaging.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Description TODO
 * @Author luohongde
 * @Date 2021/10/1111:28 上午
 * @Version 1.0
 **/
@Configuration
public class ShiroConfig {
    //shiroFilterFacoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /**
         * anno:无需认证就可以访问
         * authc:必须认证了才能访问
         * user:必须拥有记住我功能才能使用
         * perms:拥有对某个资源的权限才能访问
         * role:拥有某个角色权限才能访问
         */
        //拦截
        Map<String,String> filterMap=new LinkedHashMap<>();
        //授权，正常情况下未授权会跳转到未授权页面
//        filterMap.put("/user/add","perms[user:add]");
//        filterMap.put("/user/update","perms[user:update]");
        //filterMap.put("/user/add","authc");
        filterMap.put("/springbootstaging/sys-user/getAllUser","perms[getAllUser]");
        filterMap.put("/springbootstaging/sys-user/*","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录的请求
        bean.setLoginUrl("/anno/notLogin");
        //设置未授权的请求
        bean.setUnauthorizedUrl("/springbootstaging/sys-user/Noauth");
        return bean;
    }
    //DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") com.lhd.springbootstaging.config.UserRealm userRealm){
      DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
      //关联UserRealm
        securityManager.setRealm(userRealm);
      return securityManager;
    }
    //创建 realm对象，需要自定义
    @Bean(name = "userRealm")
   public com.lhd.springbootstaging.config.UserRealm userRealm(){
       return new com.lhd.springbootstaging.config.UserRealm();
   }
}
