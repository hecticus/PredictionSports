package com.hecticus.gpaapi.repository;

import com.hecticus.gpaapi.domain.LearnLiveActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearnLiveActivityRepository extends JpaRepository<LearnLiveActivity, Long> {

    List<LearnLiveActivity> findTop100ByOrderByIdDesc();
}
