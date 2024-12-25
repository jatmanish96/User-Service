package com.ecp.us.serviceimpl;


import com.ecp.us.config.JwtTokenProvider;
import com.ecp.us.constants.UserConstants;
import com.ecp.us.dto.LoginRequest;
import com.ecp.us.dto.UserCreateDto;
import com.ecp.us.dto.UserDto;
import com.ecp.us.models.Role;
import com.ecp.us.models.User;
import com.ecp.us.repository.RoleRepository;
import com.ecp.us.repository.UserRepository;
import com.ecp.us.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;


@Service
public class UserServiceImpl implements IUserService {

    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<UserDto> get(Long id) {
        logger.info("User Service class get method id : {}",id);
        try {
            Optional<User> user= userRepository.findById(id);
            if(user.isEmpty()){
                throw new NoSuchElementException("No User Found for this Id :"+id);
            }
            UserDto userDto = objectMapper.convertValue(user.get(),UserDto.class);
            return  ResponseEntity.ok().body(userDto);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (Exception ex){
            logger.error("User Service class get method error : {}",ex.getStackTrace());
            throw new RuntimeException(ex.getMessage());
        }
        
    }

    @Override
    public ResponseEntity<String> create(UserCreateDto userCreateDto) throws Exception {
        logger.info("User Service class create method ");
        try {
            userCreateDto.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
            User user = objectMapper.convertValue(userCreateDto, User.class);
            user.setActive(true);
            Role role =  roleRepository.findByName("ROLE_USER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            User savedUser = userRepository.save(user);
            URI location = URI.create("/api/users/" + savedUser.getUser_id());
            return ResponseEntity.created(location).body("User created successfully.");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (Exception ex){
            logger.error("User Service class create method error : {}",ex.getMessage());
            throw new Exception(ex);
        }
    }

    @Override
    public ResponseEntity<?> userLogin(LoginRequest loginRequest) {
        logger.info("User service class user login method");
        Authentication authentication;
        try {
            authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());
            Authentication auth=authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(auth);
            String jwt_token= JwtTokenProvider.generateToken(auth);
            return ResponseEntity.ok().body(UserConstants.JWT_TOKEN_PREFIX + jwt_token);
        }
        catch (HttpClientErrorException.Unauthorized ex){
            return ResponseEntity.status(401).body("Invalid credentials or unauthorized");
        }
        catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal server error: " + ex.getMessage());
        }
    }

}
