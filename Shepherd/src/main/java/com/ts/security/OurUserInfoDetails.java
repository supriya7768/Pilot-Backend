package com.ts.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ts.model.OurUsers;

import lombok.Getter;

@Getter
public class OurUserInfoDetails implements UserDetails {

    private final OurUsers user;

    public OurUserInfoDetails(OurUsers user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Assuming roles are stored as a comma-separated string in the OurUsers class
        // Split the roles string and create GrantedAuthority objects
        return Collections.singleton(() -> user.getRoles());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Assuming accounts do not expire
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Assuming accounts are not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Assuming credentials do not expire
    }

    @Override
    public boolean isEnabled() {
        return true; // Assuming accounts are always enabled
    }
    
    // Custom method to check if the user has a specific role
    public boolean hasRole(String role) {
        return getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
    }
}
