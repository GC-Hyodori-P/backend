package com.hyodori.backend.repository;

import com.hyodori.backend.domain.HappyPrescribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HappyPrescribeRepository extends JpaRepository<HappyPrescribe, Long> {

}
