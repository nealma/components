package com.nealma.account.dao;

import com.nealma.framework.dao.BaseDao;
import com.nealma.framework.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.datasource.DataSourceException;
import org.springframework.stereotype.Repository;

/**
 * Created by neal.ma on 5/18/16.
 */
@Repository
public interface UserDao extends BaseDao<User> {

    public User fetchByUsername(@Param("username") String username) throws DataSourceException;
}
