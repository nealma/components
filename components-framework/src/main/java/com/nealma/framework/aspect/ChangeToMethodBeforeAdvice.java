package com.nealma.framework.aspect;

import com.nealma.framework.annocation.DataSource;
import com.nealma.framework.datasource.DataSourceContextHolder;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by nealpc on 11/10/15.
 */
public class ChangeToMethodBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        DataSource annotation = o.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes()).getAnnotation(DataSource.class);
        if (null!=annotation) {
            DataSourceContextHolder.setDataSourceType(annotation.name());
        }else{
            annotation=o.getClass().getAnnotation(DataSource.class);
            if(null!=annotation){
                DataSourceContextHolder.setDataSourceType(annotation.name());
            }
        }
    }
}
