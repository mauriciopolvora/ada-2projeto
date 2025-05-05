import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // Authors: Mauricio Polvora (N 64879) and Alexandre Marques (N 65370)
    // For the resolution of the problem, we used the help of Github Copilot for code debugging,
    // optimization of written code by comparing different implementations, and for generating test cases.
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Read the first line: L (locations) and R (roads)
        String[] firstLine = reader.readLine().split(" ");
        int L = Integer.parseInt(firstLine[0]);
        int R = Integer.parseInt(firstLine[1]);

        // Create a problem instance with L locations
        Problem2 problem = new Problem2(L);

        // Read the R roads
        for (int i = 0; i < R; i++) {
            String[] parts = reader.readLine().split(" ");
            int l1 = Integer.parseInt(parts[0]);
            int l2 = Integer.parseInt(parts[1]);
            int h = Integer.parseInt(parts[2]);

            // Add road between locations l1 and l2 with travel time h
            problem.addRoad(l1, l2, h);
        }

        // Read the number of test cases
        int T = Integer.parseInt(reader.readLine());

        // Process each test case
        for (int i = 0; i < T; i++) {
            String[] parts = reader.readLine().split(" ");
            int startLocation = Integer.parseInt(parts[0]);
            int destinationLocation = Integer.parseInt(parts[1]);

            // Find shortest path and print the result
            int shortestTime = problem.findShortestPath(startLocation, destinationLocation);
            System.out.println(shortestTime);
        }
    }
}