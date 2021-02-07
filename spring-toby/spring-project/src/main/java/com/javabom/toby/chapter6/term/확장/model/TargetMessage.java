package com.javabom.toby.chapter6.term.확장.model;

public class TargetMessage implements Message {
    private final String message;

    public TargetMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
