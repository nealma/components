package com.nealma.account.security;

import com.nealma.account.service.UserService;
import com.nealma.framework.domain.User;
import org.apache.ibatis.datasource.DataSourceException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by neal.ma on 5/17/16.
 */
public class AccountRealm extends AuthorizingRealm {
    public static final Logger LOGGER = LoggerFactory.getLogger(AccountRealm.class);

    @Autowired
    UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 身份认证
     *
     * @param authenticationToken
     * @return
     * @throws org.apache.shiro.authc.AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        try {
            User user = userService.fetchByUsername(username);
            if(user == null) {
                throw new UnknownAccountException();
            }
            return new SimpleAuthenticationInfo(user.getUsername(), // 用户名
                    user.getPassword(), // 密码
                    getName() // realm name
            );
        } catch (DataSourceException e) {
           LOGGER.debug("authentication is error by {}.", e.getMessage());
           throw new DataSourceException();
        }
    }
}
