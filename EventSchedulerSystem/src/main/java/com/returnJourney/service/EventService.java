package com.returnJourney.service;

import com.returnJourney.exception.CalendarException;
import com.returnJourney.exception.EventsException;
import com.returnJourney.exception.UserException;
import com.returnJourney.model.Event;
import com.returnJourney.model.User;

import java.util.List;

public interface EventService {
    public Event addEventToCalendar(String email, Long calendarId, Event event) throws CalendarException, EventsException, UserException;
    public Event updateEvent(Long eventId, Event event) throws CalendarException, EventsException;
    public void deleteEvent(Long eventId) throws CalendarException, EventsException;
    public Event getEvent(Long eventId) throws EventsException;
    public List<Event> getEventsByCalendarId(Long calendarId) throws EventsException;
    public Event addUserInEvent(String email, Long eventId) throws EventsException, UserException;
    public Event createEvent(String email, Event event) throws EventsException, UserException;
}
