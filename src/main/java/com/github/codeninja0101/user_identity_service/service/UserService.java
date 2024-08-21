package com.github.codeninja0101.user_identity_service.service;

import com.github.codeninja0101.user_identity_service.model.UserEntity;

import java.util.Optional;

public interface UserService {
    UserEntity saveUser(UserEntity userEntity);

    Optional<UserEntity> findByUsername(String username);

    boolean validateUser(String username, String password);

    void resetPassword(String username, String newPassword);

}
