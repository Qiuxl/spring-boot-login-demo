package com.example.qzz.mvcdemo.controller;

import com.example.qzz.mvcdemo.config.SecurityInterceptor;
import com.example.qzz.mvcdemo.dao.meta.User;
import com.example.qzz.mvcdemo.service.ILoginService;
import com.example.qzz.mvcdemo.service.IUserService;
import com.example.qzz.mvcdemo.utils.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @Autowired
    private IUserService userService;

    @Autowired
    private ILoginService loginService;

    @GetMapping(value = {"/","/index"})
    public String index(@SessionAttribute(SecurityInterceptor.SESSION_KEY) String account, Model model){
        User user = userService.getByName(account);
        model.addAttribute("user",user);
        model.addAttribute("registerTime", DateTimeUtils.convertTimeIntoStr(user.getCreateTime()));
        model.addAttribute("updateTime",DateTimeUtils.convertTimeIntoStr(user.getUpdateTime()));
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ResponseBody
    @PostMapping("/loginVerify")
    public Map<String, Object> loginVerify( String username, String password, HttpSession session){

        logger.info("receive login request, user name: {}, password: {}, request: {}",username,password);
        Map<String, Object> result = new HashMap<>();
        boolean verify = loginService.verifyLogin(username,password);
        if (verify) {
            session.setAttribute(SecurityInterceptor.SESSION_KEY, username);
            result.put("success", true);
            result.put("message", "登录成功");
        } else {
            result.put("success", false);
            result.put("message", "密码错误");
        }
        return result;
    }


    @ResponseBody
    @PostMapping("/modifyPassword")
    public Object modifyPassword(HttpSession session, String password){

        String name = (String) session.getAttribute(SecurityInterceptor.SESSION_KEY);

        logger.info("request to modify password of user {}, change to be: {}", name,password);

        boolean updateSucc = userService.updatePassword(name,password);
        Map<String, Object> result = new HashMap<>();
        result.put("修改结果",updateSucc);
        // 设置重新登陆
        if(updateSucc){
            session.setAttribute(SecurityInterceptor.SESSION_KEY,null);
        }
        return result;
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(SecurityInterceptor.SESSION_KEY);
        return "redirect:/login";
    }

}
