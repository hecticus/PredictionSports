package com.hecticus.gpaapi.repository;

import com.hecticus.gpaapi.domain.Config;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfigRepository extends JpaRepository<Config, Long> {

    Optional<Config> findByConfigKey(String configKey);
}
