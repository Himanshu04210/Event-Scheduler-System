package com.returnJourney.repository;

import com.returnJourney.model.Calendar;
import com.returnJourney.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Optional<List<Calendar>> findByOwner(User user);
}
