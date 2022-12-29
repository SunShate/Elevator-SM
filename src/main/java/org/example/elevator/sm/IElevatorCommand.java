package org.example.elevator.sm;

import org.example.elevator.writer.FileWriterExeption;

public interface IElevatorCommand {
    /**
     * @throws FileWriterExeption if was some problem with file writing
     */
    void execute() throws FileWriterExeption;
}
