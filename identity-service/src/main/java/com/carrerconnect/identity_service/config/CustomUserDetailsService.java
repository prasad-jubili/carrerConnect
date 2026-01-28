package com.carrerconnect.identity_service.config;

import com.carrerconnect.identity_service.entity.User;
import com.carrerconnect.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("DEBUG: Attempting to load user: " + username);
        Optional<User> credential = repository.findByUsername(username);
        if (credential.isPresent()) {
            System.out.println("DEBUG: User found: " + credential.get().getUsername() + ", Password Hash: "
                    + credential.get().getPassword());
        } else {
            System.out.println("DEBUG: User NOT found!");
        }
        return credential.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
    }
}
