import UnionFind.UnionFindInArray;

import java.util.*;

public class Problem {
    private final List<Road> roads;
    private final int numLocations;
    private final LinkedList<Road>[] mst; // MST as array of LinkedList of Road
    private boolean mstBuilt = false;

    @SuppressWarnings("unchecked")
    public Problem(int numLocations, int numRoads) {
        this.roads = new ArrayList<>(numRoads);
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
    public int findHardness(int start, int destination) {
        // Ensure MST is built
        buildMST();

        boolean[] visited = new boolean[numLocations];
        int[] maxRoadTime = new int[numLocations];

        Arrays.fill(maxRoadTime, Integer.MAX_VALUE);
        maxRoadTime[start] = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int current = stack.peek();

            if (current == destination) {
                break; // Found the destination
            }

            boolean foundUnvisited = false;
            for (Road road : mst[current]) {
                int next = road.getTo();
                int weight = road.getH();

                if (!visited[next]) {
                    visited[next] = true;
                    maxRoadTime[next] = Math.max(maxRoadTime[current], weight);
                    stack.push(next);
                    foundUnvisited = true;
                    break;
                }
            }

            if (!foundUnvisited) {
                stack.pop(); // Backtrack if no unvisited neighbors
            }
        }

        return maxRoadTime[destination];
    }
}