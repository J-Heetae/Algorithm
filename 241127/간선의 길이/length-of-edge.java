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

        // 최단 거리 찾기 (정점 1에서 n까지)
        long beforeMinDistance = dijkstra(1, n, n);

        long maxDiffer = 0L;

        // 최단 경로 상의 간선 추적
        int path = n;
        while (prev[path] != -1) {
            int before = prev[path];

            // 경로 상의 간선에 대해 거리 두 배로 변경 후 차이 계산
            for (Edge edge : graph[before]) {
                if (edge.to == path) {
                    int originalDistance = edge.distance;
                    long doubledDistance = originalDistance * 2;

                    // 현재 edge만 수정된 상태로 다시 계산
                    long afterMinDistance = dijkstraWithModifiedEdge(1, n, n, before, path, doubledDistance);
                    maxDiffer = Math.max(maxDiffer, afterMinDistance - beforeMinDistance);
                    break;
                }
            }

            path = prev[path];
        }

        System.out.print(maxDiffer);
    }

    // 다익스트라 알고리즘
    static long dijkstra(int start, int end, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] distance = new long[n + 1];
        prev = new int[n + 1];

        Arrays.fill(distance, Long.MAX_VALUE);
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
                long newDistance = distance[u] + edge.distance;

                if (distance[v] > newDistance) {
                    prev[v] = u;
                    distance[v] = newDistance;
                    pq.offer(new Node(v, newDistance));
                }
            }
        }
        return distance[end];
    }

    // 특정 간선 가중치를 수정하고 다익스트라 실행
    static long dijkstraWithModifiedEdge(int start, int end, int n, int modifyFrom, int modifyTo, long newDistance) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] distance = new long[n + 1];
        int[] localPrev = new int[n + 1];

        Arrays.fill(distance, Long.MAX_VALUE);
        Arrays.fill(localPrev, -1);

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
                long effectiveDistance = (u == modifyFrom && v == modifyTo) ? newDistance : edge.distance;
                long newTotalDistance = distance[u] + effectiveDistance;

                if (distance[v] > newTotalDistance) {
                    localPrev[v] = u;
                    distance[v] = newTotalDistance;
                    pq.offer(new Node(v, newTotalDistance));
                }
            }
        }
        return distance[end];
    }
}
