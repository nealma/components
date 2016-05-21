package com.nealma.account.shiro.realm;

import com.nealma.account.service.ManagerService;
import com.nealma.account.service.UserService;
import com.nealma.framework.model.Resource;
import com.nealma.framework.model.Role;
import com.nealma.framework.model.User;
import org.apache.ibatis.datasource.DataSourceException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        if( principalCollection == null ){
            throw new AuthorizationException("principal can't be null");
        }
        /*用户的角色*/
        Set<String> roleNames = new HashSet<String>();
        /*用户的权限*/
        Set<String> resourceNames = new HashSet<String>();
        /*根据用户名得到用户*/
        User user = managerService.fetchByUsername((String) principalCollection.getPrimaryPrincipal());

        List<Role> roles = user.getRoles();
        if(roles != null){
            for (Role role : roles){
                roleNames.add(role.getName());
            }
        }
        List<Resource> resources = user.getResources();
        if(resources != null){
            for (Resource resource : resources){
                resourceNames.add(resource.getName());
            }
        }

        /*授权信息，设置角色和权限*/
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(resourceNames);
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws org.apache.shiro.authc.AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        LOGGER.info(">>>>>>>>>>>>>>>> execute doGetAuthenticationInfo <<<<<<<<<<<<<<<<<<<<<<<<<<<");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        try {
            if(username == null){
                throw new AccountException("username can not be null.");
            }
            User user = managerService.fetchByUsername(username);
            if(user == null) {
                throw new UnknownAccountException("username not exists.");
            }

            /*查找到的用户与Token里面的用户进行比较  匹配则登陆成功，不匹配则登陆失败*/
            return new SimpleAuthenticationInfo(user.getUsername(),
                    user.getPassword(),
                    getName()
            );
        } catch (DataSourceException e) {
           LOGGER.debug("authentication is error by {}.", e.getMessage());
           throw new DataSourceException("datasource error.");
        }
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection arg0) {
        super.clearCachedAuthorizationInfo(arg0);
    }

    /**
     * 退出登录即点击logout时清除登陆的所有信息*
     * @param principals
     */
    @Override
    protected void doClearCache(PrincipalCollection principals) {
        Object principal = principals.getPrimaryPrincipal();
        getCacheManager().getCache(principal.toString());
        super.doClearCache(principals);
    }
}
