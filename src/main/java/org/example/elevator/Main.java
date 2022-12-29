package org.example.elevator;

import org.example.elevator.controller.ElevatorController;
import org.example.elevator.controller.IElevatorController;
import org.example.elevator.event.IEventReader;
import org.example.elevator.event.EventReader;
import org.example.elevator.sm.ElevatorContext;
import org.example.elevator.writer.FileWriter;
import org.example.elevator.writer.FileWriterExeption;
import org.example.elevator.writer.IWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main application entry point
 */
public class Main {
    /**
     * Main function for app
     *
     * @param args - console arguments
     * @throws FileWriterExeption if was some problem with file writing
     */
    public static void main(final String[] args) throws FileWriterExeption, IOException {
        String path = ".";
        File dir = new File(path);
        File fileToRead = new File(dir, "input.txt");
        File fileToWrite = new File(dir, "result.txt");

        // IO
        BufferedReader fileReader = new BufferedReader(new FileReader(fileToRead));
        IWriter fileWriter = new FileWriter(fileToWrite);
        IEventReader eventReader = new EventReader(fileReader);

        IElevatorController controller = new ElevatorController();
        IElevator firstElevator = new Elevator();
        IElevator secondElevator = new Elevator();

        // contexts
        ElevatorContext firstElevatorContext = new ElevatorContext(
                fileWriter,
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1])
        );
        ElevatorContext secondElevatorContext = new ElevatorContext(
                fileWriter,
                Integer.parseInt(args[0]),
                Integer.parseInt(args[2])
        );

        // fill elevators queues
        List<ElevatorContext> contexts = new ArrayList<>(List.of(firstElevatorContext, secondElevatorContext));
        controller.control(eventReader, contexts);

        // start elevators
        firstElevator.calculateRoute(fileWriter, firstElevatorContext);
        secondElevator.calculateRoute(fileWriter, secondElevatorContext);

        // print how many floors the route took for each event
        firstElevatorContext.getFloorsTake().forEach(event -> {
            System.out.println("First elevator route takes:");
            System.out.println(event.toString());
        });
        secondElevatorContext.getFloorsTake().forEach(event -> {
            System.out.println("\nSecond elevator route takes:");
            System.out.println(event.toString());
        });
    }
}

