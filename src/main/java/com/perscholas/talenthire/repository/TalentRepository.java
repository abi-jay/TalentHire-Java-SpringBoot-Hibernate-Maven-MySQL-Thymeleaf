package com.perscholas.talenthire.repository;

import com.perscholas.talenthire.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TalentRepository extends JpaRepository<Talent, Long> {
    Optional<Talent> findByTalent(String talent);
}
