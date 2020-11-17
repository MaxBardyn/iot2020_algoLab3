package lab3;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Data {

    public static Set[] loadTribes() {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Set[] values = new Set[n];
        for (int i = 0; i < n; i++) {
            values[i] = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toSet());
        }
        return values;
    }
}
