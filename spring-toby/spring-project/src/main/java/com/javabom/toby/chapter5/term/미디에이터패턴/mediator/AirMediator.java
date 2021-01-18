package com.javabom.toby.chapter5.term.미디에이터패턴.mediator;

import com.javabom.toby.chapter5.term.미디에이터패턴.airplane.AirPlane;

public interface AirMediator {
    void addAirPlane(AirPlane airPlane);

    boolean alert(AirPlane airPlane);
}
