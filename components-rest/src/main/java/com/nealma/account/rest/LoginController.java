package com.nealma.account.rest;

import static org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nealpc on 5/21/16.
 */
@RestController
public class LoginController {

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String tologin(HttpServletRequest request,
                          HttpServletResponse response, ModelMap model) {
        return "view/login";
    }
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(String username, String password, String captcha,
                        String message, HttpServletRequest request,
                        HttpServletResponse response, ModelMap model) {
        Object error = request.getAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if (error != null) {
            model.put("message", "用户名/密码不匹配！");
        }
        return "view/login";
    }

    @RequestMapping("/main.do")
    public String main() {
        System.out.println("1111");
        return "view/main";
    }
}
