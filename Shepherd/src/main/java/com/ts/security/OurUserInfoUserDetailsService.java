package com.ts.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ts.dao.OurUsersRepository;
import com.ts.model.OurUsers;

@Service
public class OurUserInfoUserDetailsService implements UserDetailsService {

    private final OurUsersRepository ourUsersRepository;
    private final PasswordEncoder passwordEncoder;

    public OurUserInfoUserDetailsService(OurUsersRepository ourUsersRepository, PasswordEncoder passwordEncoder) {
        this.ourUsersRepository = ourUsersRepository;
        this.passwordEncoder = passwordEncoder;

        // Check if the table is empty
        if (ourUsersRepository.count() == 0) {
            // Create and save the default SUPERADMIN user with hashed password
            OurUsers defaultSuperAdmin = new OurUsers();
            defaultSuperAdmin.setUserName("Kirankumar Pal");
            defaultSuperAdmin.setEmail("kp@gmail.com");
            defaultSuperAdmin.setPassword(passwordEncoder.encode("kiran")); // Hash the password
            defaultSuperAdmin.setRoles("SUPERADMIN");
            ourUsersRepository.save(defaultSuperAdmin);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<OurUsers> user = ourUsersRepository.findByEmail(username);
        OurUsers ourUser = user.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new OurUserInfoDetails(ourUser);
    }
}
