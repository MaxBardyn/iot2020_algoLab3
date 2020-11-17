package lab3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Algorithm {
    private static ArrayList<Integer>[] graph;
    private static Map<Integer, Integer> map;

    public static boolean possibleBipartition(int N, int[][] dislikes) {
        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; ++i)
            graph[i] = new ArrayList();

        for (int[] edge: dislikes) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        map = new HashMap();
        for (int node = 1; node <= N; ++node)
            if (!map.containsKey(node) && depthFirstSearch(node, 0))
                return false;
        return true;
    }

    public static boolean depthFirstSearch(int node, int c) {
        if (map.containsKey(node))
            return map.get(node) != c;
        map.put(node, c);

        for (int nei: graph[node])
            if (depthFirstSearch(nei, c ^ 1))
                return true;
        return false;
    }
}