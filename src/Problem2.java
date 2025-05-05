import UnionFind.UnionFindInArray;

import java.util.*;

public class Problem2 {
    private final List<Road> roads;
    private final int numLocations;
    private final LinkedList<Road>[] mst; // MST as array of LinkedList of Road
    private boolean mstBuilt = false;

    @SuppressWarnings("unchecked")
    public Problem2(int numLocations) {
        this.roads = new ArrayList<>();
        this.numLocations = numLocations;
        this.mst = new LinkedList[numLocations];

        // Initialize LinkedLists
        for (int i = 0; i < numLocations; i++) {
            this.mst[i] = new LinkedList<>();
        }
    }

    public void addRoad(int l1, int l2, int h) {
        roads.add(new Road(l1, l2, h));
        mstBuilt = false;
    }

    // Build MST using Kruskal's algorithm (only once)
    private void buildMST() {
        if (mstBuilt) return;

        // Sort roads by travel time
        roads.sort(Comparator.comparingInt(Road::getH));

        UnionFindInArray uf = new UnionFindInArray(numLocations);

        for (Road road : roads) {
            try {
                int from = road.getFrom();
                int to = road.getTo();

                if (uf.find(from) != uf.find(to)) {
                    uf.union(uf.find(from), uf.find(to));

                    mst[from].add(new Road(from, to, road.getH()));
                    mst[to].add(new Road(to, from, road.getH()));
                }
            } catch (Exception e) {
                System.out.println("UnionFind Failed");
            }
        }

        mstBuilt = true;
    }

    // Find hardness of journey between start and destination using DFS
    public int findShortestPath(int start, int destination) {
        // Ensure MST is built
        buildMST();

        if (start == destination) return 0;

        boolean[] visited = new boolean[numLocations];
        int[] maxEdgeWeight = new int[numLocations];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // destination reached
            if (current == destination) {
                return maxEdgeWeight[destination];
            }

            // neighbors
            for (Road road : mst[current]) {
                int next = road.getTo();
                int weight = road.getH();

                if (!visited[next]) {
                    visited[next] = true;
                    maxEdgeWeight[next] = Math.max(maxEdgeWeight[current], weight);
                    queue.add(next);
                }
            }
        }

        return -1; // Shoulnt happen if MST works
    }
}
