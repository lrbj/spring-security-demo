package com.example.security.demo.config;

import com.example.security.demo.entity.AuthorityUser;
import com.example.security.demo.entity.User;
import com.example.security.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 2:59 PM 3/5/2019
 */
@Component
public class myUserDetailService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //查询帐号是否存在
        User user = userRepository.findByUsername(s);
        if(null == user){
            throw new UsernameNotFoundException("userName" + s + "not found");
        }

        AuthorityUser authorityUser = new AuthorityUser();
        // 置空Role中的User，避免死循环问题
        if (authorityUser.getRoles() != null) {
            for (int i = 0; i < authorityUser.getRoles().size(); i++) {
                authorityUser.getRoles().get(i).setUsers(null);
            }
        }
        BeanUtils.copyProperties(user,authorityUser);
        return  authorityUser;
    }
}
