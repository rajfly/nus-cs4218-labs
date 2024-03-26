package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.exception.WcException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IO_EXCEPTION;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;

public class WcCountExtractor {
    private static final int LINES_INDEX = 0;
    private static final int WORDS_INDEX = 1;
    private static final int BYTES_INDEX = 2;

    /**
     * Returns array containing the number of lines, words, and bytes based on data in InputStream.
     *
     * @param input An InputStream
     * @throws WcException if exception occurs with {@code input}
     */
    public long[] getCountReport(InputStream input) throws WcException {
        if (input == null) {
            throw new WcException(ERR_NULL_STREAMS);
        }
        long[] result = new long[3]; // lines, words, bytes

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int inRead;
        boolean inWord = false;
        try {
            while ((inRead = input.read(data, 0, data.length)) != -1) {
                for (int i = 0; i < inRead; ++i) {
                    if (Character.isWhitespace(data[i])) {
                        // Use <newline> character here. (Ref: UNIX)
                        if (data[i] == '\n') {
                            ++result[LINES_INDEX];
                        }
                        if (inWord) {
                            ++result[WORDS_INDEX];
                        }

                        inWord = false;
                    } else {
                        inWord = true;
                    }
                }
                result[BYTES_INDEX] += inRead;
                buffer.write(data, 0, inRead);
            }
            buffer.flush();
            if (inWord) {
                ++result[WORDS_INDEX]; // To handle last word
            }
        } catch (IOException e) {
            throw (WcException) new WcException(ERR_IO_EXCEPTION).initCause(e);
        }

        return result;
    }
}
