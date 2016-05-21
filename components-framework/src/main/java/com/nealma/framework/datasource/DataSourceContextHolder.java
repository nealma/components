package com.nealma.framework.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nealpc on 11/10/15.
 */
public class DataSourceContextHolder {
    public static final Logger LOGGER = LoggerFactory.getLogger(DataSourceContextHolder.class);
    public static final String WRITE = "write";
    public static final String READ = "news";

    public static final ThreadLocal<String> contextHolder = new ThreadLocal();

    public static void setDataSourceType(String dataSourceType) {
        LOGGER.info("[set] {}", Thread.currentThread().getName());
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        String type = contextHolder.get();
        LOGGER.info("[get] {}", Thread.currentThread().getName());
        return type;
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
