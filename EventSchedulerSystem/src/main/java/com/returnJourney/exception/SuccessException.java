package com.returnJourney.exception;

import com.sun.net.httpserver.Authenticator;

public class SuccessException extends Exception{
    public SuccessException() { super(); }
    public SuccessException(String message) { super(message); }
}
