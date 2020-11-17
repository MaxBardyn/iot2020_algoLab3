package lab3;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set[] pairs = Data.loadTribes();
        System.out.println(Algorithm.algorithm(pairs));
    }
}
