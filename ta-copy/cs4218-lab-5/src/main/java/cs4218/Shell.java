package cs4218;

import cs4218.exceptions.ApplicationException;
import cs4218.exceptions.ShellException;
import cs4218.interfaces.Command;

import java.util.Scanner;

public class Shell {
    private final ApplicationRunner appRunner;
    private final CommandBuilder commandBuilder;

    public Shell(ApplicationRunner appRunner, CommandBuilder commandBuilder) {
        System.setErr(System.out);
        this.appRunner = appRunner;
        this.commandBuilder = commandBuilder;
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");

                if (!scanner.hasNextLine()) {
                    break;
                }

                String line = scanner.nextLine();

                try {
                    Command command = this.commandBuilder.getCommand(line, appRunner);
                    command.evaluate(System.in, System.out);
                } catch (ShellException e) {
                    System.err.print(e.formatException());
                } catch (ApplicationException e) {
                    System.err.print(e.formatException());
                }

                System.out.println();
            }
        }
    }
}
