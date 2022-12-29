package org.example.elevator.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Event class
 */
public class EventReader implements IEventReader {
    private final BufferedReader reader;
    private final Queue<Event> events = new LinkedList<>();

    private static final Pattern NUMBER_DECLARATION = Pattern.compile("(\\(\\d,\\s\\d\\))");

    /**
     * @param reader - it is contain text that have to be split into events
     * @throws IOException if was some problem with file reading
     */
    public EventReader(final BufferedReader reader) throws IOException {
        this.reader = reader;
        splitIntoEvents();
    }


    /**
     * @return true if event has more events
     */
    @Override
    public boolean hasMoreEvents() {
        return events.peek() == null;
    }

    private void splitIntoEvents() throws IOException {
        Matcher matcher;
        String line;
        while ((line = reader.readLine()) != null) {
            matcher = NUMBER_DECLARATION.matcher(line);
            while (matcher.find()) {
                int from = Integer.parseInt(matcher.group(2));
                int to = Integer.parseInt(matcher.group(3));

                if (from > to) {
                    events.add(new Event("down", from, to));
                }
                if (from < to) {
                    events.add(new Event("up", from, to));
                }
            }
        }
    }

    @Override
    public IEvent readEvent() {
        return events.poll();
    }
}
