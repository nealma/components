package com.nealma.account.shiro.security;

import org.apache.shiro.ShiroException;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by nealpc on 5/21/16.
 */
public class SensitiveWordsFilter extends PathMatchingFilter {
    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws ShiroException {
        HttpServletRequest req = (HttpServletRequest) request;
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        /*解决Model之间有循环包含关系*/
//        JsonConfig jsonConfig = new JsonConfig();
//        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//
//        Enumeration<String> enuma=req.getParameterNames();
//        for(Enumeration<String> e=enuma;enuma.hasMoreElements();){
//            String thisName=e.nextElement().toString();
//            String thisValue=request.getParameter(thisName);
//
//            SensitiveWords sensitiveWords=new SensitiveWords();
//            if(sensitiveWords.haveWords(thisValue)){
//                System.out.println(thisValue);
//                throw new SensitivewordsExecption("你输入的内容中存在敏感词");
//            }
//        }
        return true;
    }
}
