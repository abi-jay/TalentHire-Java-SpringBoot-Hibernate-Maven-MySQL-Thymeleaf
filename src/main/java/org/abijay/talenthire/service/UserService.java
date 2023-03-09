package org.abijay.talenthire.service;

import org.abijay.talenthire.dto.RegistrationDto;
import org.abijay.talenthire.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
