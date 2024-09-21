package com.returnJourney.exception;

import java.time.LocalDateTime;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyErrorDetails {
	private LocalDateTime timeStamp;
	private String message;
	private String details;
}
