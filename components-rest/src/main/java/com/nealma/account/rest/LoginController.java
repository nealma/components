package com.nealma.account.rest;

import static org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                        String message, HttpServletRequest request,
                        HttpServletResponse response, ModelAndView model) {
        Object error = request.getAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        LOGGER.info("username={}, password={}, captcha={}", username, password, captcha);
        if (error != null) {
            model.addObject("message", "用户名/密码不匹配！");
        }
        model.setViewName("view/login");
        return model;
    }

    @RequestMapping("/main.do")
    public ModelAndView main() {
        LOGGER.info("action -> {}", "main");
        return new ModelAndView("view/main");
    }
}
