package com.returnJourney.service.imple;

import com.returnJourney.exception.UserException;
import com.returnJourney.model.User;
import com.returnJourney.repository.CalendarRepository;
import com.returnJourney.repository.EventRepository;
import com.returnJourney.repository.UserRepository;
import com.returnJourney.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImple implements UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private EventRepository eventRepository;
    @Autowired private CalendarRepository calendarRepository;

    @Override
    @Transactional
    public User createUser(User user) throws UserException {
        if(user == null) throw new UserException("User can't be null");
        try {
            user.setRole("ROLE_" + user.getRole());
            Optional<User> userOpt = userRepository.findByEmail(user.getEmail());
            if(userOpt.isPresent()) throw new UserException("User already Exist");
            user = userRepository.save(user);
            return user;
        }
        catch(Exception ex) {
            throw new UserException("Something went wrong because " + ex.getLocalizedMessage());
        }
    }

    @Override
    public List<User> getAllUsers() throws UserException {
        try {
            List<User> users = userRepository.findAll();
            if(users.isEmpty()) throw new UserException("No User is present");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return users;
        }
        catch(Exception ex) {
            throw new UserException("Something went wrong because " + ex.getLocalizedMessage());
        }
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        if(email == null || email.isEmpty()) throw new UserException("Email can't be empty");
        try {
            return userRepository.findByEmail(email).orElseThrow(() -> new UserException("No user found"));
        }
        catch(Exception ex) {
            throw new UserException("Something went wrong because " + ex.getLocalizedMessage());
        }
    }
}
