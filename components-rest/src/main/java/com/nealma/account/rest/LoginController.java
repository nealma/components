package com.nealma.account.rest;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.ThreadLocalRandom;

import static org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;

/**
 * Created by nealpc on 5/21/16.
 */
@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public ModelAndView tologin(HttpServletRequest request,
                                HttpServletResponse response) {
        return new ModelAndView("view/login");
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView login(String username, String password, String captcha,
                              HttpServletRequest request,
                              HttpServletResponse response, ModelAndView model) {
        Object error = request.getAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        LOGGER.info("username={}, password={}, captcha={}, error={}", username, password, captcha, error);
        if (error != null) {
            model.addObject("message", "用户名/密码不匹配！");
        }
        model.setViewName("view/login");
        return model;
    }

    @RequestMapping(value = "/register.do", method = RequestMethod.GET)
    public ModelAndView toregister(HttpServletRequest request,
                                   HttpServletResponse response) {
        return new ModelAndView("view/register");
    }

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public ModelAndView register(String username, String password, String captcha,
                                 HttpServletRequest request,
                                 HttpServletResponse response, ModelAndView model) {
        Object error = request.getAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

        PasswordService passwordService = new DefaultPasswordService();
        String encrypted = passwordService.encryptPassword(password);
        String encrypted1 = new Sha512Hash(password, username, ThreadLocalRandom.current().nextInt()).toHex();
        LOGGER.info("username={}, password={}, encrypted={}, error={}", username, password, encrypted1, error);
        if (error != null) {
            model.addObject("message", "用户名/密码不匹配！");
        }
        model.setViewName("view/register");
        return model;
    }

    @RequestMapping("/main.do")
    public ModelAndView main() {
        LOGGER.info("action -> {}", "main");
        return new ModelAndView("view/main");
    }

    @RequestMapping("/userlist.do")
    @RequiresPermissions("user:view")
    public ModelAndView userlist() {
        LOGGER.info("action -> {}", "userlist");
        return new ModelAndView("view/user/userlist");
    }

    @RequestMapping("/rolelist.do")
    @RequiresPermissions("role:view")
    public ModelAndView rolelist() {
        LOGGER.info("action -> {}", "rolelist");
        return new ModelAndView("view/role/rolelist");
    }

    @RequestMapping("/permissionlist.do")
    @RequiresPermissions("permission:view")
    public ModelAndView permissionlist() {
        LOGGER.info("action -> {}", "permissionlist");
        return new ModelAndView("view/permission/permissionlist");
    }

    @RequestMapping("/loglist.do")
    @RequiresPermissions("log:view")
    public ModelAndView loglist() {
        LOGGER.info("action -> {}", "loglist");
        return new ModelAndView("view/log/loglist");
    }
}
