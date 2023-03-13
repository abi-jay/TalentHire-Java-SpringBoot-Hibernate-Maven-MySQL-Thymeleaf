/**
 *
 * * Filename: RoleRepository.java
 * * 03/13/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.abijay.talenthire.repository;

import org.abijay.talenthire.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
