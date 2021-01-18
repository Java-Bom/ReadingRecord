package com.javabom.toby.chapter5.term.미디에이터패턴;

import com.javabom.toby.chapter5.term.미디에이터패턴.airplane.Boeing;
import com.javabom.toby.chapter5.term.미디에이터패턴.airplane.JejuAir;
import com.javabom.toby.chapter5.term.미디에이터패턴.mediator.AirMediator;
import com.javabom.toby.chapter5.term.미디에이터패턴.mediator.Airport;

public class MediatorPatternMain {
    public static void main(String[] args) {
        AirMediator airMediator = new Airport();

        Boeing boeing = new Boeing(airMediator);
        JejuAir jejuAir = new JejuAir(airMediator);

        airMediator.addAirPlane(boeing);
        airMediator.addAirPlane(jejuAir);

        boeing.tryLanding();
        jejuAir.tryLanding();
    }
}
