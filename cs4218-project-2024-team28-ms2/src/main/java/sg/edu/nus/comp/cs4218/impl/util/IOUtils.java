package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;

@SuppressWarnings("PMD.PreserveStackTrace")
public final class IOUtils {
    private IOUtils() {
    }

    /**
     * Open an inputStream based on the file name.
     *
     * @param fileName String containing file name.
     * @return InputStream of file opened.
     * @throws ShellException If file destination is inaccessible.
     */
    public static InputStream openInputStream(String fileName) throws ShellException {
        String resolvedFileName = resolveFilePath(fileName).toString();

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(resolvedFileName));
        } catch (FileNotFoundException e) {
            throw new ShellException(ERR_FILE_NOT_FOUND);
        }

        return fileInputStream;
    }

    /**
     * Open an outputStream based on the file name.
     *
     * @param fileName String containing file name.
     * @return OutputStream of file opened.
     * @throws ShellException If file destination is inaccessible.
     */
    public static OutputStream openOutputStream(String fileName) throws ShellException, FileNotFoundException {
        String resolvedFileName = resolveFilePath(fileName).toString();

        FileOutputStream fileOutputStream;

        File file = new File(resolvedFileName);

        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
        } catch (IOException e) {
            throw (ShellException) new ShellException(ERR_IO_EXCEPTION).initCause(e);
        }

        return fileOutputStream;
    }

    /**
     * Close an inputStream. If inputStream provided is System.in or null, it will be ignored.
     *
     * @param inputStream InputStream to be closed.
     * @throws ShellException If inputStream cannot be closed successfully.
     */
    public static void closeInputStream(InputStream inputStream) throws ShellException {
        if (inputStream.equals(System.in)) {
            return;
        }

        try {
            inputStream.close();
        } catch (IOException e) {
            throw new ShellException(ERR_CLOSING_STREAMS);
        }
    }

    /**
     * Close an outputStream. If outputStream provided is System.out or null, it will be ignored.
     *
     * @param outputStream OutputStream to be closed.
     * @throws ShellException If outputStream cannot be closed successfully.
     */
    public static void closeOutputStream(OutputStream outputStream) throws ShellException {
        if (outputStream.equals(System.out)) {
            return;
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            throw new ShellException(ERR_CLOSING_STREAMS);
        }
    }

    public static Path resolveFilePath(String fileName) {
        Path currentDirectory = Paths.get(Environment.currentDirectory);
        return currentDirectory.resolve(fileName);
    }

    /**
     * Returns a list of lines based on the given InputStream.
     *
     * @param input InputStream containing arguments from System.in or FileInputStream
     * @throws Exception
     */
    public static List<String> getLinesFromInputStream(InputStream input) throws IOException {
        if(input == null) {
            return null;
        }
        List<String> output = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        while ((line = reader.readLine()) != null) {
            output.add(line);
        }
        reader.close();
        return output;
    }

    /**
     * Converts filename to absolute path, if initially was relative path
     *
     * @param fileName supplied by user
     * @return a String of the absolute path of the filename
     */
    public static String convertToAbsolutePath(String fileName) {
        String home = System.getProperty("user.home").trim();
        String currentDir = Environment.currentDirectory.trim();
        String convertedPath = convertPathToSystemPath(fileName);

        String newPath;
        if (convertedPath.length() >= home.length() && convertedPath.substring(0, home.length()).trim().equals(home)) {
            newPath = convertedPath;
        } else {
            newPath = currentDir + CHAR_FILE_SEP + convertedPath;
        }
        return newPath;
    }

    /**
     * Converts path provided by user into path recognised by the system
     *
     * @param path supplied by user
     * @return a String of the converted path
     */
    public static String convertPathToSystemPath(String path) {
        String convertedPath = path;
        String pathIdentifier = "\\" + CHAR_FILE_SEP;
        convertedPath = convertedPath.replaceAll("(\\\\)+", pathIdentifier);
        convertedPath = convertedPath.replaceAll("/+", pathIdentifier);

        if (convertedPath.length() != 0 && convertedPath.charAt(convertedPath.length() - 1) == CHAR_FILE_SEP) {
            convertedPath = convertedPath.substring(0, convertedPath.length() - 1);
        }

        return convertedPath;
    }
}
