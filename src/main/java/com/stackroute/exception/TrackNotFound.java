package com.stackroute.exception;

public class TrackNotFound extends Exception{
    private String errorMessage;

    public TrackNotFound() {}      //deafault

    public TrackNotFound(String errorMessage)
    {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
