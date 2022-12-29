package org.example.elevator.sm;

import org.example.elevator.writer.FileWriterExeption;

public class ChangeDoorStatusCommand implements IElevatorCommand {
    private final ElevatorContext context;

    public ChangeDoorStatusCommand(final ElevatorContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        context.setOpenedDoor(!context.isOpenedDoor());
    }
}
