package com.javabom.toby.chapter5.term.미디에이터패턴.mediator;

import com.javabom.toby.chapter5.term.미디에이터패턴.airplane.AirPlane;

import java.util.ArrayList;
import java.util.List;

public class Airport implements AirMediator {
    private List<AirPlane> airPlanes;

    public Airport() {
        airPlanes = new ArrayList<>();
    }

    @Override
    public void addAirPlane(final AirPlane airPlane) {
        this.airPlanes.add(airPlane);
    }

    @Override
    public boolean alert(final AirPlane airPlane) {
        return airPlanes.stream()
                .filter(cur -> !cur.equals(airPlane))
                .map(AirPlane::getState)
                .anyMatch(state -> state.equals("FLIGHT"));
    }
}
