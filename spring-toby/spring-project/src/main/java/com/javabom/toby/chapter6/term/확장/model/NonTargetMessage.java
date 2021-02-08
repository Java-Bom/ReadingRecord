package com.javabom.toby.chapter6.term.확장.model;

public class NonTargetMessage implements Message {
    private final String message;

    public NonTargetMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
