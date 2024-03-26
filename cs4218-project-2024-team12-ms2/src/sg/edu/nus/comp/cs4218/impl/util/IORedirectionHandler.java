package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_MULTIPLE_STREAMS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_REDIR_INPUT;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_REDIR_OUTPUT;

public class IORedirectionHandler {
    private final List<String> argsList;
    private final ArgumentResolver argumentResolver;
    private final InputStream origInputStream;
    private final OutputStream origOutputStream;
    private List<String> noRedirArgsList;
    private InputStream inputStream;
    private OutputStream outputStream;

    public IORedirectionHandler(List<String> argsList, InputStream origInputStream,
                                OutputStream origOutputStream, ArgumentResolver argumentResolver) {
        this.argsList = argsList;
        this.inputStream = origInputStream;
        this.origInputStream = origInputStream;
        this.outputStream = origOutputStream;
        this.origOutputStream = origOutputStream;
        this.argumentResolver = argumentResolver;
    }

    /**
     * Extracts the redirection options from the list of arguments and updates the input and output streams accordingly.
     *
     * @throws AbstractApplicationException If an exception happens while opening the file streams
     * @throws ShellException               If there is a syntax error in the redirection options
     * @throws FileNotFoundException         If the file specified in the redirection options does not exist
     */
    public void extractRedirOptions() throws AbstractApplicationException, ShellException, FileNotFoundException {
        if (argsList == null || argsList.isEmpty()) {
            throw new ShellException(ERR_SYNTAX);
        }

        noRedirArgsList = new LinkedList<>();

        // extract redirection operators (with their corresponding files) from argsList
        ListIterator<String> argsIterator = argsList.listIterator();
        while (argsIterator.hasNext()) {
            String arg = argsIterator.next();

            // leave the other args untouched
            if (!isRedirOperator(arg)) {
                noRedirArgsList.add(arg);
                continue;
            }

            // if current arg is < or >, fast-forward to the next arg to extract the specified file
            String file = argsIterator.next();

            if (isRedirOperator(file)) {
                throw new ShellException(ERR_SYNTAX);
            }

            // handle quoting + globing + command substitution in file arg
            List<String> fileSegment = argumentResolver.resolveOneArgument(file);
            if (fileSegment.size() > 1) {
                // ambiguous redirect if file resolves to more than one parsed arg
                throw new ShellException(ERR_SYNTAX);
            }
            file = fileSegment.get(0);

            // replace existing inputStream / outputStream
            if (arg.equals(String.valueOf(CHAR_REDIR_INPUT))) {
                IOUtils.closeInputStream(inputStream);
                if (!inputStream.equals(origInputStream)) { // Already have a stream
                    throw new ShellException(ERR_MULTIPLE_STREAMS);
                }
                inputStream = IOUtils.openInputStream(file);
            } else if (arg.equals(String.valueOf(CHAR_REDIR_OUTPUT))) {
                IOUtils.closeOutputStream(outputStream);
                if (!outputStream.equals(origOutputStream)) { // Already have a stream
                    throw new ShellException(ERR_MULTIPLE_STREAMS);
                }
                outputStream = IOUtils.openOutputStream(file);
            }
        }
    }

    /**
     * Returns the list of arguments without the redirection operators.
     *
     * @return List of arguments without redirection operators
     */
    public List<String> getNoRedirArgsList() {
        return noRedirArgsList;
    }

    /**
     * Returns the input stream after redirection.
     *
     * @return InputStream after redirection
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Returns the output stream after redirection.
     *
     * @return OutputStream after redirection
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Returns true if the given string is a redirection operator.
     *
     * @param str String to check
     * @return True if the given string is a redirection operator
     */
    private boolean isRedirOperator(String str) {
        return str.equals(String.valueOf(CHAR_REDIR_INPUT)) || str.equals(String.valueOf(CHAR_REDIR_OUTPUT));
    }
}
