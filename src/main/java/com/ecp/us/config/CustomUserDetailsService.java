package com.ecp.us.config;


import com.ecp.us.models.User;
import com.ecp.us.repository.RoleRepository;
import com.ecp.us.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    public static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user =  userRepository.findByUsername(username);
            if(user==null) {
                throw new UsernameNotFoundException("Username not Found!.");
            }
            List<GrantedAuthority> authorities =user.getRoles().stream().map(authority->new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
        } catch (Exception e) {
            logger.error("CustomUserDetailsService class loadUserByName method error: {}", (Object) e.getStackTrace());
            throw new RuntimeException(e.getMessage());
        }

    }
}
