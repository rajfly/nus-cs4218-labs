package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.*;
import java.nio.charset.CharacterCodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_REDIR_INPUT;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_REDIR_OUTPUT;


class IORedirectionHandlerTest {
    private IORedirectionHandler ioRedirHandler;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ArgumentResolver argumentResolver;
    private List<String> argumentList;
    private final String inputRedirArrow = String.valueOf(CHAR_REDIR_INPUT);
    private final String outputRedirArrow = String.valueOf(CHAR_REDIR_OUTPUT);
    private final static String FILE1_PATH_STRING = "src/test/java/sg/edu/nus/comp/cs4218/impl/util/file1.txt";
    private final Path file1Path = Paths.get(FILE1_PATH_STRING);
    private final static String FILE2_PATH_STRING = "src/test/java/sg/edu/nus/comp/cs4218/impl/util/file2.txt";
    private final Path file2Path = Paths.get(FILE2_PATH_STRING);


    @BeforeEach
    void setUp() {
        inputStream = new ByteArrayInputStream("test".getBytes());
        outputStream = new ByteArrayOutputStream();
        argumentResolver = new ArgumentResolver();
        argumentList = new ArrayList<>();
        ioRedirHandler = null;
    }

    void clearFiles() throws IOException {
        // Clear the contents of file1
        Files.write(file1Path, new byte[0]);

        // Clear the contents of file2
        Files.write(file2Path, new byte[0]);
    }

    @Test
    void extractRedirOptions_emptyArgs_throwShellException() {
        ioRedirHandler = new IORedirectionHandler(argumentList,inputStream,outputStream,argumentResolver);
        assertThrows(ShellException.class,() -> ioRedirHandler.extractRedirOptions());
    }

    @Test
    void extractRedirOptions_nullArgs_throwShellException() {
        ioRedirHandler = new IORedirectionHandler(null,inputStream,outputStream,argumentResolver);
        assertThrows(ShellException.class,() -> ioRedirHandler.extractRedirOptions());
    }

    @Test
    void extractRedirOptions_twoInputRedir_throwShellException() {
        argumentList.add(inputRedirArrow);
        argumentList.add(inputRedirArrow);
        ioRedirHandler = new IORedirectionHandler(argumentList,inputStream,outputStream,argumentResolver);
        assertThrows(ShellException.class,() -> ioRedirHandler.extractRedirOptions());
    }

    @Test
    void extractRedirOptions_twoOutputRedir_throwShellException() {
        argumentList.add(outputRedirArrow);
        argumentList.add(outputRedirArrow);
        ioRedirHandler = new IORedirectionHandler(argumentList,inputStream,outputStream,argumentResolver);
        assertThrows(ShellException.class,() -> ioRedirHandler.extractRedirOptions());
    }

    @Test
    void extractRedirOptions_oneInputOneOutputRedir_throwShellException() {
        argumentList.add(inputRedirArrow);
        argumentList.add(outputRedirArrow);
        ioRedirHandler = new IORedirectionHandler(argumentList,inputStream,outputStream,argumentResolver);
        assertThrows(ShellException.class,() -> ioRedirHandler.extractRedirOptions());
        Collections.reverse(argumentList);
        ioRedirHandler = new IORedirectionHandler(argumentList,inputStream,outputStream,argumentResolver);
        assertThrows(ShellException.class,() -> ioRedirHandler.extractRedirOptions());
    }

    @Test
    void extractRedirOptions_inputfileDoesNotExists_throwException() {
        argumentList.add(inputRedirArrow);
        argumentList.add("fake_file.txt");
        ioRedirHandler = new IORedirectionHandler(argumentList,inputStream,outputStream,argumentResolver);
        assertThrows(FileNotFoundException.class, () -> ioRedirHandler.extractRedirOptions());
    }

    @Test
    void extractRedirOptions_outputfileDoesNotExists_throwException() {
        argumentList.add(outputRedirArrow);
        argumentList.add("fake_file_does_not_exists.txt");
        ioRedirHandler = new IORedirectionHandler(argumentList,inputStream,outputStream,argumentResolver);
        assertDoesNotThrow(() -> ioRedirHandler.extractRedirOptions());
    }

    @Test
    void extractRedirOptions_validOutputRedir_ReturnNothing() throws IOException, ShellException, AbstractApplicationException {
        argumentList.add(outputRedirArrow);
        argumentList.add(FILE1_PATH_STRING);
        ioRedirHandler = new IORedirectionHandler(argumentList,inputStream,outputStream,argumentResolver);
        assertDoesNotThrow(() ->  ioRedirHandler.extractRedirOptions());
    }

    @Test
    void extractRedirOptions_validInputRedir_ReturnNothing() throws IOException, ShellException, AbstractApplicationException {
        argumentList.add(inputRedirArrow);
        argumentList.add(FILE1_PATH_STRING);
        ioRedirHandler = new IORedirectionHandler(argumentList,inputStream,outputStream,argumentResolver);
        assertDoesNotThrow(() ->  ioRedirHandler.extractRedirOptions());
    }

    @Test
    void extractRedirOptions_validMultipleInputRedir_ReturnNothing() throws IOException, ShellException, AbstractApplicationException {
        argumentList.add(inputRedirArrow);
        argumentList.add(FILE1_PATH_STRING);
        argumentList.add(inputRedirArrow);
        argumentList.add(FILE2_PATH_STRING);
        ioRedirHandler = new IORedirectionHandler(argumentList,inputStream,outputStream,argumentResolver);
        assertDoesNotThrow(() ->  ioRedirHandler.extractRedirOptions());
    }

    @Test
    void extractRedirOptions_validMultipleOutputRedir_ReturnNothing() throws IOException, ShellException, AbstractApplicationException {
        argumentList.add(outputRedirArrow);
        argumentList.add(FILE1_PATH_STRING);
        argumentList.add(outputRedirArrow);
        argumentList.add(FILE2_PATH_STRING);
        ioRedirHandler = new IORedirectionHandler(argumentList,inputStream,outputStream,argumentResolver);
        assertDoesNotThrow(() ->  ioRedirHandler.extractRedirOptions());
    }
}