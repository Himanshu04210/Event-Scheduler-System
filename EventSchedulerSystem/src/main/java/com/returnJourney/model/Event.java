package com.returnJourney.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name="TITLE")
    private String title;

    @NotNull
    @NotBlank
    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="START_DATE", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endTime;

    @Column(name="LOCATION")
    private String location;  // Only organizers can update this

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZER_ID", nullable = false)
    private User organizer;  // The organizer of this event

    @ManyToMany
    @JoinTable(
            name = "EVENT_ATTENDEES",
            joinColumns = @JoinColumn(name = "EVENT_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    )
    private List<User> attendees;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CALENDAR_ID", nullable = false)
    private Calendar calendar;
}
