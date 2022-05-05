package com.recycle.config.filter;

import com.recycle.config.handler.CustomAuthenticationFailureHandler;
import com.recycle.config.exception.VerificationCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zhouzhu
 * @date 2022/5/5
 */
public class VerificationCodeFilter extends OncePerRequestFilter{

    private final AuthenticationFailureHandler authenticationFailureHandler=new CustomAuthenticationFailureHandler();
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //非登录请求不校验验证码
        if (!"/doLogin".equals(httpServletRequest.getRequestURI())){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }else {
            try {
                verificationCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest,httpServletResponse);
            } catch (VerificationCodeException e) {
                System.out.println(e);
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
    public void verificationCode(HttpServletRequest httpServletRequest){
        String requestCode = httpServletRequest.getParameter("captcha");
        HttpSession session = httpServletRequest.getSession();
        String saveCode = session.getAttribute("captcha").toString();
        //校验一次后清除验证码，不管是否成功
        if (StringUtils.isNotEmpty(saveCode)){
            session.removeAttribute("captcha");
        }
        //校验不通过抛出异常
        if (StringUtils.isEmpty(requestCode) || StringUtils.isEmpty(saveCode) || !requestCode.equals(saveCode)){
           throw new VerificationCodeException("验证码错误");
        }
    }
}
