package com.recycle.config;

import com.recycle.config.filter.VerificationCodeFilter;
import com.recycle.config.handler.CustomAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zhouzhu
 * @date 2022/5/1
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/code/image","/toLogin","/doLogin","/login/error")
                .permitAll()
                .anyRequest()
                .authenticated()
                // and 方法表示结束当前标签，上下文回到HttpSecurity，开启新一轮的配置
                .and()
                .formLogin()
                //自定义登录页面
                .loginPage("/toLogin")
                //自定义登录接口
                .loginProcessingUrl("/doLogin")
                //登录相关的界面，接口不要拦截
                .permitAll()
                .failureHandler(new CustomAuthenticationFailureHandler())
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .csrf().disable();

        http.addFilterBefore(new VerificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    /**
     * 配置登录用户密码
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("admin");
    }

    /**
     * 密码加密，官方推荐BCryptPasswordEncoder加密
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
