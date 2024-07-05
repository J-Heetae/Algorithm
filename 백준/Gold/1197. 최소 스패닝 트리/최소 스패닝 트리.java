import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    static int[] parent;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(tokenizer.nextToken());
        int E = Integer.parseInt(tokenizer.nextToken());

        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(Comparator.comparing(a -> a[2]));
        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            pq.add(new int[] {a, b, w});
        }

        int totalWeight = 0;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int a = poll[0];
            int b = poll[1];
            int w = poll[2];

            if (find(a) != find(b)) {
                union(a, b);
                totalWeight += w;
            }
        }
        System.out.println(totalWeight);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA != parentB) {
            if (parentA > parentB) {
                parent[parentA] = parentB;
            } else {
                parent[parentB] = parentA;
            }
        }
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }
}