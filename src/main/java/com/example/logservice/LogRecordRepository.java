
package com.example.logservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRecordRepository extends JpaRepository<LogRecord, Long> {
    // TODO: add query methods for filters
}
