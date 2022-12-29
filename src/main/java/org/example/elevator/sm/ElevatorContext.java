package org.example.elevator.sm;

import org.example.elevator.Pair;
import org.example.elevator.event.IEvent;
import org.example.elevator.writer.IWriter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ElevatorContext {
    private final int floors;
    private int currentFloor;
    private final IWriter writer;
    private final Queue<IEvent> events;

    private final List<Pair<IEvent, Integer>> floorsTake;

    private boolean isOpenedDoor;

    /**
     * ElevatorContext's constructor
     * @param writer to write
     */
    public ElevatorContext(final IWriter writer, int floors, int startFloor) {
        this.writer = writer;
        this.floors = floors;
        currentFloor = startFloor;
        events = new LinkedList<>();
        isOpenedDoor = false;
        floorsTake = new ArrayList<>();
    }

    public boolean isOpenedDoor() {
        return isOpenedDoor;
    }

    public void setOpenedDoor(final boolean openedDoor) {
        this.isOpenedDoor = openedDoor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(final int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public IWriter getWriter() {
        return writer;
    }

    public IEvent pollEvent() {
        return events.poll();
    }
    public Queue<IEvent> getEvents() {
        return events;
    }

    public List<Pair<IEvent, Integer>> getFloorsTake() {
        return floorsTake;
    }

    public void addEvent(final IEvent event) {
        events.add(event);
        if (event != null) {
            floorsTake.add(new Pair<>(event, 0));
        }
    }
}
