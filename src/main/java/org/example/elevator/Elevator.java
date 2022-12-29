package org.example.elevator;

import org.example.elevator.sm.ElevatorCommandMap;
import org.example.elevator.sm.ElevatorContext;
import org.example.elevator.sm.ElevatorStateTransition;
import org.example.elevator.event.IEvent;
import org.example.elevator.writer.FileWriterExeption;
import org.example.elevator.writer.IWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.IOException;

/**
 * Elevator class
 */
public class Elevator implements IElevator {
    private final ScriptEngine se;

    /**
     * Constructor for Elevator class. Create new object Elevator's type.
     */
    public Elevator() {
        ScriptEngineManager sem = new ScriptEngineManager();
        this.se = sem.getEngineByName("JavaScript");
    }

    /**
     * function that proceed elevator logic
     *
     * @param writer - the result of command execution is written here.
     * @throws FileWriterExeption if was some problems with file writing
     */
    public void calculateRoute(final IWriter writer, final ElevatorContext elevatorContext) throws FileWriterExeption, IOException {
        ElevatorStateTransition elevatorStateTransition = new ElevatorStateTransition();
        State currentState = elevatorStateTransition.getStartState();
        ElevatorCommandMap elevatorCommandMap = new ElevatorCommandMap(elevatorContext);

        IEvent event;

        while((event = elevatorContext.pollEvent()) != null) {
            elevatorContext.addEvent(event);
            elevatorCommandMap.getCommand(currentState, event.getName());
            currentState = elevatorStateTransition.nextState(currentState, event.getName());
        }
    }
}
