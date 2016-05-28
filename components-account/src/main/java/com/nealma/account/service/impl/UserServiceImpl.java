package com.nealma.account.service.impl;

import com.nealma.account.dao.UserDao;
import com.nealma.account.service.UserService;
import com.nealma.framework.annocation.DataSource;
import com.nealma.framework.model.User;
import org.apache.ibatis.datasource.DataSourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by neal.ma on 5/18/16.
 */
@Service
@DataSource(name = DataSource.READ)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void insert(User user) throws DataSourceException {
        userDao.insert(user);
    }

    @Override
    public void update(User user) throws DataSourceException {
        userDao.update(user);
    }

    @Override
    public User fetch(Long id) throws DataSourceException {
        return userDao.fetch(id);
    }

    @Override
    public List<User> list() throws DataSourceException {
        return userDao.list();
    }

    @Override
    public User fetchByUsername(String username) throws DataSourceException {
        return userDao.fetchByUsername(username);
    }
}
