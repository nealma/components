package com.nealma.account.service.impl;

import com.nealma.account.dao.*;
import com.nealma.account.service.ManagerService;
import com.nealma.framework.model.*;
import org.apache.ibatis.datasource.DataSourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by neal.ma on 5/18/16.
 */
@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    private UserDao userDao;
    @Autowired
    private  RoleDao roleDao;
    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private UserRoleLinkDao userRoleLinkDao;
    @Autowired
    private RoleResourceLinkDao roleResourceLinkDao;

    @Override
    public User fetchByUsername(String username) throws DataSourceException {
        if(username == null) {
            throw new DataSourceException("username can't be null.");
        }
        User user =  userDao.fetchByUsername(username);
        if(user == null) {
            throw new DataSourceException("user not exists.");
        }

        List<UserRoleLink> userRoleLinks = userRoleLinkDao.fetchByUserId(user.getId());
        if(userRoleLinks != null) {
           List<Role> roles = roleDao.fetchByIds(userRoleLinks);
           user.setRoles(roles);
        }

        List<RoleResourceLink> roleResourceLinks = roleResourceLinkDao.fetchByRoleIds(userRoleLinks);
        if(roleResourceLinks != null) {
            List<Resource> resources = resourceDao.fetchByIds(roleResourceLinks);
            user.setResources(resources);
        }

        return user;
    }
}
