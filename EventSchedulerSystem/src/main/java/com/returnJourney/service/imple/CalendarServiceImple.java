package com.returnJourney.service.imple;

import com.returnJourney.exception.CalendarException;
import com.returnJourney.exception.SuccessException;
import com.returnJourney.exception.UserException;
import com.returnJourney.model.Calendar;
import com.returnJourney.model.User;
import com.returnJourney.repository.CalendarRepository;
import com.returnJourney.repository.UserRepository;
import com.returnJourney.service.CalendarService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalendarServiceImple implements CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Calendar
    @Override
    @Transactional
    public Calendar createCalendar(Calendar calendar, String userEmail) throws CalendarException, UserException {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UserException("User not found"));
        calendar.setCreatedDate(LocalDateTime.now());
        calendar.setModifiedDate(LocalDateTime.now());
        calendar.setOwner(user);
        return calendarRepository.save(calendar);
    }

    // Get Calendars by User Email
    @Override
    public List<Calendar> getCalendarsByUserEmail(String email) throws CalendarException, SuccessException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new CalendarException("User not found"));
        return calendarRepository.findByOwner(user).orElseThrow(() -> new SuccessException("Data not Found"));
    }

    // Update Calendar
    @Override
    @Transactional
    public Calendar updateCalendar(Long calendarId, Calendar updatedCalendar) throws CalendarException {
        try {
            Calendar calendar = calendarRepository.findById(calendarId).orElseThrow(() -> new CalendarException("Calendar not found"));
            calendar.setName(updatedCalendar.getName());
            calendar.setDescription(updatedCalendar.getDescription());
            calendar.setModifiedDate(LocalDateTime.now());
            return calendarRepository.save(calendar);
        }
        catch (Exception ex) {
            throw new CalendarException("Something went wrong because " + ex.getMessage());
        }
    }

    // Delete Calendar
    @Override
    public void deleteCalendar(Long calendarId) throws CalendarException{
        try {
            Calendar calendar = calendarRepository.findById(calendarId).orElseThrow(() -> new CalendarException("Calendar not found"));
            calendarRepository.delete(calendar);
        }
        catch(Exception ex) {
            throw new CalendarException("Something went wrong because " + ex.getMessage());
        }
    }

    @Override
    public List<Calendar> getAllCalendars()  throws CalendarException, SuccessException {
        List<Calendar> calendars = null;
        try {
            calendars = calendarRepository.findAll();
            if(calendars.isEmpty()) throw new SuccessException("No Data available");
        }
        catch (Exception ex) {
            throw new CalendarException("Something went wrong because " + ex.getMessage());
        }
        return calendars;
    }
}
