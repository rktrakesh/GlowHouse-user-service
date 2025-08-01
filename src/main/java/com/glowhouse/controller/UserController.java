package com.glowhouse.controller;

import com.glowhouse.dto.UserUpdateDTO;
import com.glowhouse.dto.request.UserDTO;
import com.glowhouse.dto.response.UserResponseDTO;
import com.glowhouse.entity.User;
import com.glowhouse.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/addNewUser")
    public User addNewUser (@RequestBody @Valid UserDTO userDto) {
        return userService.addNewUser(userDto);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUserDetails () {
        return userService.getAllUserDetails();
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity <UserResponseDTO> getUserDetailsById (@PathVariable Long id) throws Exception {
        Optional <User> userDetail = userService.getUserDetailsById(id);
        if (userDetail.isEmpty()) {
            return ResponseEntity.status(404).body(new UserResponseDTO(null, "User not found with the id: "+id));
        }
        return ResponseEntity.status(200).body(new UserResponseDTO(userDetail.get(), null));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUserById(@Valid @RequestBody UserUpdateDTO userDTO, @PathVariable Long id) {
        try {
            Optional<User> userDetails = userService.getUserDetailsById(id);
            if (userDetails.isEmpty()) {
                logger.info("Unable to find the user details for userId: {} " , id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User details not found for id: " + id);
            }
            userService.updateUserById(userDTO, id);
            return ResponseEntity.ok("User details updated successfully for id: " + id);

        } catch (Exception e) {
            logger.error("Failed to update user with ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update failed for id: " + id);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUserById (@PathVariable Long id) {
        Optional<User> userDetails = userService.getUserDetailsById(id);
        if (userDetails == null || userDetails.isEmpty()) {
            return ResponseEntity.status(404).body("User not found with the id: "+id);
        }
        userService.deleteUserById(id);
        return ResponseEntity.ok("User details deleted successfully.");
    }

}
