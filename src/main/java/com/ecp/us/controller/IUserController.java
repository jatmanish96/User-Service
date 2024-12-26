package com.ecp.us.controller;

import com.ecp.us.dto.LoginRequest;
import com.ecp.us.dto.UserCreateDto;
import com.ecp.us.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUserController {

    @PostMapping("/create")
    ResponseEntity<String> create(@RequestBody UserCreateDto userCreateDto) throws Exception;

    @GetMapping("/get")
    ResponseEntity<UserDto> get(@RequestParam(name = "id")Long id);

    @PostMapping("/auth/login")
    ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) throws Exception;

 }
