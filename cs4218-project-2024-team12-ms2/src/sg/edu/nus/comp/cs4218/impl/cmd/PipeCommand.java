package sg.edu.nus.comp.cs4218.impl.cmd;

import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;

/**
 * A Pipe Command is a sub-command consisting of two Call Commands separated with a pipe,
 * or a Pipe Command and a Call Command separated with a pipe.
 * <p>
 * Command format: <Call> | <Call> or <Pipe> | <Call>
 */
public class PipeCommand implements Command {
    private final List<CallCommand> callCommands;

    public PipeCommand(List<CallCommand> callCommands) {
        this.callCommands = callCommands;
    }

    @Override
    public void evaluate(InputStream stdin, OutputStream stdout)
            throws AbstractApplicationException, ShellException, FileNotFoundException {
        if (callCommands == null || callCommands.size() < 2) {
            throw new ShellException(ERR_SYNTAX);
        }

        AbstractApplicationException absAppException = null;
        ShellException shellException = null;

        InputStream nextInputStream = stdin;
        OutputStream nextOutputStream = null; //NOPMD - suppressed CloseResource - stream closed below

        for (int i = 0; i < callCommands.size(); i++) {
            CallCommand callCommand = callCommands.get(i);

            if (absAppException != null || shellException != null) {
                continue;
            }

            try {
                nextOutputStream = new ByteArrayOutputStream();
                if (i == callCommands.size() - 1) {
                    nextOutputStream = stdout;
                }
                callCommand.evaluate(nextInputStream, nextOutputStream);
                if (i != callCommands.size() - 1) {
                    nextInputStream = new ByteArrayInputStream(((ByteArrayOutputStream) nextOutputStream).toByteArray()); //NOPMD - suppressed CloseResource - steram closed below
                }
            } catch (AbstractApplicationException e) {
                absAppException = e;
            } catch (ShellException e) {
                shellException = e;
            } finally {
                IOUtils.closeInputStream(nextInputStream);
                IOUtils.closeOutputStream(nextOutputStream);
            }
        }

        if (absAppException != null) {
            throw absAppException;
        }
        if (shellException != null) {
            throw shellException;
        }
    }

    /**
     * Returns a list of Call Commands for the pipe command
     *
     * @return List of Call Commands
     */
    public List<CallCommand> getCallCommands() {
        return callCommands;
    }
}
