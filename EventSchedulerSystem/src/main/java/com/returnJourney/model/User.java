package com.returnJourney.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be empty")
    @Size(min = 3 , message = "Name length should not be less than 3")
    @Size(max = 40 , message = "Name length should not be greater than 40")
    @Column(name = "NAME")
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 50, message = "email should not be more than 50 character")
    @Column(name="EMAIL", unique = true)
    private String email;

    @NotNull
    @NotBlank
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @Column(name="PASSWORD")
    private String password;

    /*@Enumerated(EnumType.STRING)*/
    private String role;

    @Column(name="CREATED_DATE", updatable = false)
    private LocalDateTime createdDate;

    @Column(name="MODIFIED_DATE")
    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Calendar> calenders;
}

