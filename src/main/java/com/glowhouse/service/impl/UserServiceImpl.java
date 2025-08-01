package com.glowhouse.service.impl;

import com.glowhouse.dto.UserUpdateDTO;
import com.glowhouse.dto.request.UserDTO;
import com.glowhouse.entity.User;
import com.glowhouse.repository.UserRepository;
import com.glowhouse.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addNewUser(UserDTO userDto) {
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setMobileNo(userDto.getMobileNo());
        user.setRole(userDto.getRole());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUserDetails() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserDetailsById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateUserById(UserUpdateDTO userDTO, Long id) {
        try {
            updateUser(id, userDTO);
        } catch (Exception e) {
            logger.error("Error occurred while updateUserById: {} ", e.getMessage());
        }
    }

    @Transactional
    public void updateUser(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        if (dto.getFullName() != null) {
            user.setFullName(dto.getFullName());
        }
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
        if (dto.getMobileNo() != null) {
            user.setMobileNo(dto.getMobileNo());
        }
        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }
        userRepository.save(user);
        logger.info("User with ID {} updated successfully", id);
    }

    @Override
    public void deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            logger.info("User details deleted successfully for id: {}", id);
        } catch (Exception e) {
            logger.error("Exception occurred while deleting data for id: {} , {}" , id, e.getMessage());
        }
    }
}
