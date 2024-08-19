package com.github.codeninja0101.user_identity_service.UserServiceImplTest;

import com.github.codeninja0101.user_identity_service.model.UserEntity;
import com.github.codeninja0101.user_identity_service.repository.UserRepository;
import com.github.codeninja0101.user_identity_service.service.userServiceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testSaveUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testuser");
        userEntity.setPassword("password");

        when(passwordEncoder.encode(userEntity.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        UserEntity savedUser = userService.saveUser(userEntity);

        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());
    }

    @Test
    public void testValidateUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testuser");
        userEntity.setPassword("encodedPassword");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        boolean isValid = userService.validateUser("testuser", "password");

        assertTrue(isValid);
    }
}
