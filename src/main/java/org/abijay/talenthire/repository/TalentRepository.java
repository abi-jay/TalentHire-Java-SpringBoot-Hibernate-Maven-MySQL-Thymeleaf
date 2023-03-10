package org.abijay.talenthire.repository;

import org.abijay.talenthire.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface TalentRepository extends JpaRepository<Talent, Long> {
    Optional<Talent> findByUrl(String url);
    Optional<Talent> findByTalent(String talent);

    @Query("SELECT p FROM Talent p WHERE " +
            "p.talent LIKE CONCAT('%', :query, '%') OR " +
            "p.shortDescription LIKE CONCAT('%', :query, '%') OR " +
            "p.description LIKE CONCAT('%', :query, '%')")
    List<Talent> searchTalents(String query);

    // passing native SQL query to @Query annotation. uses table columns
    @Query(value = "SELECT * FROM talents p WHERE p.created_by =:userId", nativeQuery = true)
    List<Talent> findTalentsByUser(Long userId);
}
