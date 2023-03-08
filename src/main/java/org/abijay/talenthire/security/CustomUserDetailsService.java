package org.abijay.talenthire.security;

import org.abijay.talenthire.entity.User;
import org.abijay.talenthire.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

// Spring Security provides user service details interface and Spring boot will create instance of this inorder to create object
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // load user record from database by username/email address
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user != null){
            // create instance of spring security provided user object
            org.springframework.security.core.userdetails.User authenticatedUser =
                    new org.springframework.security.core.userdetails.User(
                            user.getEmail(),
                            user.getPassword(),
                            user.getRoles().stream()
                                    .map((role) -> new SimpleGrantedAuthority(role.getName()))
                                    .collect(Collectors.toList())
                    );
            return authenticatedUser;
        }
        else{
            throw new UsernameNotFoundException("Invalid username/password");
        }
    }
}
