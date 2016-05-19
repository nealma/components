package com.nealma.framework.commons;

/**
 * Created by nealpc on 5/18/16.
 */
public class StringUtil {
    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }
}
