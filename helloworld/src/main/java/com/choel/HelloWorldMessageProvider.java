package com.choel;

public class HelloWorldMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "hello World";
    }
}
