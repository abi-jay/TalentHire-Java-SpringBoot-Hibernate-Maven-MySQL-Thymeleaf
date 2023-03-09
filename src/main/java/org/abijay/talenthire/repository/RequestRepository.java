package org.abijay.talenthire.repository;

import org.abijay.talenthire.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RequestRepository extends JpaRepository<Request, Long> {
}
