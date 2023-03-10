package org.abijay.talenthire.repository;

import org.abijay.talenthire.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TalentRepository extends JpaRepository<Talent, Long> {
    Optional<Talent>findByUrl(String url);
    Optional<Talent> findByTalent(String talent);

    // JPQL query to search Clients. JPQL uses entity and its fields, NOT table columns
    @Query("SELECT p FROM Talent p WHERE " +
            " p.talent LIKE CONCAT('%', :query, '%')")
    List<Talent> searchClientsByName(String query);

    @Query("SELECT p FROM Talent p WHERE " +
            " p.location LIKE CONCAT('%', :query, '%')")
    List<Talent> searchClientsByLocation(String query);

    @Query("SELECT p FROM Talent p WHERE " +
            " p.talent LIKE CONCAT('%', :query, '%')")
    List<Talent> searchClientsByTalent(String query);

    @Query("SELECT p FROM Talent p WHERE " +
            "p.talent LIKE CONCAT('%', :query, '%') OR " +
            "p.shortDescription LIKE CONCAT('%', :query, '%') OR " +
            "p.description LIKE CONCAT('%', :query, '%')")
    List<Talent> searchTalents(String query);

    // passing native SQL query to @Query annotation. uses table columns
    @Query(value = "SELECT * FROM talents p WHERE p.created_by =:userId", nativeQuery = true)
    List<Talent> findTalentsByUser(Long userId);
}
