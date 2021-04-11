package com.kuang.helloshiro.config;

import com.kuang.helloshiro.pojo.User;
import com.kuang.helloshiro.service.UserService;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权方法");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        //获取当前用户
        User user = (User) subject.getPrincipal();
        //给当前用户授权
        info.addStringPermission(user.getPerms());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证方法");
       UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.queryByName(username);
     if(user==null){
    return null;
}

      //密码认证shiro做

        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
