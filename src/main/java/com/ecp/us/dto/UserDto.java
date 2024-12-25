package com.ecp.us.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private String user_id;
    private String username;
    private String email;
    private String first_name;
    private String last_name;
    private String phone;
    private String address;
    private boolean active;
    private Date createdAt;
    private Date updateAt;


}
