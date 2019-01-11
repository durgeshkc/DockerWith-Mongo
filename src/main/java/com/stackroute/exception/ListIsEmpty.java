package com.stackroute.exception;

public class ListIsEmpty extends Exception {
    String errorMessage;

    public ListIsEmpty() {}      //deafault

    public ListIsEmpty(String errorMessage)
    {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
