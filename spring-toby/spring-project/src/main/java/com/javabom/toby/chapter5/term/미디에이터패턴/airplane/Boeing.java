package com.javabom.toby.chapter5.term.미디에이터패턴.airplane;

import com.javabom.toby.chapter5.term.미디에이터패턴.mediator.AirMediator;

public class Boeing extends AirPlane {
    public Boeing(AirMediator airMediator) {
        this.airMediator = airMediator;
    }

    @Override
    public String getName() {
        return "Boeing Airplane";
    }

    @Override
    public String getState() {
        return "LANDING";
    }
}
