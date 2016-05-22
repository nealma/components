package com.nealma.framework.dao;

import org.apache.ibatis.datasource.DataSourceException;

import java.util.List;

/**
 * Created by neal.ma on 5/14/16.
 *
 * @param <T>
 */
public interface BaseDao<T> {

    public Long insert(T t) throws DataSourceException;

    public Integer update(T t) throws DataSourceException;

    public T fetch(long id) throws DataSourceException;

    public List<T> list() throws DataSourceException;
}