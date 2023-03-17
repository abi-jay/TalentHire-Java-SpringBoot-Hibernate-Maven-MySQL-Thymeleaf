/**
 *
 * * Filename: FulfillRepository.java
 * * 03/13/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.abijay.talenthire.repository;

import org.abijay.talenthire.entity.Fulfill;
import org.abijay.talenthire.entity.Request;
import org.abijay.talenthire.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FulfillRepository extends JpaRepository<Fulfill, Long> {
    // JPQL query to search Clients. JPQL uses entity and its fields, NOT table columns
    @Query("SELECT f FROM Fulfill f WHERE " +
            " f.name LIKE CONCAT('%', :query, '%')")
    List<Fulfill> searchClientsByName(String query);

    @Query(value = "SELECT f.* FROM talenthire.fulfills f INNER JOIN talenthire.talents t\n" +
            "WHERE f.talent_id = t.id and t.created_by =:userId", nativeQuery = true)
    List<Fulfill> findFulfiillsByTalent(Long userId);
}