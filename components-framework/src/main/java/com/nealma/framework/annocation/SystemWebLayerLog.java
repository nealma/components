package com.nealma.framework.annocation;

import java.lang.annotation.*;

/**
 * Created by nealpc on 5/25/16.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemWebLayerLog {
    String description() default "";
}
