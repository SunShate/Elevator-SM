package org.example.elevator.writer;

/**
 * IWriter interface
 */
public interface IWriter {
    /**
     * function that writes the symbol
     * @param symbol - symbol that have to be written
     * @throws FileWriterExeption if was some problem with file writing
     */
    void write(char symbol) throws FileWriterExeption;
}
