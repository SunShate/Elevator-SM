package org.example.elevator.sm;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import au.com.bytecode.opencsv.CSVReader;
import org.example.elevator.Pair;
import org.example.elevator.State;

public class ElevatorStateMapFromCSV implements IElevatorStateMap {

    private String filePath = "./src/main/resources/config/elevatorStateMap.csv";
    private final State startState = new State("start");
    private final State finalState = new State("final state");
    private final Map<Pair<State, String>, State> states;
    private final Set<State> stateSet;

    ElevatorStateMapFromCSV() throws IOException {
        states = new HashMap<>();
        stateSet = new HashSet<>();
        State goingUp = new State("move up");
        State goingDown = new State("move down");
        State changeDoorStatus = new State("change door status");
        stateSet.add(startState);
        stateSet.add(finalState);
        stateSet.add(goingUp);
        stateSet.add(goingDown);
        stateSet.add(changeDoorStatus);

        CSVReader reader = new CSVReader(new FileReader(filePath), ',', '"', 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            states.put(new Pair<>(stringToState(nextLine[0]), nextLine[1]), stringToState(nextLine[2]));
        }

    }

    @Override
    public State getStartState() {
        return startState;
    }

    @Override
    public State getNextState(State state, String type) {
        return states.getOrDefault(new Pair<>(state, type), finalState);
    }

    private State stringToState(String stateString) {
        for (State state : stateSet) {
            if (state.toString().equals(stateString)) {
                return state;
            }
        }
        return null;
    }
}
