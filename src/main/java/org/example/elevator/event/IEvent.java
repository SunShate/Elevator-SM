package org.example.elevator.event;

/**
 * IEvent interface
 */
public interface IEvent {
    /**
     * @return token's name
     */
    String getName();

    /**
     * @return token's lexeme
     */
    int getCallFloor();
    int getDestinationFloor();
}
