package com.nealma.account.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by nealpc on 5/21/16.
 */
public class LogOutFilter extends LogoutFilter {


    public static final String RETURN_URL = "returnUrl";

    protected String getRedirectUrl(ServletRequest req, ServletResponse resp,Subject subject) {
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.logout();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "login.do";

    }
}
