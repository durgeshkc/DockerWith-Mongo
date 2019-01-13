package com.stackroute.exception;

public class TrackAlreadyPresent extends  Exception {

    private String errorMessage;

    public TrackAlreadyPresent() {}

    public TrackAlreadyPresent(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
