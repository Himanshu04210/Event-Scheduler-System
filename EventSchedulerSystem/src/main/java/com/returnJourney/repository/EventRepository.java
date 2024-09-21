package com.returnJourney.repository;

import com.returnJourney.model.Calendar;
import com.returnJourney.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<List<Event>> findByCalendar(Calendar calendar);
}
