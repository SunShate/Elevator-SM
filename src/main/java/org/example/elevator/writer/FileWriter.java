package org.example.elevator.writer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * FileWriter class
 */
public class FileWriter implements IWriter, AutoCloseable {
    private java.io.FileWriter writer;
    private File fileToWrite;

    /**
     *  Constructor for FileWriter Class. Create new object FileWriter's type.
     * @param file - File into which is written code.
     * @throws FileWriterExeption if was some problems with file writing
     */
    public FileWriter(final File file) throws FileWriterExeption {
        fileToWrite = file;
        try {
            writer = new java.io.FileWriter(fileToWrite, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileWriterExeption("Ошибка при создании FileWriter");
        }
    }

    @Override
    public void write(final char symbol) throws FileWriterExeption {
        try {
            writer.write(symbol);
            writer.flush();
        } catch (IOException e) {
            throw new FileWriterExeption("Ошибка при записи символа");
        }

    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}
