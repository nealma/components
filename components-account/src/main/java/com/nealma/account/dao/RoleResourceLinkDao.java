package com.nealma.account.dao;

import com.nealma.framework.dao.BaseDao;
import com.nealma.framework.model.RoleResourceLink;
import com.nealma.framework.model.UserRoleLink;
import org.apache.ibatis.datasource.DataSourceException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by neal.ma on 5/18/16.
 */
@Repository
public interface RoleResourceLinkDao extends BaseDao<RoleResourceLink> {

    public List<RoleResourceLink> fetchByRoleIds(final List<UserRoleLink> userRoleLinks) throws DataSourceException;
}
