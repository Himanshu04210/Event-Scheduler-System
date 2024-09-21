package com.returnJourney.service;

import com.returnJourney.exception.UserException;
import com.returnJourney.model.User;

import java.util.List;

public interface UserService {
    public User createUser(User user) throws UserException;
    public List<User> getAllUsers() throws UserException;
    public User getUserByEmail(String email) throws UserException;
}
