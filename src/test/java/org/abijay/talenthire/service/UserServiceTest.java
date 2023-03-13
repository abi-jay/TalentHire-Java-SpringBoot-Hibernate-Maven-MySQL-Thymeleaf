package org.abijay.talenthire.service;

import org.abijay.talenthire.entity.User;
import org.abijay.talenthire.repository.RoleRepository;
import org.abijay.talenthire.repository.UserRepository;
import org.abijay.talenthire.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private User mockUser;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(mockUserRepository, mockRoleRepository, mockPasswordEncoder);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@test.com", "test", "@33421&.com"})
    public void happyPathFindByEmail(String email) {
        // Given
        when(mockUserRepository.findByEmail(email)).thenReturn(mockUser);

        // When
        User actual = userService.findByEmail(email);

        // Then
        Assertions.assertNotNull(actual);
    }
}
