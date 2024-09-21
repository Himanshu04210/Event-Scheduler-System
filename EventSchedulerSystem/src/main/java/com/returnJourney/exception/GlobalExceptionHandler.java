package com.returnJourney.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
		MyErrorDetails err= new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(request.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	//This method will handle those method which will contains validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorDetails> myMathodArguNotValidExceptionHandler(MethodArgumentNotValidException mve) {
		MyErrorDetails myErorDetails = new MyErrorDetails();
		myErorDetails.setTimeStamp(LocalDateTime.now());
		myErorDetails.setMessage("Validation error");
		myErorDetails.setDetails(mve.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(myErorDetails, HttpStatus.BAD_REQUEST);
    }
    //This exception handler will handle those exception when we try to call that method which is not exist in the application
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> methodNotFoundExceptionHandler(NoHandlerFoundException nfe, WebRequest req) {
		MyErrorDetails myErorDetails = new MyErrorDetails();
		myErorDetails.setTimeStamp(LocalDateTime.now());
		myErorDetails.setMessage(nfe.getMessage());
		myErorDetails.setDetails(req.getDescription(false));
		return new ResponseEntity<>(myErorDetails, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(UserException.class)
	public ResponseEntity<?> UserExceptionHandler(UserException ue, WebRequest request) {
		MyErrorDetails err= new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ue.getMessage());
		err.setDetails(request.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CalendarException.class)
	public ResponseEntity<?> CalendarExceptionHandler(CalendarException ce, WebRequest request) {
		MyErrorDetails err= MyErrorDetails.builder()
				.timeStamp(LocalDateTime.now()).message(ce.getMessage())
				.details(request.getDescription(false))
				.build();
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EventsException.class)
	public ResponseEntity<?> EventsExceptionHandler(EventsException ce, WebRequest request) {
		MyErrorDetails err= MyErrorDetails.builder()
				.timeStamp(LocalDateTime.now()).message(ce.getMessage())
				.details(request.getDescription(false))
				.build();
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> BadCredentialExceptionHandler(EventsException ce, WebRequest request) {
		MyErrorDetails err= MyErrorDetails.builder()
				.timeStamp(LocalDateTime.now()).message(ce.getMessage())
				.details(request.getDescription(false))
				.build();
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.NOT_FOUND);
	}
}
