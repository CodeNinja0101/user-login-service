package com.github.codeninja0101.user_identity_service.repository;

import com.github.codeninja0101.user_identity_service.dto.UserDTO;
import com.github.codeninja0101.user_identity_service.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {
    Optional<UserDTO> findByUsername(String username);
}

