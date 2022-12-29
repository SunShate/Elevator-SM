package org.example.elevator.sm;

import org.example.elevator.Pair;
import org.example.elevator.State;
import org.example.elevator.writer.FileWriterExeption;

import java.util.HashMap;
import java.util.Map;

public class ElevatorCommandMap {
    private final Map<Pair<State, String>, IElevatorCommand> commands;

    /**
     * ElevatorCommandMap's constructor
     * @param elevatorContext has all necessary info for commands
     */
    public ElevatorCommandMap(final ElevatorContext elevatorContext) {
        commands = new HashMap<>();
        State changeDoorStatusState = new State("change door status");
        State startState = new State("start");
        State moveUpState = new State("move up");
        State moveDownState = new State("move down");

        IElevatorCommand moveDown = new MoveDownCommand(elevatorContext);
        IElevatorCommand moveUp = new MoveUpCommand(elevatorContext);
        IElevatorCommand changeDoorStatus = new ChangeDoorStatusCommand(elevatorContext);
        IElevatorCommand ignore = new IgnoreCommand(elevatorContext);

        commands.put(new Pair<>(startState, "%d < %d"), moveUp);
        commands.put(new Pair<>(startState, "%d > %d"), moveDown);
        commands.put(new Pair<>(startState, "%d == %d"), changeDoorStatus);

        commands.put(new Pair<>(moveUpState, "%d != %d"), moveUp);
        commands.put(new Pair<>(moveUpState, "%d >= %d"), ignore);
        commands.put(new Pair<>(moveUpState, "%d == %d"), changeDoorStatus);

        commands.put(new Pair<>(moveDownState, "%d != %d"), moveDown);
        commands.put(new Pair<>(moveDownState, "%d <= %d"), ignore);
        commands.put(new Pair<>(moveDownState, "%d == %d"), changeDoorStatus);

        commands.put(new Pair<>(changeDoorStatusState, "%d == %d"), changeDoorStatus);
        commands.put(new Pair<>(changeDoorStatusState, "%d > %d"), moveDown);
        commands.put(new Pair<>(changeDoorStatusState, "%d < %d"), moveUp);
    }

    /**
     * function to get next command
     * @param state current system state
     * @param type of text that elevator have to handle
     * @throws FileWriterExeption if was some problems with file writing
     */
    public void getCommand(final State state, final String type) throws FileWriterExeption {
        commands.get(new Pair<>(state, type)).execute();
    }
}

