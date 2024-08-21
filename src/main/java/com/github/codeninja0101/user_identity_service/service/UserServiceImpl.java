package com.github.codeninja0101.user_identity_service.service;

import com.github.codeninja0101.user_identity_service.model.UserEntity;
import com.github.codeninja0101.user_identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean validateUser(String username, String password) {
        Optional<UserEntity> userEntity = findByUsername(username);
        return userEntity.isPresent() && passwordEncoder.matches(password, userEntity.get().getPassword());
    }

    @Override
    public void resetPassword(String username, String newPassword) {
        Optional<UserEntity> userEntity = findByUsername(username);
        if (userEntity.isPresent()) {
            UserEntity existingUser = userEntity.get();
            existingUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(existingUser);
        } else {
            System.out.println(username + ": User is Not Present");
        }
    }
}
