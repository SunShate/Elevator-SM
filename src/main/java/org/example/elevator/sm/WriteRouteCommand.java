package org.example.elevator.sm;

public class WriteRouteCommand implements IElevatorCommand {
    private ElevatorContext context;

    /**
     * WriteLexemCommand's constructor
     * @param context has all necessary information for command execution
     */
    public WriteRouteCommand(final ElevatorContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
    }
}
