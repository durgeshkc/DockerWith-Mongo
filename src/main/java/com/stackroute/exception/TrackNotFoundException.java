package com.stackroute.exception;

public class TrackNotFoundException extends Exception{
    String errorMessage;

    public TrackNotFoundException() {}      //deafault

    public TrackNotFoundException(String errorMessage)
    {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
