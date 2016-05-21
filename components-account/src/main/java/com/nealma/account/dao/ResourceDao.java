package com.nealma.account.dao;

import com.nealma.framework.dao.BaseDao;
import com.nealma.framework.model.Resource;
import com.nealma.framework.model.RoleResourceLink;
import org.apache.ibatis.datasource.DataSourceException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by neal.ma on 5/18/16.
 */
@Repository
public interface ResourceDao extends BaseDao<Resource> {
    public List<Resource> fetchByIds(List<RoleResourceLink> roleResourceLinks) throws DataSourceException;
}
