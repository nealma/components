package com.nealma.account.service;

import com.nealma.framework.model.User;
import org.apache.ibatis.datasource.DataSourceException;

/**
 * Created by neal.ma on 5/18/16.
 */
public interface ManagerService {

    public User insertForRegister(String username, String password) throws DataSourceException;

    public User fetchByUsername(String username) throws DataSourceException;
}
