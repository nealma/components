package com.nealma.framework.datasource;

/**
 * Created by nealpc on 11/10/15.
 */
public class DataSourceContextHolder {
    public static final String  PAY= "pay";
    public static final String NEWS= "news";

    public static final ThreadLocal<String> contextHolder = new ThreadLocal();

    public static void setDataSourceType(String dataSourceType){
        System.out.println("DataSourceContextHolder->set : " + Thread.currentThread().getName() + ", type : " + dataSourceType);
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType(){
        String type = contextHolder.get();
        System.out.println("DataSourceContextHolder->get : " + Thread.currentThread().getName() + ", type : " + type);
        return type;
    }

    public static void clearDataSourceType(){
        contextHolder.remove();
    }
}
