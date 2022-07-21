package com.olnester.bookslist.exception;

public class BookAlreadyExists extends Exception{

    public BookAlreadyExists(String message) {
        super(message);
    }

    public BookAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
