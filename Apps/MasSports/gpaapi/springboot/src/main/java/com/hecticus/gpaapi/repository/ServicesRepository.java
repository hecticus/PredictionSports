package com.hecticus.gpaapi.repository;

import com.hecticus.gpaapi.domain.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicesRepository extends JpaRepository<Services, Long> {

    Optional<Services> findByName(String name);

    Optional<Services> findByIdentifier(String identifier);
}
