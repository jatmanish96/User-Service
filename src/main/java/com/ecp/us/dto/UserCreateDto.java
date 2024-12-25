package com.ecp.us.dto;

import lombok.Data;

@Data
public class UserCreateDto {

    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String password;


}
