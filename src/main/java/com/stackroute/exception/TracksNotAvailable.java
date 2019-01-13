package com.stackroute.exception;

public class TracksNotAvailable extends Exception {
    private String errorMessage;

    public TracksNotAvailable() {}      //deafault

    public TracksNotAvailable(String errorMessage)
    {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
