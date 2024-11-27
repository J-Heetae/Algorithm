import java.util.*;

public class Main {

    static class Edge {
        int to;
        int distance;

        Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        long distance;

        Node(int vertex, long distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.distance, o.distance);
        }
    }

    static List<Edge>[] graph;
    static int[] prev;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int distance = sc.nextInt();
            graph[a].add(new Edge(b, distance));
            graph[b].add(new Edge(a, distance));
        }

        long maxDiffer = 0L;
        long beforeMinDistance = dijkstra(1, n, n);
        int path = n;

        while (prev[path] != -1) {
            int before = prev[path];
            int orginDistance = 0;

            for (int i=0; i<graph[path].size(); i++) {
                Edge edge1 = graph[path].get(i); 
                if (edge1.to == before) {
                    orginDistance = edge1.distance;
                    edge1.distance *= 2;
                    for (int j=0; j<graph[before].size(); j++) {
                        Edge edge2 = graph[before].get(j);
                        if (edge2.to == path) {
                            edge2.distance *= 2;

                            long afterMinDistance = dijkstra(1, n, n);
                            maxDiffer = Math.max(maxDiffer, afterMinDistance - beforeMinDistance);

                            edge2.distance = orginDistance;
                            break;
                        }
                    }
                    edge1.distance = orginDistance;
                    break;
                }
            }
            path = before;
        }
        System.out.print(maxDiffer);
    }

    static long dijkstra(int start, int end, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] distance = new long[n + 1];
        prev = new int[n + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        distance[start] = 0;
        pq.offer(new Node(start, 0L));
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            if (distance[u] < current.distance) {
                continue;
            }

            for (Edge edge : graph[u]) {
                int v = edge.to;
                long newDistance = current.distance + edge.distance;

                if (distance[v] > newDistance) {
                    prev[v] = u;
                    distance[v] = newDistance;
                    pq.offer(new Node(v, newDistance));
                }
            }
        }
        return distance[end];
    }
}