package com.hecticus.gpaapi.repository;

import com.hecticus.gpaapi.domain.MaxgameActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaxgameActivityRepository extends JpaRepository<MaxgameActivity, Long> {

    Optional<MaxgameActivity> findFirstByClickIdOrderByIdDesc(String clickId);

    List<MaxgameActivity> findTop100ByOrderByIdDesc();
}
