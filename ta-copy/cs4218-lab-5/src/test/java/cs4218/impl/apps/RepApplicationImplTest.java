package cs4218.impl.apps;

import cs4218.EnvironmentUtil;
import cs4218.FileUtil;
import cs4218.enums.REPLACE_MODE;
import cs4218.exceptions.RepException;
import cs4218.stubs.RepArgsParserStub;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RepApplicationImplTest {
    RepApplicationImpl app;
    RepArgsParserStub argsStub;
    OutputStream out;
    FileUtil fileUtil;

    @BeforeEach
    void setUp() {
        this.out = new ByteArrayOutputStream();
        this.argsStub = new RepArgsParserStub(); // Can write our own stubs
        this.fileUtil = mock(FileUtil.class); // Or create mocks using mockito
    }

    private File toFile(String path) {
        return Paths.get(EnvironmentUtil.currentDirectory).resolve(path).toFile();
    }

    @Test
    void run_ReplaceWords_PrintsCorrectValues() throws RepException, IOException {
        List<String> tokens = Arrays.asList("Hello", "abced", "hello-world.txt");
        argsStub.setValues(tokens, REPLACE_MODE.REPLACE_WORDS);
        when(this.fileUtil.readFileToString(toFile("hello-world.txt"))).thenReturn("Hello World!");
        this.app = new RepApplicationImpl(argsStub, null, this.out, fileUtil);

        this.app.run(tokens);

        assertEquals("abced World!", this.out.toString());
    }

    @Test
    void run_ReplaceWordsDashAsFile_ReadFromStdin() throws RepException, IOException {
        List<String> tokens = Arrays.asList("Hello", "abced", "-");
        argsStub.setValues(tokens, REPLACE_MODE.REPLACE_WORDS);
        ByteArrayInputStream in = new ByteArrayInputStream("Hello!".getBytes());
        this.app = new RepApplicationImpl(argsStub, in, this.out, fileUtil);

        this.app.run(tokens);

        assertEquals("abced!", this.out.toString());
    }

    @Test
    void run_ReplaceWordsEmptyReplacement_ReplaceWithEmptyString() throws RepException, IOException {
        List<String> tokens = Arrays.asList("Hello", "", "hello-world.txt");
        argsStub.setValues(tokens, REPLACE_MODE.REPLACE_WORDS);
        when(this.fileUtil.readFileToString(toFile("hello-world.txt"))).thenReturn("Hello World!");
        this.app = new RepApplicationImpl(argsStub, null, this.out, fileUtil);

        this.app.run(tokens);

        assertEquals(" World!", this.out.toString());
    }

    @Test
    void run_ReplaceWordsEmptyPattern_ReplaceNothing() throws RepException, IOException {
        List<String> tokens = Arrays.asList("", "test", "hello-world.txt");
        argsStub.setValues(tokens, REPLACE_MODE.REPLACE_WORDS);
        when(this.fileUtil.readFileToString(toFile("hello-world.txt"))).thenReturn("Hello World!");
        this.app = new RepApplicationImpl(argsStub, null, this.out, fileUtil);

        this.app.run(tokens);

        assertEquals("Hello World!", this.out.toString());
    }

    @Test
    void run_ReplaceWordsEmptyFile_PrintEmptyString() throws RepException, IOException {
        List<String> tokens = Arrays.asList("", "test", "hello-world.txt");
        argsStub.setValues(tokens, REPLACE_MODE.REPLACE_WORDS);
        when(this.fileUtil.readFileToString(toFile("hello-world.txt"))).thenReturn("");
        this.app = new RepApplicationImpl(argsStub, null, this.out, fileUtil);

        this.app.run(tokens);

        assertEquals("", this.out.toString());
    }

    @Test
    void run_ReplaceChars_PrintsCorrectValues() throws RepException, IOException {
        List<String> tokens = Arrays.asList("He", "a", "hello-world.txt");
        argsStub.setValues(tokens, REPLACE_MODE.REPLACE_CHARACTERS);
        when(this.fileUtil.readFileToString(toFile("hello-world.txt"))).thenReturn("Hello World!");
        this.app = new RepApplicationImpl(argsStub, null, this.out, fileUtil);

        this.app.run(tokens);

        assertEquals("aallo World!", this.out.toString());
    }

    @Test
    void run_ReplaceCharsDashAsFile_ReadFromStdin() throws RepException, IOException {
        List<String> tokens = Arrays.asList("He", "a", "-");
        argsStub.setValues(tokens, REPLACE_MODE.REPLACE_CHARACTERS);
        ByteArrayInputStream in = new ByteArrayInputStream("Hello!".getBytes());
        this.app = new RepApplicationImpl(argsStub, in, this.out, fileUtil);

        this.app.run(tokens);

        assertEquals("aallo!", this.out.toString());
    }

    @Test
    void run_ReplaceCharsEmptyReplacement_ReplaceWithEmptyString() throws RepException, IOException {
        List<String> tokens = Arrays.asList("He", "", "hello-world.txt");
        argsStub.setValues(tokens, REPLACE_MODE.REPLACE_CHARACTERS);
        when(this.fileUtil.readFileToString(toFile("hello-world.txt"))).thenReturn("Hello World!");
        this.app = new RepApplicationImpl(argsStub, null, this.out, fileUtil);

        this.app.run(tokens);

        assertEquals("llo World!", this.out.toString());
    }

    @Test
    void run_ReplaceCharsEmptyPattern_ReplaceNothing() throws RepException, IOException {
        List<String> tokens = Arrays.asList("", "Hello", "hello-world.txt");
        argsStub.setValues(tokens, REPLACE_MODE.REPLACE_CHARACTERS);
        when(this.fileUtil.readFileToString(toFile("hello-world.txt"))).thenReturn("Hello World!");
        this.app = new RepApplicationImpl(argsStub, null, this.out, fileUtil);

        this.app.run(tokens);

        assertEquals("Hello World!", this.out.toString());
    }

    @Test
    void run_ReplaceCharsEmptyFile_PrintEmptyString() throws RepException, IOException {
        List<String> tokens = Arrays.asList("Hello", "Hello", "hello-world.txt");
        argsStub.setValues(tokens, REPLACE_MODE.REPLACE_CHARACTERS);
        when(this.fileUtil.readFileToString(toFile("hello-world.txt"))).thenReturn("");
        this.app = new RepApplicationImpl(argsStub, null, this.out, fileUtil);

        this.app.run(tokens);

        assertEquals("", this.out.toString());
    }

    @Test
    void run_ArgsValidationFailed_ThrowsRepException() {
        this.argsStub.setException(new RepException("Exception Message"));
        this.app = new RepApplicationImpl(argsStub, null, this.out, fileUtil);

        RepException ex = assertThrows(RepException.class, () -> this.app.run(Collections.emptyList()));
        assertEquals("Exception Message", ex.getMessage());
    }

    @Test
    void run_OutputStreamClosed_ThrowsRepException() throws IOException {
        List<String> tokens = Arrays.asList("He", "a", "hello-world.txt");
        argsStub.setValues(tokens, REPLACE_MODE.REPLACE_CHARACTERS);
        when(this.fileUtil.readFileToString(toFile("hello-world.txt"))).thenReturn("Hello World!");
        this.app = new RepApplicationImpl(argsStub, null, this.out, fileUtil);

        try (MockedStatic<IOUtils> ioUtils = mockStatic(IOUtils.class)) {
            ioUtils.when(() -> IOUtils.write(anyString(), (OutputStream) any())).thenThrow(IOException.class);

            RepException ex = assertThrows(RepException.class, () -> app.run(tokens));
            assertEquals("Could not write to output stream", ex.getMessage());
        }
    }
}