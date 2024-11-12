package com.controlHorari.Repository;

import com.controlHorari.Entity.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {

    @Query(value = "SELECT * FROM checkins WHERE DATE(timestamp) = :data", nativeQuery = true)
    List<Checkin> getCheckinByTimestamp(LocalDateTime data);
}
