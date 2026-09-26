package com.hecticus.gpaapi.repository;

import com.hecticus.gpaapi.domain.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientsRepository extends JpaRepository<Clients, Long> {

    Optional<Clients> findByMsisdnAndConfirm(Long msisdn, String confirm);

    Optional<Clients> findByIdentifier(String identifier);
}
