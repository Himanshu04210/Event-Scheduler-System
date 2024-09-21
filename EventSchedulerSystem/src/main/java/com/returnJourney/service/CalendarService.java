package com.returnJourney.service;

import com.returnJourney.exception.CalendarException;
import com.returnJourney.exception.SuccessException;
import com.returnJourney.exception.UserException;
import com.returnJourney.model.Calendar;
import com.returnJourney.model.Event;

import java.util.List;

public interface CalendarService {
    public Calendar createCalendar(Calendar calendar, String userEmail) throws CalendarException, UserException;
    public List<Calendar> getCalendarsByUserEmail(String email) throws CalendarException, SuccessException;
    public Calendar updateCalendar(Long calendarId, Calendar updatedCalendar) throws CalendarException;
    public void deleteCalendar(Long calendarId) throws CalendarException;
    public List<Calendar> getAllCalendars()  throws CalendarException, SuccessException;
}
