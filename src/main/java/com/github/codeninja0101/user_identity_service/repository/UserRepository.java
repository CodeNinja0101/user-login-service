package com.github.codeninja0101.user_identity_service.repository;

import com.github.codeninja0101.user_identity_service.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}

