package com.nealma.framework.annocation;

import java.lang.annotation.*;

/**
 * Created by nealpc on 11/10/15.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default DataSource.READ;
    public static String WRITE = "write";
    public static String READ = "read";
}
