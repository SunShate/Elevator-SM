package org.example.elevator.sm;

import org.example.elevator.writer.FileWriterExeption;

public class MoveDownCommand implements IElevatorCommand {
    private final ElevatorContext context;

    public MoveDownCommand(final ElevatorContext context) {
        this.context = context;
    }
    @Override
    public void execute() throws FileWriterExeption {
        context.setCurrentFloor(context.getCurrentFloor() - 1);
    }
}
