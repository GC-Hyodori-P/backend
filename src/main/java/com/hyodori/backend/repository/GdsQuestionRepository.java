package com.hyodori.backend.repository;

import com.hyodori.backend.domain.GdsQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GdsQuestionRepository extends JpaRepository<GdsQuestion, Long> {
}
