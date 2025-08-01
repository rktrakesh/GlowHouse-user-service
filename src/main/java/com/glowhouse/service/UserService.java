package com.glowhouse.service;

import com.glowhouse.dto.UserUpdateDTO;
import com.glowhouse.dto.request.UserDTO;
import com.glowhouse.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User addNewUser (UserDTO userDto);
    public List<User> getAllUserDetails ();
    public Optional<User> getUserDetailsById (Long id);
    public void updateUserById(UserUpdateDTO userDTO, Long id);
    public void deleteUserById (Long id);

}
