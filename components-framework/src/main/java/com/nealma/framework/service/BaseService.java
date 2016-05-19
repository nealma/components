package com.nealma.framework.service;

import org.apache.ibatis.datasource.DataSourceException;

import java.util.List;

/**
 * Created by neal.ma on 10/14/15.
 */
public interface BaseService<T> {

    public void insert(T t) throws DataSourceException;

    public void update(T t) throws DataSourceException;

    public T fetch(Long id) throws DataSourceException;

    public List<T> list() throws DataSourceException;
}
