package com.nealma.account.shiro.filter;

import com.nealma.account.service.ManagerService;
import com.nealma.framework.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by nealpc on 5/21/16.
 */
public class MyAuthenticationFilter extends FormAuthenticationFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationFilter.class);

    @Autowired
    private ManagerService managerService;

    /**
     * 处理登陆
     *
     * 如果有验证码的话在这里进行验证处理
     */
    @Override
    protected boolean executeLogin(ServletRequest request,ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        LOGGER.info(">>>>>>>>>>>>>>>> execute executeLogin <<<<<<<<<<<<<<<<<<<<<<<<<<<");
        if (token == null) {
            String msg = "create AuthenticationToken error";
            throw new IllegalStateException(msg);
        }
        HttpServletRequest req = (HttpServletRequest) request;
        String username = (String) token.getPrincipal();
        Subject subject = getSubject(request, response);
        try{
            subject.login(token);
        }catch (AuthenticationException e) {
            e.printStackTrace();
            return super.onLoginFailure(token, e, request, response);
        }
        return super.onLoginSuccess(token, subject, request, response);
    }
}
