import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final String SAVE_HIM = "SAVE HIM";
    static final String GOOD_BYE = "GOOD BYE";

    static ArrayList<ArrayList<int[]>> list;
    static int V, E, P;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new int[] {b, c});
            list.get(b).add(new int[] {a, c});
        }

        int[] distFromStart = dijkstra(1);
        int[] distFromP = dijkstra(P);

        // If the shortest path from start to end is equal to the path through P, then save him
        if (distFromStart[V] == distFromStart[P] + distFromP[V]) {
            System.out.println(SAVE_HIM);
        } else {
            System.out.println(GOOD_BYE);
        }
    }

    static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.length, b.length));
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.vertex;
            int length = node.length;

            if (length > dist[cur]) continue; // Skip if this path is not the shortest

            for (int[] neighbor : list.get(cur)) {
                int next = neighbor[0];
                int nextLength = length + neighbor[1];

                if (nextLength < dist[next]) {
                    dist[next] = nextLength;
                    pq.offer(new Node(next, nextLength));
                }
            }
        }

        return dist;
    }

    static class Node {
        int vertex;
        int length;

        public Node(int vertex, int length) {
            this.vertex = vertex;
            this.length = length;
        }
    }
}