package org.abijay.talenthire.repository;

import org.abijay.talenthire.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(value = "SELECT r.* FROM talenthire.requests r INNER JOIN talenthire.talents t\n" +
            "WHERE r.talent_id = t.id and t.created_by =:userId", nativeQuery = true)
    List<Request> findRequestsByTalent(Long userId);
}
