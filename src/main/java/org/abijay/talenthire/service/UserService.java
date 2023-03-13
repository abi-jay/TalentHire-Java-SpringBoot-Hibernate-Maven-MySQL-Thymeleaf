/**
 *
 * * Filename: UserService.java
 * * 03/13/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.abijay.talenthire.service;

import org.abijay.talenthire.dto.RegistrationDto;
import org.abijay.talenthire.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    User findByEmail(String email);
}
