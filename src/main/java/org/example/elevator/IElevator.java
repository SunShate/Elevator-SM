package org.example.elevator;

import org.example.elevator.event.IEventReader;
import org.example.elevator.sm.ElevatorContext;
import org.example.elevator.writer.FileWriterExeption;
import org.example.elevator.writer.IWriter;

import java.io.IOException;

/**
 * IEventElevator interface
 */
public interface IElevator {
    /**
     * @param writer - the result of command execution is written here.
     * @throws FileWriterExeption if was some problem with file writing
     */
    void calculateRoute(final IWriter writer, final ElevatorContext elevatorContext) throws FileWriterExeption, IOException;
}
