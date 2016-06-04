package com.nealma.account.service.impl;

import com.nealma.account.dao.*;
import com.nealma.account.service.ManagerService;
import com.nealma.framework.annocation.SystemDaoLayerLog;
import com.nealma.framework.annocation.SystemServiceLayerLog;
import com.nealma.framework.commons.Constants;
import com.nealma.framework.commons.StringUtil;
import com.nealma.framework.model.*;
import org.apache.ibatis.datasource.DataSourceException;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by neal.ma on 5/18/16.
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private UserRoleLinkDao userRoleLinkDao;
    @Autowired
    private RoleResourceLinkDao roleResourceLinkDao;

    @Override
    @SystemServiceLayerLog(description = "获取用户信息")
    public User fetchByUsername(String username) throws DataSourceException {
        if (username == null) {
            throw new IllegalArgumentException("username can't be null.");
        }
        User user = userDao.fetchByUsername(username);
        if (user == null) return null;

        List<UserRoleLink> userRoleLinks = userRoleLinkDao.fetchByUserId(user.getId());
        if (userRoleLinks != null) {
            List<Role> roles = roleDao.fetchByIds(userRoleLinks);
            user.setRoles(roles);
        }

        List<RoleResourceLink> roleResourceLinks = roleResourceLinkDao.fetchByRoleIds(userRoleLinks);
        if (roleResourceLinks != null) {
            List<Resource> resources = resourceDao.fetchByIds(roleResourceLinks);
            user.setResources(resources);
        }

        return user;
    }

    @Override
    public User insertForRegister(String username, String password) throws DataSourceException {
        if (username == null || password == null) {
            throw new IllegalArgumentException("username and password can't be null.");
        }
        //判断用户名是否存在
        User userFromDb = userDao.fetchByUsername(username);
        if (userFromDb != null) {
            throw new DataSourceException("username exists");
        }
        //处理密码加密
        String salt = StringUtil.randomAlphanumeric(6);
        String encrypted = new Sha512Hash(password, salt, Constants.HASH_ITERATOR_TIMES).toHex();
        User user = new User();
        user.setUsername(username);
        user.setPassword(encrypted);
        user.setSalt(salt);
        return userDao.fetch(userDao.insert(user));
    }
}
