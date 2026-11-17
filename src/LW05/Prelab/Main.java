package LW05.Prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static int size;
    static String[] names;
    static int[][] graph;
    static List<Integer> dfsOrder = new ArrayList<>();

    // Add an undirected, weighted walkway
    static void addEdge(int v1, int v2, int minutes) {
        graph[v1][v2] = minutes;
        graph[v2][v1] = minutes;
    }

    // Print a list of buildings: A -> B -> C
    static void printPath(List<Integer> path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) sb.append(" -> ");
            sb.append(names[path.get(i)]);
        }
        System.out.println(sb);
    }

    // BFS: level by level using a Queue (FIFO)
    static List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);                                   // blank 1: enqueue start

        while (!queue.isEmpty()) {
            int current = queue.poll();                     // blank 2: dequeue
            order.add(current);
            for (int i = 0; i < size; i++) {
                if (graph[current][i] != 0 && !visited.contains(i)) {   // blank 3
                    visited.add(i);
                    queue.add(i);
                }
            }
        }
        return order;
    }

    // DFS: go as deep as possible using recursion
    static void dfs(int current, Set<Integer> visited) {
        visited.add(current);
        dfsOrder.add(current);                              // blank 1: record order
        for (int i = 0; i < size; i++) {
            if (graph[current][i] != 0 && !visited.contains(i)) {
                dfs(i, visited);                            // blank 2: recurse
            }
        }
    }

    // Helper: unvisited building with the smallest distance (smaller index wins ties)
    static int findTheNextNode(int[] dist, boolean[] visited) {
        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < minValue) {        // blank: strict < keeps smaller index
                minValue = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    // Helper: print the route by following prev backwards
    static void route(int[] prev, int dest) {
        LinkedList<Integer> path = new LinkedList<>();
        for (int v = dest; v != -1; v = prev[v]) {
            path.addFirst(v);                               // blank: build route backwards
        }
        printPath(path);
    }

    // Dijkstra: shortest times and route
    static void dijkstraAlg(int start, int dest) {
        int[] dist = new int[size];
        int[] prev = new int[size];
        boolean[] visited = new boolean[size];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;                                    // blank 1: start distance

        for (int step = 0; step < size; step++) {
            int u = findTheNextNode(dist, visited);
            if (u == -1) break;
            visited[u] = true;                              // blank 2: mark visited
            for (int v = 0; v < size; v++) {
                if (graph[u][v] != 0 && !visited[v]
                        && dist[u] + graph[u][v] < dist[v]) {   // blank 3: relaxation
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");
        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + " : " + dist[i] + " min");
        }
        System.out.println("=== Shortest Route from " + names[start] + " to " + names[dest] + " ===");
        route(prev, dest);
        System.out.println("Total time: " + dist[dest] + " min");
    }

    public static void main(String[] args) throws FileNotFoundException {
        String fileName = args.length > 0 ? args[0] : "campus.txt";
        File file = new File(fileName);
        if (!file.exists()) {
            file = new File("LW05/Prelab/" + fileName); // kalau dijalankan dari root project
        }
        Scanner sc = new Scanner(file);

        size = sc.nextInt();
        names = new String[size];
        for (int i = 0; i < size; i++) {
            names[i] = sc.next();
        }
        graph = new int[size][size];

        int e = sc.nextInt();
        for (int i = 0; i < e; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int minutes = sc.nextInt();
            addEdge(v1, v2, minutes);
        }

        int start = sc.nextInt();
        int dest = sc.nextInt();
        sc.close();

        System.out.println("=== BFS from " + names[start] + " ===");
        printPath(bfs(start));

        System.out.println("=== DFS from " + names[start] + " ===");
        dfs(start, new HashSet<>());
        printPath(dfsOrder);

        dijkstraAlg(start, dest);
    }
}
