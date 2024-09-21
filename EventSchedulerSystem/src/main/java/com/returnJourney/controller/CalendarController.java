package com.returnJourney.controller;

import com.returnJourney.exception.CalendarException;
import com.returnJourney.exception.SuccessException;
import com.returnJourney.exception.UserException;
import com.returnJourney.model.Calendar;
import com.returnJourney.service.CalendarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendars")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    // Create Calendar
    @PostMapping("/create")
    public ResponseEntity<?> createCalendar(@Valid @RequestBody Calendar calendar, @RequestParam String userEmail) throws Exception {
        return ResponseEntity.ok(calendarService.createCalendar(calendar, userEmail));
    }

    // Get all Calendars for a User
    @GetMapping("/users/{email}")
    public ResponseEntity<?> getCalendarsByUser(@PathVariable String email) throws CalendarException, SuccessException {
        return ResponseEntity.ok(calendarService.getCalendarsByUserEmail(email));
    }

    @GetMapping("/currentUser")
    public ResponseEntity<?> getLoggedInUserCalenders(Authentication authentication) throws CalendarException, SuccessException {
        String email = authentication.getName();
        return ResponseEntity.ok(calendarService.getCalendarsByUserEmail(email));
    }    // Update Calendar by ID

    @PutMapping("/update/{calendarId}")
    public ResponseEntity<?> updateCalendar(@PathVariable Long calendarId, @RequestBody Calendar calendar) throws Exception {
        return ResponseEntity.ok(calendarService.updateCalendar(calendarId, calendar));
    }

    // Delete Calendar by ID
    @DeleteMapping("/delete/{calendarId}")
    public ResponseEntity<?> deleteCalendar(@PathVariable Long calendarId) throws Exception {
        calendarService.deleteCalendar(calendarId);
        return ResponseEntity.ok("Calendar deleted successfully");
    }

    @GetMapping("/getAllCalendar")
    public ResponseEntity<List<?>> getAllCalenderList() throws SuccessException, CalendarException {
        return ResponseEntity.ok(calendarService.getAllCalendars());
    }
}
