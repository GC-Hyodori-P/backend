package com.hyodori.backend.repository;

import com.hyodori.backend.domain.UserGdsResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGdsResponseRepository extends JpaRepository<UserGdsResponse, Long> {
    List<UserGdsResponse> findAllByUser_UserIdAndCycle(Long userId, int cycle);
}
