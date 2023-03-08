package org.abijay.talenthire.service.impl;

import org.abijay.talenthire.dto.RegistrationDto;
import org.abijay.talenthire.entity.Role;
import org.abijay.talenthire.entity.User;
import org.abijay.talenthire.repository.RoleRepository;
import org.abijay.talenthire.repository.UserRepository;
import org.abijay.talenthire.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    // dependency injection
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    // constructor based DI


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        // using spring security to encrypt this password
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        // default role object with id 2 - name ROLE_GUEST
        Role role = roleRepository.findByName("ROLE_GUEST");
        // Add guest role to user object
        user.setRoles(Arrays.asList(role));
        // save user object to mysql
        userRepository.save(user);
    }

    @Override
    public User finByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
