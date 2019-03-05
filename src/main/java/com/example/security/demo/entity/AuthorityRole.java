package com.example.security.demo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: Kayla, Ye
 * @Description: 再次封装security验证的角色
 * @Date:Created in 4:13 PM 3/5/2019
 */
@Data
public class AuthorityRole implements GrantedAuthority {

    private String roleName;

    public AuthorityRole(){

    }

    public AuthorityRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
