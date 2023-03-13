/**
 *
 * * Filename: UserServiceImpl.java
 * * 03/13/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.abijay.talenthire.service.impl;

import org.abijay.talenthire.dto.RegistrationDto;
import org.abijay.talenthire.entity.Role;
import org.abijay.talenthire.entity.User;
import org.abijay.talenthire.repository.RoleRepository;
import org.abijay.talenthire.repository.UserRepository;
import org.abijay.talenthire.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.NoSuchElementException;

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
    public User findByEmail(String email) throws UsernameNotFoundException {
        try {
            return userRepository.findByEmail(email);
        } catch(NoSuchElementException e) {
            throw new UsernameNotFoundException("Couldn't find any user with Email: " + email);
        }
    }
}