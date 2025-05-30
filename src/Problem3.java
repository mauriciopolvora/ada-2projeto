import UnionFind.UnionFindInArray;
import java.util.*;

public class Problem3 {
    private final List<Road> roads;
    private boolean isSorted = false;
    private final int numLocations;

    public Problem3(int numLocations, int numRoads) {
        this.roads = new ArrayList<>(numRoads);
        this.numLocations = numLocations;
    }

    public void addRoad(int l1, int l2, int h) {
        roads.add(new Road(l1, l2, h));
        isSorted = false;
    }

    private void sortRoads() {
        if (!isSorted) {
            roads.sort(Comparator.comparingInt(Road::getH));
            isSorted = true;
        }
    }

    // Only using UnionFind
    public int findHardness(int start, int destination) {
        sortRoads();

        UnionFindInArray uf = new UnionFindInArray(numLocations);

        for (Road road : roads) {
            try {
                int rep1 = uf.find(road.getFrom());
                int rep2 = uf.find(road.getTo());

                if (rep1 != rep2) {
                    uf.union(rep1, rep2);

                    if (uf.find(start) == uf.find(destination)) {
                        return road.getH();
                    }
                }
            } catch (Exception e) {
                System.out.println("UnionFind Failed");
            }
        }

        return -1;
    }
}