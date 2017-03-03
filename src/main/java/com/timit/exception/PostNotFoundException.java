package com.timit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by timit on 2017/3/3.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "No such Post")
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException() {
    }

    public static void main(String[] args) {

    }
}
