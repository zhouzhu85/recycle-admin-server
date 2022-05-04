package com.recycle.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhouzhu
 * @date 2022/5/4
 */
public class LoginFilterConfig extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())){
            throw new AuthenticationServiceException("Authentication method not supported: " +request.getMethod());
        }
        String captcha = request.getParameter("captcha");
        String sessionCaptcha= request.getSession().getAttribute("captcha").toString();
        if (StringUtils.isNotEmpty(captcha) && StringUtils.isNotEmpty(sessionCaptcha) && captcha.equalsIgnoreCase(sessionCaptcha)){
            return super.attemptAuthentication(request, response);
        }
        //手动设置异常
        request.getSession().setAttribute("errr","验证码输入错误");
        throw new AuthenticationServiceException("");
    }
}
