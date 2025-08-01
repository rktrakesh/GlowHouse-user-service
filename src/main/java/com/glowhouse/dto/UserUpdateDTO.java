package com.glowhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class UserUpdateDTO {

    private String fullName;
    private String username;
    private String email;
    private String password;
    private String mobileNo;
    private String role;

}
