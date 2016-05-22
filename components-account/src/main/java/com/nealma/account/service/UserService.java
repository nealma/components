package com.nealma.account.service;

import com.nealma.framework.model.User;
import com.nealma.framework.service.BaseService;
import org.apache.ibatis.datasource.DataSourceException;

/**
 * Created by neal.ma on 5/18/16.
 */
public interface UserService extends BaseService<User> {

    public User fetchByUsername(String username) throws DataSourceException;

}
