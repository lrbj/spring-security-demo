package com.example.security.demo.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 1:20 PM 3/5/2019
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //定义用户认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    super.configure(auth);
        //并根据传入的AuthenticationManagerBuilder中的userDetailsService方法来接收我们自定义的认证方法。
        //且该方法必须要实现UserDetailsService这个接口。
        auth.userDetailsService(new myUserDetailService())
                .passwordEncoder(new BCryptPasswordEncoder()); //密码使用BCryptPasswordEncoder()方法验证，因为这里使用了BCryptPasswordEncoder()方法验证。
        // 所以在注册用户的时候在接收前台明文密码之后也需要使用BCryptPasswordEncoder().encode(明文密码)方法加密密码。
    }



    //定义授权策略
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()  //通过authorizeRequests()方法来开始请求权限配置
//                .antMatchers("/resources/**", "/demo/**","/about").permitAll() //任何用户都可以访问这些开头的网站
//                .antMatchers("/admin/**").hasRole("ADMIN") //运行拥有"ROLE_ADMIN"的角色
//                .antMatchers("/db/**").access("hasRole('ADMIN')and hasRole('DBA')") //同时拥有两项权限才可以访问
//                .antMatchers("/db/**").hasAnyRole("ADMIN", "DBA") //拥有任意权限便可访问
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();
//        http
//                //通过formlogin方法登录，并设置登录url为/api/user/login
//                .formLogin().loginPage("/api/user/login")
//                //指定登录成功后跳转到/index页面
//                .defaultSuccessUrl("/index")
//                //指定登录失败后跳转到/login?error页面
//                .failureUrl("/login?error")
//                .and()
//                //开启cookie储存用户信息，并设置有效期为14天，指定cookie中的密钥
//                .rememberMe().tokenValiditySeconds(1209600).key("mykey")
//                .and()
//                .logout()
//                //指定登出的url
//                .logoutUrl("/api/user/logout")
//                //指定登场成功之后跳转的url
//                .logoutSuccessUrl("/index");


        http.formLogin()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/demo/**").permitAll()
                .anyRequest().access("@mySecurity.check(authentication,request)");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}
