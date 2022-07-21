package com.olnester.bookslist.exception;

public class BookNotFoundException extends Throwable {

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
