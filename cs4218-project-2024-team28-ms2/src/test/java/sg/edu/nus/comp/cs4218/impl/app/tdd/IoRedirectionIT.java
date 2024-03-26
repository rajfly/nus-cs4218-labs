package sg.edu.nus.comp.cs4218.impl.app.tdd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

/*
    This test suite will test IORedirection with other applications
    using Integration testing
 */
public class IoRedirectionIT { //NOPMD

    Shell shell;
    OutputStream outputStream;
    File file = new File(FILE_TEST_PATH_1);
    static final String FILE_TEST_PATH_1 = "IOUtilsTest.txt";
    static final String FILE_TEST_PATH_2 = "uniq.txt";
    static final String FILE_TEST_PATH_3 = "ggwp.txt";
    BufferedReader reader;
    StringBuilder fileContent; //NOPMD


    @BeforeEach
    void setUp() throws IOException {
        shell = new ShellImpl();
        outputStream = new ByteArrayOutputStream();
        FileWriter fw = new FileWriter(file); //NOPMD
        fw.write("");
        fw.close();
        fileContent = new StringBuilder();
    }

    @AfterEach
    void tearDown() throws IOException {
        File file = new File("ggwp.txt");
        if (file.exists()) {
            file.delete(); // Delete the file if it exists
        }
        if (this.reader != null) {
            reader.close();
        }
    }

    @Test
    void run_IoRedirectionOutput_ReturnSameResult() throws IOException, AbstractApplicationException, ShellException {
        String commandString = "echo 'Hello world' > " + FILE_TEST_PATH_1;
        String expectedResult = "Hello world" + STRING_NEWLINE;
        String line;
        shell.parseAndEvaluate(commandString,outputStream);
        file = new File(FILE_TEST_PATH_1);
        reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            fileContent.append(line).append(System.lineSeparator());
        }
        assertEquals(expectedResult,fileContent.toString());
    }

    @Test
    void run_IoRedirectionInput_ReturnSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "cat < pom.xml ";
        String commandString2 = "cat pom.xml";
        String firstResult;
        String expectedResult;
        shell.parseAndEvaluate(commandString,outputStream);
        firstResult = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,firstResult);
    }

    @Test
    void run_IoRediectionInputWithMissingFile_ThrowException() {
        String commandString = "cat < ggwp";
        assertThrows(Exception.class, () -> shell.parseAndEvaluate(commandString,outputStream));
    }

    @Test
    void run_IoRedirectionOutputWithMissingFile_CreatesFile() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "echo 'hello world' > ggwp.txt";
        shell.parseAndEvaluate(commandString,outputStream);
        File file = new File("ggwp.txt");
        assertTrue(file.exists()); // Check if the file doesn't exist initially
    }

    @Test
    void run_MultipleIoRedirectionInput_ReturnSameResult() throws IOException, AbstractApplicationException, ShellException {
        String commandString = "cat < pom.xml < uniq.txt";
        String commandString2 = "cat uniq.txt";
        String expectedResult;
        shell.parseAndEvaluate(commandString,outputStream);
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        file = new File(FILE_TEST_PATH_2);
        reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line).append(System.lineSeparator());
        }
        assertEquals(expectedResult,fileContent.toString());
    }

    @Test
    void run_MultipleIoRedirectionOutput_ReturnSameResult() throws IOException, AbstractApplicationException, ShellException {
        String commandString = "echo 'hello world' > grep 'hello' > ggwp.txt";
        String expectedResult = "hello world hello" + STRING_NEWLINE;
        shell.parseAndEvaluate(commandString,outputStream);
        file = new File(FILE_TEST_PATH_3);
        reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line).append(System.lineSeparator());
        }
        assertEquals(expectedResult,fileContent.toString());
    }

    @Test
    void run_InputAndOutputIoRedirection_ReturnSameResult() throws IOException, AbstractApplicationException, ShellException {
        String commandString = "grep 'Alice' < uniq.txt > ggwp.txt";
        String expectedResult = "Alice\nAlice\nAlice\n";
        String outputResult;
        shell.parseAndEvaluate(commandString,outputStream);
        file = new File(FILE_TEST_PATH_3);
        reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line).append(System.lineSeparator());
        }
        expectedResult = expectedResult.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        outputResult = fileContent.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        assertEquals(expectedResult,outputResult);
    }

}
