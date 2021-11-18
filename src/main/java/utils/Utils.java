package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    public static String[][] parseTsv(Path path) throws IOException {
        return Files
                .lines(path)
                .map(line -> line.split("\\s+"))
                .toArray(String[][]::new);
    }
}
