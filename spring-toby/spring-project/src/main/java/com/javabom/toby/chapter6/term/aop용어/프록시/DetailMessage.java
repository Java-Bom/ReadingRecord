package com.javabom.toby.chapter6.term.aop용어.프록시;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DetailMessage implements Message{
    private final String text;
}
