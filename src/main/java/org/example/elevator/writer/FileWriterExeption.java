package org.example.elevator.writer;

public class FileWriterExeption extends Exception {

    /**
     * FileWriterExeption's constructor
     * @param str it is message of exception
     */
    FileWriterExeption(final String str) {
        super(str);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
