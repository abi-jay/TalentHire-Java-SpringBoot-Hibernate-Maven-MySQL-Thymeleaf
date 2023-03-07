package org.abijay.talenthire.repository;

import org.abijay.talenthire.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
}
