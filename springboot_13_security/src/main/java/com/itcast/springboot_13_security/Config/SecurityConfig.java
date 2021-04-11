package com.itcast.springboot_13_security.Config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//重写configure方法，  默认是生成一个user用户  密码项目启动时随机生成
    //没有登录的一定要登录
    //认证功能
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     //密码需要设置编码器
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(encoder).withUser("zhangsan").password(encoder.encode("123")).
             roles("VIP1").and().withUser("张三").password(encoder.encode("123")).roles("VIP1","VIP2").
             and().withUser("lisi").password(encoder.encode("123")).roles("VIP1","VIP2","VIP3");
    }

    //授权功能
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制授权规则
       http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/level1/**").hasRole("VIP1")
                       .antMatchers("/level2/**").hasRole("VIP2").antMatchers("/level3/**")
               .hasRole("VIP3");

       //开启自动配置的登陆功能，效果  如果没有登录，没有权限，就会来到登录页面http.formLogin();
        //这个是自定义登录页面
    http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");
        //注销
        // 注销成功后会返回/login?logout
      //  http.formLogin();
        http.logout().logoutSuccessUrl("/");//注销成功后来到首页


        //开启记住我功能
        http.rememberMe();
      //  http.rememberMe().rememberMeParameter("remember");

    }
}
