import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] minCost = new int[n + 1];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        int[][] busArr = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++)
            Arrays.fill(busArr[i], Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");

            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            int cost = Integer.parseInt(line[2]);

            if (busArr[from][to] > cost) {
                busArr[from][to] = cost;
            }
        }

        String[] line = br.readLine().split(" ");
        int start = Integer.parseInt(line[0]);
        int end = Integer.parseInt(line[1]);

        PriorityQueue<Route> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        for (int i = 1; i <= n; i++) {
            if (busArr[start][i] != Integer.MAX_VALUE) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(start);
                list.add(i);
                pq.offer(new Route(i, busArr[start][i], list));
            }
        }

        Route cheapestRoute = null;
        minCost[start] = 0;
        while (!pq.isEmpty()) {
            Route route = pq.poll();

            if (route.to == end) {
                cheapestRoute = route;
                break;
            }

            for (int i = 1; i <= n; i++) {
                if (busArr[route.to][i] != Integer.MAX_VALUE && minCost[i] > busArr[route.to][i] + route.cost) {
                    minCost[i] = busArr[route.to][i] + route.cost;
                    ArrayList<Integer> list = new ArrayList<>(route.cityList);
                    list.add(i);
                    pq.offer(new Route(i, minCost[i], list));
                }
            }
        }
        System.out.println(cheapestRoute.cost);
        System.out.println(cheapestRoute.cityList.size());
        for (Integer i : cheapestRoute.cityList) {
            System.out.print(i + " ");
        }
    }

    static class Route {
        int to, cost;
        ArrayList<Integer> cityList;

        public Route(int to, int cost, ArrayList<Integer> cityList) {
            this.to = to;
            this.cost = cost;
            this.cityList = cityList;
        }

        @Override
        public String toString() {
            return "Route{" +
                "to=" + to +
                ", cost=" + cost +
                ", cityList=" + cityList +
                '}';
        }
    }
}