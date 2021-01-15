package com.javabom.toby.chapter5.term.미디에이터패턴.airplane;

import com.javabom.toby.chapter5.term.미디에이터패턴.mediator.AirMediator;

public abstract class AirPlane {
    AirMediator airMediator;

    public abstract String getState();

    public abstract String getName();

    public void tryLanding() {
        boolean isPossible = airMediator.alert(this);

        System.out.println(getName() + "try landing : " + isPossible);
    }
}
