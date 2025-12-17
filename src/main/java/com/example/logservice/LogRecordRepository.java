
package com.example.logservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface LogRecordRepository extends JpaRepository<LogRecord, Long> {
    // TODO: add query methods for filters
    
    // Query for top apps by count within a time range
    @Query("SELECT r.app, COUNT(r) FROM LogRecord r " +
           "WHERE (:from IS NULL OR r.ts >= :from) " +
           "AND (:to IS NULL OR r.ts <= :to) " +
           "GROUP BY r.app " +
           "ORDER BY COUNT(r) DESC")
    List<Object[]> findTopAppsByCount(@Param("from") Instant from, @Param("to") Instant to);
    
    // Query for error logs by app within time buckets
    @Query("SELECT r FROM LogRecord r " +
           "WHERE r.app = :app " +
           "AND r.level = 'ERROR' " +
           "ORDER BY r.ts ASC")
    List<LogRecord> findErrorLogsByApp(@Param("app") String app);
}
