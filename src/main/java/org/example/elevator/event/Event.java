package org.example.elevator.event;

/**
 * Token class
 */
public class Event implements IEvent {
    private final String name;
    private final int callFloor;
    private final int destinationFloor;

    /**
     * @param name token's name
     * @param callFloor call floor
     * @param destinationFloor call floor
     */
    public Event(final String name, final int callFloor, final int destinationFloor) {
        this.name = name;
        this.callFloor = callFloor;
        this.destinationFloor = destinationFloor;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCallFloor() {
        return callFloor;
    }

    @Override
    public int getDestinationFloor() {
        return destinationFloor;
    }

}
