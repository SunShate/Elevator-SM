package org.example.elevator.event;

/**
 * IEventReader interface
 */
public interface IEventReader {
    /**
     * @return true if eventReader has more events
     */
    boolean hasMoreEvents();

    /**
     * @return IEvent that was read
     */
    IEvent readEvent();
}
