package com.returnJourney.controller;

import com.returnJourney.exception.EventsException;
import com.returnJourney.exception.UserException;
import com.returnJourney.model.Event;
import com.returnJourney.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(Authentication authentication, @RequestBody Event event) throws UserException, EventsException {
        event = eventService.createEvent(authentication.getName(), event);
        return new ResponseEntity<Event>(event, HttpStatus.CREATED);
    }
    @PostMapping("/{calendarId}/addEvent")
    public ResponseEntity<?> addEvent(Authentication authentication, @PathVariable Long calendarId, @Valid @RequestBody Event event) throws Exception {
        String email = authentication.getName();
        return new ResponseEntity<Event>(eventService.addEventToCalendar(email, calendarId, event), HttpStatus.CREATED);
    }

    @PutMapping("/updateEvent/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable Long eventId, @RequestBody Event event) throws Exception {
        return ResponseEntity.accepted().body(eventService.updateEvent(eventId, event));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<?> getEventById(@PathVariable Long eventId) throws EventsException {
        return ResponseEntity.accepted().body(eventService.getEvent(eventId));
    }

    @GetMapping("/getEventsByCalendar/{calendarId}")
    public ResponseEntity<?> getEventsByCalendarId(@PathVariable Long calendarId) throws EventsException{
        return ResponseEntity.ok(eventService.getEventsByCalendarId(calendarId));
    }

    @DeleteMapping("/deleteEvent/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) throws Exception {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted successfully");
    }

    @GetMapping("/addAttendeesInEvent/{eventId}")
    public ResponseEntity<?> addUserInEvent(@PathVariable Long eventId, @RequestParam String email) throws Exception {
        return ResponseEntity.ok(eventService.addUserInEvent(email, eventId));
    }
}
