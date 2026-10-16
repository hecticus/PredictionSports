package com.hecticus.gpaapi.repository;

import com.hecticus.gpaapi.domain.BliveActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BliveActivityRepository extends JpaRepository<BliveActivity, Long> {

    List<BliveActivity> findTop100ByOrderByIdDesc();
}
