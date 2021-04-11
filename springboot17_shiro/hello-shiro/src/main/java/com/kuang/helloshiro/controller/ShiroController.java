package com.kuang.helloshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShiroController {

    @RequestMapping({"/","/index"})
    public String index(Model model){
        model.addAttribute("msg","helloShiro");
        return "index";
    }

    @RequestMapping("/user/update")
    public String update(){

        return "update";
    }

    @RequestMapping("/user/add")
    public String add(){

        return "add";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @PostMapping("/login")
    public String login(String username,String password,boolean rememberMe,Model model){
        System.out.println("登录了");
        //获取当前用户
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
     try{

         subject.login(token);//在这一步，去调用认证方法


         Session session = subject.getSession();
         session.setAttribute("loginUser",token.getUsername());
         return "redirect:/index";
     }catch (UnknownAccountException e){
         model.addAttribute("msg","用户名不存在");
         return "login";
     }catch (IncorrectCredentialsException e){
         model.addAttribute("msg","密码错误");
         return "login";
     }



    }


    @RequestMapping("/noauth")
    public String noauth(){
        return "noauth";
    }

    @PostMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/index";

}}
