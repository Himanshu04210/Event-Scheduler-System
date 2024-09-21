package com.returnJourney.service.imple;

import com.returnJourney.exception.CalendarException;
import com.returnJourney.exception.EventsException;
import com.returnJourney.exception.UserException;
import com.returnJourney.model.Calendar;
import com.returnJourney.model.Event;
import com.returnJourney.model.User;
import com.returnJourney.repository.CalendarRepository;
import com.returnJourney.repository.EventRepository;
import com.returnJourney.repository.UserRepository;
import com.returnJourney.service.EventService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImple implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public Event addEventToCalendar(String email, Long calendarId, Event event) throws CalendarException, EventsException, UserException {
        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
            Calendar calendar = calendarRepository.findById(calendarId).orElseThrow(() -> new CalendarException("Calendar not found"));
            event.setCalendar(calendar);
            event.setOrganizer(user);
            return eventRepository.save(event);
        }
        catch(Exception ex) {
            throw new EventsException("Something went wrong because " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Event updateEvent(Long eventId, Event updatedEvent) throws CalendarException, EventsException {
        try {
            Event existingEvent = eventRepository.findById(eventId).orElseThrow(() -> new EventsException("Event not found"));
            existingEvent.setTitle(updatedEvent.getTitle());
            existingEvent.setDescription(updatedEvent.getDescription());
            existingEvent.setStartTime(updatedEvent.getStartTime());
            existingEvent.setEndTime(updatedEvent.getEndTime());
            existingEvent.setLocation(updatedEvent.getLocation());
            return eventRepository.save(existingEvent);
        }
        catch(Exception ex) {
            throw new EventsException("Something went wrong because " + ex.getMessage());
        }
    }

    @Override
    public Event getEvent(Long eventId) throws EventsException {
        try {
            return eventRepository.findById(eventId).orElseThrow(() -> new EventsException("Event not found"));
        }
        catch(Exception ex) {
            throw new EventsException("Something went wrong because " + ex.getMessage());
        }
    }

    @Override
    public List<Event> getEventsByCalendarId(Long calendarId) throws EventsException {
        List<Event> events = null;
        try {
            Calendar calendar = calendarRepository.findById(calendarId).orElseThrow(() -> new EventsException("Calendar not found"));
            events = eventRepository.findByCalendar(calendar).orElseThrow(() -> new EventsException("Event not found"));
        }
        catch(Exception ex) {
            throw new EventsException("Something went wrong because " + ex.getMessage());
        }
        return events;
    }

    @Override
    public Event addUserInEvent(String email, Long eventId) throws EventsException, UserException {
        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("No user found"));
            Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventsException("No event found"));
            event.getAttendees().add(user);
            return eventRepository.save(event);
        }
        catch (Exception ex) {
            throw new EventsException("Something went wrong because " + ex.getMessage());
        }
    }

    @Override
    public void deleteEvent(Long eventId) throws EventsException {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventsException("Event not found"));
        eventRepository.delete(event);
    }

    @Override
    public Event createEvent(String email, Event event) throws EventsException, UserException {
        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
            event.setOrganizer(user);
            return eventRepository.save(event);
        }
        catch(Exception ex) {
            throw new EventsException("Something went wrong because " + ex.getMessage());
        }
    }
}
