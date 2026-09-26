package com.hecticus.gpaapi.repository;

import com.hecticus.gpaapi.domain.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
}
