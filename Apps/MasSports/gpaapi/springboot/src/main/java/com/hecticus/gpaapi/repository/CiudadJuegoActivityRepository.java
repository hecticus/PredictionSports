package com.hecticus.gpaapi.repository;

import com.hecticus.gpaapi.domain.CiudadJuegoActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CiudadJuegoActivityRepository extends JpaRepository<CiudadJuegoActivity, Long> {

    Optional<CiudadJuegoActivity> findFirstByClickIdOrderByIdDesc(String clickId);
}
