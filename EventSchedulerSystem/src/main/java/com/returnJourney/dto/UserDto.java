package com.returnJourney.dto;

import com.returnJourney.model.Calendar;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be empty")
    @Size(min = 3 , message = "Name length should not be less than 3")
    @Size(max = 40 , message = "Name length should not be greater than 40")
    private String name;
    @NotNull
    @NotBlank
    @Size(max = 50, message = "email should not be more than 50 character")
    private String email;
    @NotNull
    @NotBlank
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime modifiedDate;
    private List<Calendar> calenders;
}

enum Role {
    USER, ORGANIZER
}
