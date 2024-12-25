package com.ecp.us.service;

import com.ecp.us.dto.LoginRequest;
import com.ecp.us.dto.UserCreateDto;
import com.ecp.us.dto.UserDto;
import com.ecp.us.models.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    ResponseEntity<UserDto> get(Long id);
    ResponseEntity<String> create(UserCreateDto userCreateDto) throws Exception;
    ResponseEntity<?>userLogin(LoginRequest loginRequest);
}
