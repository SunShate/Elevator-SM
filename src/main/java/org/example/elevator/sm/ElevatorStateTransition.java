package org.example.elevator.sm;

import org.example.elevator.State;

import java.io.IOException;

public class ElevatorStateTransition {
    private final IElevatorStateMap elevatorStateMap;

    /**
     * ElevatorStateTransition's constructor
     */
    public ElevatorStateTransition() throws IOException {
        this.elevatorStateMap = new ElevatorStateMapFromCSV();
    }

    /**
     * @param state current system state
     * @param type of text
     * @return new state
     */
    public State nextState(final State state, final String type) {
        return elevatorStateMap.getNextState(state, type);
    }

    public State getStartState() {
        return this.elevatorStateMap.getStartState();
    }
}
