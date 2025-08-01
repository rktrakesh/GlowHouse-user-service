package com.glowhouse.dto.request;

import lombok.*;

@Data
@AllArgsConstructor@NoArgsConstructor
public class UserDTO {
    private String fullName;
    private String email;
    private String mobileNo;
    private String role;
    private String password;
    private String username;
}
