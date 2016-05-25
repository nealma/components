package com.nealma.framework.exception;

import org.apache.shiro.ShiroException;

/**
 * Created by nealpc on 5/25/16.
 */
public class SensitivewordsExecption extends ShiroException{
    public SensitivewordsExecption() {
    }

    public SensitivewordsExecption(String message) {
        super(message);
    }
}
