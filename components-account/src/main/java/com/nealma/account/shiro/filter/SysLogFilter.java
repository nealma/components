package com.nealma.account.shiro.filter;

import com.nealma.framework.model.Log;
import com.nealma.framework.model.User;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by nealpc on 5/21/16.
 */
public class SysLogFilter extends PathMatchingFilter {

    @Autowired
//    private LogService logService;


    @Override
    public  boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception  {
        HttpServletRequest req=(HttpServletRequest) request;

        //当前登录的用户
        User user= (User) req.getSession().getAttribute("userinfo");
        String method = req.getRequestURI();
        //IP
        String ip = request.getRemoteAddr();
        //正常请求为0 异常为1
        String type="0";
        //操作时间
        Date date=new Date();
        Log log=new Log();
        log.setUsername(user.getUsername());
        log.setIp(ip);
        log.setType(type);
        log.setMethod(method);
        log.setCreateDate(date);
//        logService.addLog(log);
        System.out.println(log.toString());

        return true;
    }
}
