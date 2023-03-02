package com.perscholas.talenthire.repository;

import com.perscholas.talenthire.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TalentRepository extends JpaRepository<Talent, Long> {
    Optional<Talent> findByTalent(String talent);

    // JPQL query to search Clients. JPQL uses entity and its fields, not table columns
    @Query("SELECT p FROM Talent p WHERE " +
            " p.name LIKE CONCAT('%', :query, '%')")
    List<Talent> searchClients(String query);
}
