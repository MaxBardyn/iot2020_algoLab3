package lab3;

public class Main {
    public static void main(String[] args) {

        int[][] array = {{1, 2}, {1, 3}};
        int[][] array1 = {{1, 2}, {2, 1}};

        System.out.print(Algorithm.possibleBipartition(3, array) + "\n");
        System.out.print(Algorithm.possibleBipartition(3, array1) + "\n");
    }
}
