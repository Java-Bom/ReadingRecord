package com.javabom.toby.chapter5.term.미디에이터패턴.airplane;

import com.javabom.toby.chapter5.term.미디에이터패턴.mediator.AirMediator;

public class JejuAir extends AirPlane {
    public JejuAir(AirMediator airMediator) {
        this.airMediator = airMediator;
    }

    @Override
    public String getName() {
        return "Jeju Airplane";
    }

    @Override
    public String getState() {
        return "FLIGHT";
    }
}
