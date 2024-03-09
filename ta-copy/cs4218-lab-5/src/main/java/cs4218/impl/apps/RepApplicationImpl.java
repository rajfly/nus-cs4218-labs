package cs4218.impl.apps;

import cs4218.EnvironmentUtil;
import cs4218.FileUtil;
import cs4218.exceptions.RepException;
import cs4218.interfaces.apps.RepApplication;
import cs4218.interfaces.parsers.RepArgsParser;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RepApplicationImpl implements RepApplication {
    private final RepArgsParser args;
    private final InputStream in;
    private final OutputStream out;
    private final FileUtil fileUtil;

    public RepApplicationImpl(RepArgsParser args, InputStream in, OutputStream out, FileUtil fileUtil) {
        this.args = args;
        this.in = in;
        this.out = out;
        this.fileUtil = fileUtil;
    }

    @Override
    public void run(List<String> tokens) throws RepException {
        this.args.parseAndValidate(tokens);

        String filePath = this.args.getFilePath();
        String input = this.getInput(filePath, this.in);
        String pattern = this.args.getPattern();
        String replacement = this.args.getReplacement();

        String result;

        if ("".equals(pattern)) {
            result = input;
        } else {
            switch (this.args.getMode()) {
                case REPLACE_WORDS:
                    result = this.replaceWords(pattern, replacement, input);
                    break;
                case REPLACE_CHARACTERS:
                    result = this.replaceCharacters(pattern, replacement, input);
                    break;
                default:
                    throw new RepException("Invalid Mode");
            }
        }

        try {
            IOUtils.write(result, this.out);
        } catch (IOException e) {
            throw new RepException("Could not write to output stream", e);
        }
    }

    private String getInput(String filePath, InputStream in) throws RepException {
        try {
            if ("-".equals(filePath)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                List<String> lines = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
                br.close();
                return String.join(System.lineSeparator(), lines);
            } else {
                Path currentDirectory = Paths.get(EnvironmentUtil.currentDirectory);
                File file = currentDirectory.resolve(filePath).toFile();
                return this.fileUtil.readFileToString(file);
            }
        } catch (IOException e) {
            throw new RepException("Invalid file path provided", e);
        }
    }

    private String replaceWords(String pattern, String replacement, String fileContents) {
        return fileContents.replaceAll(pattern, replacement);
    }

    private String replaceCharacters(String pattern, String replacement, String fileContents) {
        String charSetRegex = String.format("[%s]", pattern);
        return fileContents.replaceAll(charSetRegex, replacement);
    }

}
