package org.example.elevator.sm;

import org.example.elevator.State;

public interface IElevatorStateMap {
    State getStartState();
    State getNextState(final State state, final String type);
}
