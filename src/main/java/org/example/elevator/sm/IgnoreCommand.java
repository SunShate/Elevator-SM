package org.example.elevator.sm;

import org.example.elevator.writer.FileWriterExeption;

public class IgnoreCommand implements IElevatorCommand {
    private ElevatorContext context;

    /**
     * IgnoreCommand's constructor
     * @param context has all necessary information for command execution
     */
    public IgnoreCommand(final ElevatorContext context) {
        this.context = context;
    }
    @Override
    public void execute() throws FileWriterExeption {

    }
}
