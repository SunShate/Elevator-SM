package org.example.elevator.controller;

import org.example.elevator.Pair;
import org.example.elevator.State;
import org.example.elevator.event.IEvent;
import org.example.elevator.event.IEventReader;
import org.example.elevator.sm.ElevatorContext;

import java.util.List;

public class ElevatorController implements IElevatorController {

    private static final State UP = new State("move up");
    private static final State DOWN = new State("move down");

    @Override
    public void control(final IEventReader eventReader, final List<ElevatorContext> elevatorsContext) {
        while (eventReader.hasMoreEvents()) {
            IEvent event = eventReader.readEvent();
            int elevatorId = 0;
            int minFloorDifference;

            if (elevatorsContext.get(0).pollEvent() == null) {
                minFloorDifference = elevatorsContext.get(0).getCurrentFloor() - event.getCallFloor();
            } else {
                minFloorDifference = elevatorsContext.get(0).pollEvent().getDestinationFloor() - event.getCallFloor();
            }

            for (int i = 0; i < elevatorsContext.size(); ++i) {
                ElevatorContext context = elevatorsContext.get(i);

                int difference;

                // if elevator haven't events
                if (context.pollEvent() == null) {
                    difference = context.getCurrentFloor() - event.getCallFloor();

                    // if second elevator near the call floor
                    if (Math.abs(difference) <= minFloorDifference) {
                        elevatorId = i;
                    }
                } else {
                    difference = context.pollEvent().getDestinationFloor() - event.getCallFloor();

                    // floors: 10, elevatorFloor: 1, elevatorFloor: 8, [(4 -> 6), (5 -> 6), (7 -> 3), (2 -> 5), (6 -> 2)]
                    // 1 -> (4 -> 6),
                    // 1 -> (4 -> 6), (5 -> 6)
                    // 1 -> (4 -> 6), (5 -> 6), (6 -> 2)
                    // 8 -> (7 -> 3)
                    // 8 -> (7 -> 3), (2, -> 5)


                    // if elevator is going up or down we get events on his way for going up or down
                    if (Math.abs(difference) <= minFloorDifference
                            && event.getName().equals(UP.toString())
                            && context.pollEvent().getName().equals(UP.toString())
                            || Math.abs(difference) <= minFloorDifference
                            && event.getName().equals(DOWN.toString())
                            && context.pollEvent().getName().equals(DOWN.toString())) {
                        minFloorDifference = Math.abs(difference);
                        elevatorId = i;
                    }

                    // if elevator on the current floor and had calls before
                    if (difference == 0) {
                        minFloorDifference = difference;
                        elevatorId = i;
                    }
                }
            }

            // put event to elevators queue
            elevatorsContext.get(elevatorId).addEvent(event);
        }
        elevatorsContext.forEach(elevatorContext -> elevatorContext.addEvent(null));
    }
}
