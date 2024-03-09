package cs4218;

import cs4218.impl.commands.CallCommand;
import cs4218.impl.commands.PipeCommand;
import cs4218.interfaces.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandBuilder {
    public Command getCommand(String line, ApplicationRunner appRunner) {
        // This is a simplified implementation of pipe
        if (line.contains(" | ")) {
            String[] arr = line.split(" \\| ");
            List<CallCommand> callCommands = Arrays.stream(arr)
                    .map(s -> Arrays.stream(s.split(" ")).collect(Collectors.toCollection(ArrayList::new)))
                    .map(t -> new CallCommand(t, appRunner))
                    .collect(Collectors.toList());
            return new PipeCommand(callCommands);
        } else {
            List<String> tokens = Arrays.stream(line.split(" ")).collect(Collectors.toCollection(ArrayList::new));
            return new CallCommand(tokens, appRunner);
        }
    }
}
