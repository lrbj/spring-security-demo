package com.example.security.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 4:52 PM 3/5/2019
 */
@Component("mySecurity")
public class MySecurity {
    //这里应该注入用户和该用户所拥有的权限（权限在登录成功的时候已经缓存起来，当需要访问该用户的权限是，直接从缓存取出！），然后验证该请求是否有权限，有就返回true，否则则返回false不允许访问该Url。
    //而且这里还传入了request,我也可以使用request获取该次请求的类型。
    //根据restful风格我们可以使用它来控制我们的权限，例如当这个请求是post请求，证明该请求是向服务器发送一个新建资源请求，我们可以使用request.getMethod()来获取该请求的方式，然后在配合角色所允许的权限路径进行判断和授权操作！
    public boolean check(Authentication authentication, HttpServletRequest request){
        //如果能获取到Principal对象不为空证明，授权已经通过
        Object principal  = authentication.getPrincipal();
        if(principal  != null && principal  instanceof UserDetails){
            //获取请求登录的url
            System.out.println(((UserDetails)principal).getAuthorities()) ;
            return true;
        }
        return false;
    }
}
