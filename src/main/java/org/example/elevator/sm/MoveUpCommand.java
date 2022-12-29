package org.example.elevator.sm;

import org.example.elevator.writer.FileWriterExeption;

public class MoveUpCommand implements IElevatorCommand {
    private final ElevatorContext context;

    public MoveUpCommand(final ElevatorContext context) {
        this.context = context;
    }
    @Override
    public void execute() throws FileWriterExeption {
        context.setCurrentFloor(context.getCurrentFloor() + 1);
    }
}
