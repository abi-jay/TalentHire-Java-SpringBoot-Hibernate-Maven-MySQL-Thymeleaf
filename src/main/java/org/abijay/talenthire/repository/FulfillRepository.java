package org.abijay.talenthire.repository;

import org.abijay.talenthire.entity.Fulfill;
import org.abijay.talenthire.entity.Request;
import org.abijay.talenthire.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FulfillRepository extends JpaRepository<Fulfill, Long> {
    @Query("SELECT f FROM Fulfill f WHERE " +
            " f.name LIKE CONCAT('%', :query, '%')")
    List<Fulfill> searchClientsByName(String query);
}