package com.lemon.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.pojo.User;
import com.lemon.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @program: cktest
 * @description
 * @author: NiuBenQi
 * @create: 2020-02-18 20:46
 **/
public class MyRealm extends AuthorizingRealm {

    UserService userService;

    // 授权（权限管理）
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 认证逻辑
        String username = token.getPrincipal().toString();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User adUser = userService.getOne(queryWrapper);

        if (adUser != null) {
//            if (adUser.getPassword().equals(token.getCredentials())){
//            }
            return new SimpleAuthenticationInfo(username, adUser.getPassword(), getName());
        }
        return null;
    }
}
