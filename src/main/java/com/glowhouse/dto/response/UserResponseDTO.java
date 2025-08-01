package com.glowhouse.dto.response;

import com.glowhouse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class UserResponseDTO {
    private User user;
    private String errorMessage;
}
