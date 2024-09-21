package com.returnJourney.dto;

import com.returnJourney.model.Calendar;
import com.returnJourney.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class EventDto {
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;  // Only organizers can update this
    private User organizer;  // The organizer of this event
    private List<User> attendees;
    private Calendar calendar;
}

