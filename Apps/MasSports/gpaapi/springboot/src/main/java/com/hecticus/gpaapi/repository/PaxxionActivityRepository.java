package com.hecticus.gpaapi.repository;

import com.hecticus.gpaapi.domain.PaxxionActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaxxionActivityRepository extends JpaRepository<PaxxionActivity, Long> {

    List<PaxxionActivity> findTop100ByOrderByIdDesc();
}
