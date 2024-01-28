package com.ts.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ts.model.OurUsers;

public class OurUserInfoDetails implements UserDetails {
    private String email;
    private String password;
    private List<GrantedAuthority> roles;

    public OurUserInfoDetails(OurUsers ourUsers){
        this.email = ourUsers.getEmail();
        this.password = ourUsers.getPassword();
        this.roles = Arrays.stream(ourUsers.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public boolean hasSuperAdminRole() {
        return this.roles.stream().anyMatch(role -> role.getAuthority().equals("SUPERADMIN"));
    }
}
