package com.stackroute.exception;

public class SameCommentExists extends Exception {
    private String errorMessage;

    public SameCommentExists() {}      //deafault

    public SameCommentExists(String errorMessage)
    {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
