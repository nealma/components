package com.nealma.account.shiro.realm;

import com.nealma.account.service.ManagerService;
import com.nealma.framework.model.Resource;
import com.nealma.framework.model.Role;
import com.nealma.framework.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by neal.ma on 5/17/16.
 */
public class ShiroDaoRealm extends AuthorizingRealm {

    public static final Logger LOGGER = LoggerFactory.getLogger(ShiroDaoRealm.class);

    @Autowired
    ManagerService managerService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        if (principalCollection == null) {
            throw new AuthorizationException("principal can't be null");
        }
        /*用户的角色*/
        Set<String> roleNames = new HashSet<String>();
        /*用户的权限*/
        Set<String> resourceNames = new HashSet<String>();
        /*根据用户名得到用户*/
        User user = managerService.fetchByUsername((String) principalCollection.getPrimaryPrincipal());

        List<Role> roles = user.getRoles();
        if (roles != null) {
            for (Role role : roles) {
                roleNames.add(role.getName());
            }
        }
        List<Resource> resources = user.getResources();
        if (resources != null) {
            for (Resource resource : resources) {
                resourceNames.add(resource.getPermission());
            }
        }

        LOGGER.info("roleNames={} \n resourceNames={}", roleNames, resourceNames);
        /*授权信息，设置角色和权限*/
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(resourceNames);
        return info;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws org.apache.shiro.authc.AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        String username = (String) token.getPrincipal();
        // 调用ManagerService查询是否有此用户
        User user = managerService.fetchByUsername(username);
        if (user == null) {
            // 抛出 帐号找不到异常
            throw new UnknownAccountException();
        }
        // 判断帐号是否锁定
//        if (Boolean.TRUE.equals(user.getLocked())) {
//            // 抛出 帐号锁定异常
//            throw new LockedAccountException();
//        }

        LOGGER.info("username={}, password={}, credential={}, salt={}", token.getPrincipal(), user.getPassword(), token.getCredentials(), user.getSalt());
        /*查找到的用户与Token里面的用户进行比较  匹配则登陆成功，不匹配则登陆失败*/
        return new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                new SerializableByteSource(user.getCredentialSalt().getBytes()),
                getName()
        );
    }

    /**
     * 退出登录即点击logout时清除登陆的所有信息
     *
     * @param principals
     */
    @Override
    protected void doClearCache(PrincipalCollection principals) {
        Object principal = principals.getPrimaryPrincipal();
//        LOGGER.debug("principal={}", getCacheManager().getCache(principal.toString()).toString());
//        LOGGER.debug("principal={}", principal.toString());
        super.doClearCache(principals);
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
}

class SerializableByteSource extends SimpleByteSource implements Serializable {
    public SerializableByteSource(byte[] bytes) {
        super(bytes);
    }
}