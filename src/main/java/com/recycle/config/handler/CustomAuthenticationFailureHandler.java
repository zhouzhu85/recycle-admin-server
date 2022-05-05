package com.recycle.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recycle.config.exception.VerificationCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouzhu
 * @date 2022/5/4
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登陆失败");
        if (exception instanceof VerificationCodeException){
            request.getSession().setAttribute("errorMsg",exception.getMessage());
        }else {
            request.getSession().setAttribute("errorMsg","用户名或密码错误");
        }
        // 转发到错误Url
        request.getRequestDispatcher("/login/error").forward(request,response);

    }
}

