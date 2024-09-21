package com.returnJourney.dto;

import com.returnJourney.model.Event;
import com.returnJourney.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class CalendarDto {
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String description;
    private LocalDateTime createdDate;
    private User owner;
    private List<Event> events;
}
