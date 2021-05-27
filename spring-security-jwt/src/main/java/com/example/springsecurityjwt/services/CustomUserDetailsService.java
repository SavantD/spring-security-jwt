package com.example.springsecurityjwt.services;

import com.example.springsecurityjwt.models.CustomUserDetails;
import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return user.map(CustomUserDetails::new).get();
        } else {
            throw new UsernameNotFoundException("User Not Found For Username: " + username);
        }

    }
}
