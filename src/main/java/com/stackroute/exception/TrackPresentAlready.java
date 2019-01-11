package com.stackroute.exception;

public class TrackPresentAlready extends  Exception {

    String errorMessage;

    public TrackPresentAlready () {}

    public TrackPresentAlready (String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
