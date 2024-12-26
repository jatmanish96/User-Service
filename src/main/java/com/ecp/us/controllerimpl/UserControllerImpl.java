package com.ecp.us.controllerimpl;

import com.ecp.us.controller.IUserController;
import com.ecp.us.dto.LoginRequest;
import com.ecp.us.dto.UserCreateDto;
import com.ecp.us.dto.UserDto;
import com.ecp.us.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserControllerImpl implements IUserController {

    public static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);

    @Autowired
    private IUserService iUserService;

    @Override
    public ResponseEntity<String> create(UserCreateDto userCreateDto) throws Exception {
        logger.info("User Controller Class create method");
        return iUserService.create(userCreateDto);
    }

    @Override
    public ResponseEntity<UserDto> get(Long id) {
        logger.info("User Controller Class get method id : {}",id);
        return iUserService.get(id);
    }

    @Override
    public ResponseEntity<?> userLogin(LoginRequest loginRequest) throws Exception {
        return iUserService.userLogin(loginRequest);
    }

}
