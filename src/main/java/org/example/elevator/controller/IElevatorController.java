package org.example.elevator.controller;

import org.example.elevator.event.IEventReader;
import org.example.elevator.sm.ElevatorContext;

import java.util.List;

public interface IElevatorController {

    void control(final IEventReader eventReader, final List<ElevatorContext> elevatorsContext);
}
