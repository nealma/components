package com.nealma.framework.exception;

import org.apache.shiro.ShiroException;

/**
 * Created by nealpc on 5/25/16.
 */
public class SensitiveWordsExecption extends ShiroException{
    public SensitiveWordsExecption() {
    }

    public SensitiveWordsExecption(String message) {
        super(message);
    }
}
