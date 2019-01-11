package com.stackroute.exception;

public class CommentAlreadyThere extends Exception {
    String errorMessage;

    public CommentAlreadyThere() {}      //deafault

    public CommentAlreadyThere(String errorMessage)
    {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
