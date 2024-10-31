package oracle.simulator.utils;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class FileUtilTest {

    @Test
    public void testParseFileToCharArray() throws IOException {
        String filePath = "src/test/resources/sitemap1.txt";
        char[][] expected = {
                {'o', 'o', 't', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'o', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'r', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'r', 'r', 'r', 'r', 'r', 't', 'o', 'o', 'o', 'o'}
        };

        char[][] result = FileUtil.parseFileToCharArray(filePath);

        assertArrayEquals(expected, result);
    }
}