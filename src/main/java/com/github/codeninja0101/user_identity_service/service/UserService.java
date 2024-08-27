package com.github.codeninja0101.user_identity_service.service;

import com.github.codeninja0101.user_identity_service.dto.UserDTO;
import com.github.codeninja0101.user_identity_service.model.UserModel;

import java.util.Optional;

public interface UserService {

    UserDTO registerUser(UserModel userEntity);

    Optional<UserModel> findByUsername(String username);

    boolean validateUser(String username, String password);

    void resetPassword(String username, String newPassword);

}
