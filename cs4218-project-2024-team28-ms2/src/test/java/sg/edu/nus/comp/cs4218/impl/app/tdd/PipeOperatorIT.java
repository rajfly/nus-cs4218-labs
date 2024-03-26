package sg.edu.nus.comp.cs4218.impl.app.tdd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

import java.io.*;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    This test suite will test all applications with the pipe operator using pairwise testing.
    With pairwise testing, the number of test cases reduced
    from 15^15 test cases
    to 10C2 = 44 test cases as "cd", "exit", "mkdir", "mv", "rm" removed
    and "tee" removed as the first application tested
 */
public class PipeOperatorIT { //NOPMD

    Shell shell;
    OutputStream outputStream;
    static final String FILE_TEST_PATH_1 = "teeFileTest.txt";
    static final String FILE_TEST_PATH_2 = "pipeTest.txt";
    File file = new File(FILE_TEST_PATH_1);
    File file2 = new File(FILE_TEST_PATH_2);
    BufferedReader reader;
    StringBuilder fileContent; //NOPMD
    @BeforeEach
    void setUp() throws IOException {
        shell = new ShellImpl();
        outputStream = new ByteArrayOutputStream();
        FileWriter fw = new FileWriter(file); //NOPMD
        fw.write("");
        fw.close();
        FileWriter fw2 = new FileWriter(file2); //NOPMD
        fw2.write("");
        fw2.close();
        fileContent = new StringBuilder();
    }

    @AfterEach
    void tearDown() throws IOException {
        if (this.reader != null) {
            reader.close();
        }
    }

    // TC1: echo | wc
    @Test
    void run_EchoPipeWc_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "echo 'Hello world' | wc";
        String expectedResult = "\t1\t2\t13 -" + STRING_NEWLINE;
        shell.parseAndEvaluate(commandString,outputStream);
        assertEquals(expectedResult,outputStream.toString());
    }

    // TC2: echo | sort
    @Test
    void run_EchoPipeSort_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "echo 'Hello world' | sort";
        String expectedResult = "Hello world" + STRING_NEWLINE;
        shell.parseAndEvaluate(commandString,outputStream);
        assertEquals(expectedResult,outputStream.toString());
    }

    // TC3: echo | cat
    @Test
    void run_EchoPipeCat_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "echo 'Hello world' | cat";
        String expectedResult = "Hello world" + STRING_NEWLINE;
        shell.parseAndEvaluate(commandString,outputStream);
        assertEquals(expectedResult,outputStream.toString());
    }

    // TC4: echo | ls
    @Test
    void run_EchoPipeLs_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "echo 'Hello world' | ls";
        String commandString2 = "ls";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC5: echo | paste
    @Test
    void run_EchoPipePaste_ReturnsSameResult() throws IOException, AbstractApplicationException, ShellException {
        String commandString = "echo 'Hello world' | paste > pipeTest.txt"; //NOPMD
        String commandString2 = "cat pipeTest.txt";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        reader = new BufferedReader(new FileReader(file2));
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line).append(System.lineSeparator());
        }
        testCommandOutput = fileContent.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC6: echo | uniq
    @Test
    void run_EchoPipeUniq_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "echo 'Hello world' | uniq";
        String commandString2 = "echo Hello world";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC7: echo | cut
    @Test
    void run_EchoPipeCut_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "echo 'baz' | cut -b 3";
        String expectedResult = "z" + STRING_NEWLINE;
        shell.parseAndEvaluate(commandString,outputStream);
        assertEquals(expectedResult,outputStream.toString());
    }

    // TC8: echo | tee
    @Test
    void run_EchoPipeTee_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "echo 'hehe' | tee teeFileTest.txt";
        String expectedResult = "hehe" + STRING_NEWLINE;
        shell.parseAndEvaluate(commandString,outputStream);
        assertEquals(expectedResult,outputStream.toString());
    }

    // TC9: echo | grep
    @Test
    void run_EchoPipeGrep_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "echo 'java is boring' | grep 'java' ";
        String expectedResult = "java is boring" + STRING_NEWLINE;
        shell.parseAndEvaluate(commandString,outputStream);
        assertEquals(expectedResult,outputStream.toString());
    }

    // TC10: wc | sort
    @Test
    void run_WcPipeSort_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "wc pom.xml | sort";
        String commandString2 = "wc pom.xml"; // NOPMD
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC11: wc | cat
    @Test
    void run_WcPipeCat_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "wc pom.xml | cat";
        String commandString2 = "wc pom.xml";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC12: wc | ls
    @Test
    void run_WcPipeLs_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "wc pom.xml | ls";
        String commandString2 = "ls";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC13: wc | paste
    @Test
    void run_WcPipePaste_ReturnsSameResult() throws IOException, AbstractApplicationException, ShellException {
        String commandString = "wc pom.xml | paste > pipeTest.txt";
        String commandString2 = "wc pom.xml";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        reader = new BufferedReader(new FileReader(file2));
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line).append(System.lineSeparator());
        }
        testCommandOutput = fileContent.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC14: wc | uniq
    @Test
    void run_WcPipeUniq_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "wc pom.xml | uniq";
        String commandString2 = "wc pom.xml";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC15: wc | cut
    @Test
    void run_WcPipeCut_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "wc uniq.txt | cut -c 1-2";
        String expectedResult = "6" + STRING_NEWLINE;
        shell.parseAndEvaluate(commandString,outputStream);
        assertEquals(expectedResult,outputStream.toString());
    }

    // TC16: wc | tee
    @Test
    void run_WcPipeTee_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "wc uniq.txt | tee teeFileTest.txt";
        String commandString2 = "wc uniq.txt";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC17: wc | grep
    @Test
    void run_WcPipeGrep_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "wc uniq.txt | grep 'uniq.txt'";
        String commandString2 = "wc uniq.txt";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC18: sort | cat
    @Test
    void run_SortPipeCat_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "sort pom.xml | cat";
        String commandString2 = "sort pom.xml";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC19: sort | ls
    @Test
    void run_SortPipeLs_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "sort pom.xml | ls";
        String commandString2 = "ls";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC20: sort | paste
    @Test
    void run_SortPipePaste_ReturnsSameResult() throws IOException, AbstractApplicationException, ShellException {
        String commandString = "sort pom.xml | paste > pipeTest.txt";
        String commandString2 = "sort pom.xml";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        reader = new BufferedReader(new FileReader(file2));
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line).append(System.lineSeparator());
        }
        testCommandOutput = fileContent.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC21: sort | uniq
    @Test
    void run_SortPipeUniq_ReturnsSameResult() throws IOException, AbstractApplicationException, ShellException {
        String commandString = "sort uniq.txt | uniq > pipeTest.txt";
        String expectedResult = "Alice\nBob\nHello World\n";
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        reader = new BufferedReader(new FileReader(file2));
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line).append(System.lineSeparator());
        }
        testCommandOutput = fileContent.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n"); //NOPMD
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC22: sort | cut
    @Test
    void run_SortPipeCut_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "sort uniq.txt | cut -c 1-3";
        String expectedResult = "Ali\nAli\nAli\nBob\nBob\nHel\nHel\n";
        expectedResult = expectedResult.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        shell.parseAndEvaluate(commandString,outputStream);
        String output = outputStream.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        assertEquals(expectedResult,output);
    }

    @Test
    // TC23: sort | tee
    void run_SortPipeTee_ReturnsSameResult() throws IOException, AbstractApplicationException, ShellException {
        String commandString = "sort uniq.txt | tee > pipeTest.txt";
        String expectedResult = "Alice\nAlice\nAlice\nBob\nBob\nHello World\nHello World\n";
        shell.parseAndEvaluate(commandString,outputStream);
        reader = new BufferedReader(new FileReader(file2));
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line).append(System.lineSeparator());
        }
        String testCommandOutput = fileContent.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC24: sort | grep
    @Test
    void run_SortPipeGrep_ReturnsSameResult() throws IOException, AbstractApplicationException, ShellException {
        String commandString = "sort uniq.txt | grep 'Alice' > pipeTest.txt";
        String expectedResult = "Alice\nAlice\nAlice\n";
        shell.parseAndEvaluate(commandString,outputStream);
        reader = new BufferedReader(new FileReader(file2));
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line).append(System.lineSeparator());
        }
        String testCommandOutput = fileContent.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC25: cat | ls
    @Test
    void run_CatPipeLs_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "cat pom.xml | ls";
        String commandString2 = "ls";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC26: cat | paste
    @Test
    void run_CatPipePaste_ReturnsSameResult() throws IOException, AbstractApplicationException, ShellException {
        String commandString = "cat uniq.txt | paste > pipeTest.txt";
        String commandString2 = "cat uniq.txt";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        reader = new BufferedReader(new FileReader(file2));
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line).append(System.lineSeparator());
        }
        testCommandOutput = fileContent.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC27: cat | uniq
    @Test
    void run_CatPipeUniq_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "cat pom.xml | uniq";
        String commandString2 = "cat pom.xml";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC28: cat | cut
    @Test
    void run_CatPipeCut_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "cat uniq.txt | cut -c 1-3";
        String commandString2 = "cut -c 1-3 uniq.txt";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC29: cat | tee
    @Test
    void run_CatPipeTee_returnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "cat uniq1.txt | tee";
        String commandString2 = "cat uniq1.txt";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC30: cat | grep
    @Test
    void run_CatPipeGrep_returnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "cat uniq.txt | grep 'Alice'";
        String expectedResult = "Alice\nAlice\nAlice\n";
        shell.parseAndEvaluate(commandString,outputStream);
        String currentResult = outputStream.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        assertEquals(expectedResult,currentResult);
    }

    // TC31: ls | paste
    @Test
    void run_LsPipePaste_returnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "ls pom.xml | paste > testFile.txt";
        String expectedResult = "";
        shell.parseAndEvaluate(commandString,outputStream);
        assertEquals(expectedResult,outputStream.toString());
    }

    // TC32: ls | uniq
    @Test
    void run_LsPipeUniq_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "ls | uniq";
        String commandString2 = "ls";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC33: ls | cut
    @Test
    void run_LsPipeCut_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "ls src | cut -c 1";
        String expectedResult = "s\nm\nt" + STRING_NEWLINE;
        expectedResult = expectedResult.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        shell.parseAndEvaluate(commandString,outputStream);
        String output = outputStream.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        assertEquals(expectedResult,output);
    }

    // TC34: ls | tee
    @Test
    void run_LsPipeTee_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "ls | tee teeFileTest.txt";
        String commandString2 = "ls";
        String expectedResult;
        String testCommandOutput;
        shell.parseAndEvaluate(commandString,outputStream);
        testCommandOutput = outputStream.toString();
        outputStream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(commandString2,outputStream);
        expectedResult = outputStream.toString();
        assertEquals(expectedResult,testCommandOutput);
    }

    // TC35: ls | grep
    @Test
    void run_LsPipeGrep_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "ls | grep 'src'";
        String expectedResult = "src" + STRING_NEWLINE;
        shell.parseAndEvaluate(commandString,outputStream);
        assertEquals(expectedResult,outputStream.toString());
    }

    // TC 36: uniq | cut
    @Test
    void run_UniqPipeCut_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "uniq uniq.txt | cut -c 1";
        String expectedResult = "H\nA\nB\nA\nB\n";
        expectedResult = expectedResult.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        shell.parseAndEvaluate(commandString,outputStream);
        String output = outputStream.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");;
        assertEquals(expectedResult,output);
    }

    // TC 37: uniq | tee
    @Test
    void run_UniqPipeTee_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "uniq uniq.txt | tee";
        String expectedResult = "Hello World\nAlice\nBob\nAlice\nBob\n";
        expectedResult = expectedResult.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        shell.parseAndEvaluate(commandString,outputStream);
        String output = outputStream.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");;
        assertEquals(expectedResult,output);
    }

    // TC42: uniq | grep
    @Test
    void run_UniqPipeGrep_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "uniq uniq.txt | grep 'Alice'";
        String expectedResult = "Alice\nAlice" + STRING_NEWLINE;
        expectedResult = expectedResult.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        shell.parseAndEvaluate(commandString,outputStream);
        String output = outputStream.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");;
        assertEquals(expectedResult,output);
    }

    // TC43: cut | tee
    @Test
    void run_CutPipeTee_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
            String commandString = "cut -c 1 uniq.txt | tee";
        String expectedResult = "H\nH\nA\nA\nB\nA\nB" + STRING_NEWLINE;
        expectedResult = expectedResult.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        shell.parseAndEvaluate(commandString,outputStream);
        String output = outputStream.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");;
        assertEquals(expectedResult,output);
    }

    // TC44: cut | grep
    @Test
    void run_CutPipeGrep_ReturnsSameResult() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "cut -c 1 uniq.txt | grep 'A'";
        String expectedResult = "A\nA\nA" + STRING_NEWLINE;
        expectedResult = expectedResult.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        shell.parseAndEvaluate(commandString,outputStream);
        String output = outputStream.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");;
        assertEquals(expectedResult,output);
    }
}
