package com.glowguide.repository;

import com.glowguide.model.Concern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcernRepository extends JpaRepository<Concern, Long> {
    List<Concern> findByClientEmail(String clientEmail);
    List<Concern> findBySpecialistEmailOrderByIdDesc(String specialistEmail);
}