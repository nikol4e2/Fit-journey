package com.nikola.fitjourney.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException() {
        super("Password do not match");
    }
}
