package com.hyodori.backend.repository;

import com.hyodori.backend.domain.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {
}
