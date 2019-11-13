package com.sam.api.security;

import com.sam.api.model.User;
import com.sam.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOfEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(usernameOfEmail, usernameOfEmail)
        .orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email: " + usernameOfEmail)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id)
        .orElseThrow(() ->
                new UsernameNotFoundException("User not found with id:" + id)
        );

        return UserPrincipal.create(user);
    }
}
