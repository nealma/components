package com.nealma.account.dao;

import com.nealma.framework.dao.BaseDao;
import com.nealma.framework.model.Role;
import com.nealma.framework.model.UserRoleLink;
import org.apache.ibatis.datasource.DataSourceException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by neal.ma on 5/18/16.
 */
@Repository
public interface RoleDao extends BaseDao<Role> {

    public List<Role> fetchByIds(List<UserRoleLink> userRoleLinks) throws DataSourceException;
}
