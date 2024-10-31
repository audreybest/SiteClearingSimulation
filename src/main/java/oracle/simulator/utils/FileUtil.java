package oracle.simulator.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    public static char[][] parseFileToCharArray(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        int rows = lines.size();
        int cols = lines.get(0).split(" ").length;
        char[][] charArray = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String[] chars = lines.get(i).split(" ");
            for (int j = 0; j < chars.length; j++) {
                charArray[i][j] = chars[j].charAt(0);
            }
        }

        return charArray;
    }
}
