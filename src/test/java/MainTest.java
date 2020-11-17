import lab3.Algorithm;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void test1() {
        Set[] values = loadValues();
        int result = Algorithm.algorithm(values);
        assertEquals(4, result);
    }

    private static Set[] loadValues() {
        String testDestination = "src/test/resources/" + "test_1";
        Set[] values = null;
        try {

            List<String> strings = Files.readAllLines(Path.of(testDestination));
            int n = Integer.parseInt(strings.get(0));
            values = new Set[n];
            for (int i = 1; i <= n; i++) {
                Set<Integer> integerSet = Arrays.stream(strings.get(i).split(" ")).map(Integer::parseInt)
                        .collect(Collectors.toSet());
                values[i - 1] = integerSet;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }
}
