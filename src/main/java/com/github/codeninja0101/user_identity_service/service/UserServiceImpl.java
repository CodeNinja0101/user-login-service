package com.github.codeninja0101.user_identity_service.service;

import com.github.codeninja0101.user_identity_service.util.PasswordUtil;
import com.github.codeninja0101.user_identity_service.dto.UserDTO;
import com.github.codeninja0101.user_identity_service.model.UserModel;
import com.github.codeninja0101.user_identity_service.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDTO registerUser(UserModel userModel) {
        if (userRepository.findByUsername(userModel.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userModel, userDTO);
        userDTO.setPassword(PasswordUtil.encodePassword(userModel.getPassword()));
        return userRepository.save(userDTO);
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        Optional<UserDTO> userDTO = userRepository.findByUsername(username);
        if (userDTO.isPresent()) {
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(userDTO.get(), userModel);
            return Optional.of(userModel);
        }
        return Optional.empty();
    }

    @Override
    public boolean validateUser(String username, String password) {
        Optional<UserDTO> userDTO = userRepository.findByUsername(username);
        return userDTO.isPresent() && PasswordUtil.matchPassword(password, userDTO.get().getPassword());
    }

    @Override
    public void resetPassword(String username, String newPassword) {
        Optional<UserDTO> userDTO = userRepository.findByUsername(username);
        if (userDTO.isPresent()) {
            UserDTO existingUser = userDTO.get();
            existingUser.setPassword(PasswordUtil.encodePassword(newPassword));
            userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("Username not found");
        }
    }

}
