package com.nealma.account.rest;

import com.nealma.account.service.ManagerService;
import com.nealma.framework.annocation.SystemWebLayerLog;
import com.nealma.framework.commons.StringUtil;
import org.apache.ibatis.datasource.DataSourceException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;

/**
 * Created by nealpc on 5/21/16.
 */
@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ManagerService managerService;

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public ModelAndView toLogin(HttpServletRequest request,
                                HttpServletResponse response) {
        return new ModelAndView("view/login");
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView login(String username, String password, String captcha,
                              HttpServletRequest request,
                              HttpServletResponse response, ModelAndView model) {
        model.setViewName("view/main");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        String error = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (ExcessiveAttemptsException e) {
            error = "登录失败多次，账户锁定10分钟";
        } catch (AuthenticationException e) {
            // 其他错误，比如锁定，如果想单独处理请单独catch处理
            e.printStackTrace();
            error = "其他错误：" + e.getMessage();
        }

        LOGGER.info("username={}, password={}, error={}", username, password, error);
        if (error != null) {
            model.addObject("message", error);
            model.setViewName("view/login");
        }

        return model;
    }

    @RequestMapping(value = "/register.do", method = RequestMethod.GET)
    public ModelAndView toRegister(HttpServletRequest request,
                                   HttpServletResponse response) {
        return new ModelAndView("view/register");
    }

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public ModelAndView register(String username, String password, String captcha,
                                 HttpServletRequest request,
                                 HttpServletResponse response, ModelAndView model) {
        Object error = request.getAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

        LOGGER.info("username={}, password={}, error={}", username, password, error);
        model.addObject("message", "请重新登录你的账号！");
        model.setViewName("view/login");

        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(username)) {
            model.addObject("message", "用户名或密码为空！");
            model.setViewName("view/register");
            return model;
        }
        try {
            managerService.insertForRegister(username, password);
        } catch (IllegalArgumentException e) {
            model.addObject("message", "用户名或密码为空！");
            model.setViewName("view/register");
        } catch (DataSourceException e) {
            model.addObject("message", "用户名已经被使用！");
            model.setViewName("view/register");
        }
        return model;
    }

    @RequestMapping("/main.do")
    public ModelAndView main() {
        LOGGER.info("action -> {}", "main");
        return new ModelAndView("view/main");
    }

    @RequestMapping("/logout.do")
    public ModelAndView logout() {
        LOGGER.info("action -> {}", "logout");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
        }
        return new ModelAndView("view/login");
    }

    @RequestMapping("/userlist.do")
    @RequiresPermissions("user:view")
    @SystemWebLayerLog(description = "用户列表")
    public ModelAndView userlist() {
        LOGGER.info("action -> {}", "userlist");
        return new ModelAndView("view/user/userlist");
    }

    @RequestMapping("/rolelist.do")
    @RequiresPermissions("role:view")
    @SystemWebLayerLog(description = "用户列表")
    public ModelAndView rolelist() {
        LOGGER.info("action -> {}", "rolelist");
        return new ModelAndView("view/role/rolelist");
    }

    @RequestMapping("/permissionlist.do")
    @RequiresPermissions("permission:view")
    @SystemWebLayerLog(description = "用户列表")
    public ModelAndView permissionlist() {
        LOGGER.info("action -> {}", "permissionlist");
        return new ModelAndView("view/permission/permissionlist");
    }

    @RequestMapping("/loglist.do")
    @RequiresPermissions("log:view")
    @SystemWebLayerLog(description = "用户列表")
    public ModelAndView loglist() {
        LOGGER.info("action -> {}", "loglist");
        return new ModelAndView("view/log/loglist");
    }
}
