package com.hecticus.gpaapi.repository;

import com.hecticus.gpaapi.domain.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {

    List<LogEntry> findTop200ByIdentifierOrderByIdDesc(String identifier);

    List<LogEntry> findTop100ByIdentifierInOrderByIdDesc(Collection<String> identifiers);
}
