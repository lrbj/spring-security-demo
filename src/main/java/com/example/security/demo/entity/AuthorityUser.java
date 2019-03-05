package com.example.security.demo.entity;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Kayla, Ye
 * @Description:再次封装用于security验证的用户
 * @Date:Created in 4:09 PM 3/5/2019
 */
@Data
public class AuthorityUser extends User implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<AuthorityRole> authorityRoles = new ArrayList<>();
        List<Role> roles = super.getRoles();
        if (roles != null) {
            roles.forEach(role -> {
                authorityRoles.add(new AuthorityRole(role.getName().toString()));
            });
        }
        return authorityRoles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void  setAuthorities(){
        this.authorities = this.getAuthorities();
    }
}
