package com.hecticus.gpaapi.repository;

import com.hecticus.gpaapi.domain.ClienteAppland;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteApplandRepository extends JpaRepository<ClienteAppland, Long> {

    Optional<ClienteAppland> findFirstByMsisdnOrderByIdAsc(String msisdn);

    Optional<ClienteAppland> findByIdentifier(String identifier);
}
