package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.PasteInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.PasteException;
import sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser;

import java.io.*;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.IOUtils.convertToAbsolutePath;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_TAB;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class PasteApplication implements PasteInterface { //NOPMD - suppressed GodClass - Functions are needed
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        if (stdout == null || stdin == null) {
            throw new PasteException(ERR_NO_OSTREAM);
        }
        String res;
        PasteArgsParser parser = new PasteArgsParser();
        try {
            parser.parse(args);
        } catch (InvalidArgsException e) {
            throw (PasteException) new PasteException(ERR_INVALID_FLAG).initCause(e);
        }
        boolean isSerial = parser.isSerial();
        String[] files = parser.getFiles();
        int countFile = parser.numFile();
        int countStdin = parser.numStdin();

        if ((countStdin > 0 && countFile > 0)) { //File and stdin
            try {
                res = mergeFileAndStdin(isSerial,stdin,files);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (countStdin == 0 && countFile > 0) { //File
            res = mergeFile(isSerial,files);
        } else {
            res = mergeStdin(isSerial,stdin); //Stdin
        }
        try {
            stdout.write(res.getBytes());
        } catch (IOException e) {
            throw new PasteException(ERR_WRITE_STREAM);//NOPMD
        }
    }

    @Override
    public String mergeStdin(Boolean isSerial, InputStream stdin) throws AbstractApplicationException {
        validateInputStream(stdin);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stdin))) {
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                appendLineToOutput(output, line, isSerial);
            }
            removeTrailingCharacter(output, isSerial);
            return output.toString();
        } catch (IOException e) {
            PasteException pasteEx = new PasteException(ERR_IO_EXCEPTION);
            pasteEx.initCause(e); // Preserve the original stack trace
            throw pasteEx;
        }
    }

    private void validateInputStream(InputStream stdin) throws PasteException {
        if (stdin == null) {
            throw new PasteException(ERR_NO_ISTREAM);
        }
    }

    private void appendLineToOutput(StringBuilder output, String line, boolean isSerial) {
        output.append(line);
        if (isSerial) {
            output.append(CHAR_TAB);
        } else {
            output.append(STRING_NEWLINE);
        }
    }

    private void removeTrailingCharacter(StringBuilder output, boolean isSerial) {
        if (output.length() > 0) {
            if (isSerial) {
                output.deleteCharAt(output.length() - 1);
            } else {
                output.delete(output.length() - STRING_NEWLINE.length(), output.length());
            }
        }
    }


    @Override
    public String mergeFile(Boolean isSerial, String... fileName) throws AbstractApplicationException {
        validateFileNameArray(fileName);

        StringBuilder output = new StringBuilder();
        BufferedReader[] readers = openReaders(fileName);
        try {
            if (fileName.length == 1) {
                processSingleFile(readers[0], output, isSerial);
            } else {
                mergeMultipleFiles(fileName.length, readers, output,isSerial);
            }
        } catch (IOException e) {
            throw new PasteException(ERR_IO_EXCEPTION); //NOPMD
        }
        return output.toString();
    }


    private void validateFileNameArray(String... fileName) throws PasteException {
        if (fileName == null || fileName.length == 0) {
            throw new PasteException(ERR_NULL_ARGS);
        }
    }

    private BufferedReader[] openReaders(String... fileName) throws PasteException {
        BufferedReader[] readers = new BufferedReader[fileName.length];
        try {
            for (int i = 0; i < fileName.length; i++) {
                String path = validateFileName(fileName[i]);
                readers[i] = new BufferedReader(new FileReader(path));
            }
        } catch (IOException e) {
            throw new PasteException(e.getMessage()); //NOPMD
        }
        return readers;
    }

    private void processSingleFile(BufferedReader reader, StringBuilder output, boolean isSerial) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            appendLineToOutput(output, line, isSerial);
        }
        removeTrailingCharacter(output, isSerial);
        reader.close();
    }

    @Override
    public String mergeFileAndStdin(Boolean isSerial, InputStream stdin, String... fileName) throws Exception {
        validateInputStream(stdin);
        validateFileNameArray(fileName);

        StringBuilder output = new StringBuilder();
        BufferedReader[] readers = new BufferedReader[fileName.length];
        try (BufferedReader stdinReader = new BufferedReader(new InputStreamReader(stdin))) {
            for (int i = 0; i < fileName.length; i++) {
                if ("-".equals(fileName[i])) {
                    readers[i] = stdinReader;
                    continue;
                }
                String path = validateFileName(fileName[i]);
                readers[i] = new BufferedReader(new FileReader(path));
            }
            mergeMultipleFiles(fileName.length, readers, output, isSerial);

            for (BufferedReader reader : readers) reader.close();//NOPMD

        } catch (IOException e) {
            throw new PasteException(ERR_IO_EXCEPTION);//NOPMD
        }
        return output.toString();
    }

    private void mergeMultipleFiles(int fileNumber, BufferedReader[] readers, StringBuilder output, boolean isSerial) throws IOException {
        if (isSerial) {
            mergeFilesSerially(fileNumber, readers, output);
        } else {
            mergeFilesConcurrently(fileNumber, readers, output);
        }
    }

    private void mergeFilesSerially(int fileNumber, BufferedReader[] readers, StringBuilder output) throws IOException {
        String line;
        for (int i = 0; i < fileNumber; i++) {
            StringBuilder builder = new StringBuilder();
            while ((line = readers[i].readLine()) != null) {
                builder.append(line).append('\t');
            }
            appendToOutput(output, builder);
        }
        removeTrailingNewline(output);
    }

    private void mergeFilesConcurrently(int fileNumber, BufferedReader[] readers, StringBuilder output) throws IOException {
        String line;
        int unfinished;
        do {
            unfinished = fileNumber;
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < fileNumber; i++) {
                line = readers[i].readLine();
                if (line == null) {
                    unfinished--;
                } else {
                    builder.append(line);
                }
                if (i < fileNumber - 1) {
                    builder.append(CHAR_TAB);
                }
            }
            if (unfinished > 0) {
                output.append(builder).append(STRING_NEWLINE);
            }
        } while (unfinished > 0);
        removeTrailingNewline(output);
    }

    private void appendToOutput(StringBuilder output, StringBuilder builder) {
        output.append(builder);
        if (!output.toString().isEmpty()) {
            output.deleteCharAt(output.length() - 1);
            output.append(STRING_NEWLINE);
        }
    }

    private void removeTrailingNewline(StringBuilder output) {
        if (output.length() > STRING_NEWLINE.length()) {
            output.delete(output.length() - STRING_NEWLINE.length(), output.length());
        }
    }
    private String validateFileName(String fileName) throws PasteException {
        if (fileName == null) {
            throw new PasteException(ERR_NULL_ARGS);
        }
        String path = convertToAbsolutePath(fileName);
        File file = new File(path);

        if (!file.exists()) {
            throw new PasteException(fileName + " " + ERR_FILE_NOT_FOUND);
        }
        if (file.isDirectory()) {
            throw new PasteException(path + " " + ERR_IS_DIR);
        }
        if (!file.canRead()) {
            throw new PasteException(fileName + " " + ERR_NO_PERM);
        }
        return path;
    }

}
