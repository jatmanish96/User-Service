package com.ecp.us.dto;

import com.ecp.us.utils.BooleanDeserializerUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String user_id;
    private String username;
    private String email;
    private String first_name;
    private String last_name;
    private String phone;
    private String address;
    private Boolean active;
    private Date createdAt;
    private Date updateAt;


}
