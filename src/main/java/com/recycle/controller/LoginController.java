package com.recycle.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author zhouzhu
 * @date 2022/5/1
 */
@Controller
public class LoginController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @RequestMapping("toLogin")
    public ModelAndView login(ModelAndView modelAndView,String error){
        modelAndView.setViewName("/index/login");
        return modelAndView;
    }

    @RequestMapping("/code/image")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取验证码字符串
        String code = defaultKaptcha.createText();
        //设置验证码到session
        request.getSession().setAttribute("captcha", code);
        //获取验证码图片
        BufferedImage image = defaultKaptcha.createImage(code);
        //将验证码图片写出去
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
    }

    @RequestMapping("/login/error")
    public String loginError(HttpServletRequest request, HttpServletResponse response, Model model) {
        response.setContentType("text/html;charset=utf-8");
        Object exception= request.getSession().getAttribute("errorMsg");
        try {
            response.getWriter().write(exception.toString());
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(exception);
        model.addAttribute("errorMsg",exception);

        return "/index/login";
    }
}
