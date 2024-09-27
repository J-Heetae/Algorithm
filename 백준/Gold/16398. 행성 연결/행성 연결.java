import java.io.*;
import java.util.*;

class Main {

    static int[] parent, level;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] read;
        int N = Integer.parseInt(br.readLine());

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        level = new int[N];

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        for (int a = 0; a < N; a++) {
            read = br.readLine().split(" ");
            for (int b = a + 1; b < N; b++) {
                pq.offer(new int[] {a, b, Integer.parseInt(read[b])});
            }
        }

        long totalCost = 0;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int a = poll[0];
            int b = poll[1];
            int cost = poll[2];
            if (union(a, b)) {
                totalCost += cost;
            }
        }
        System.out.println(totalCost);
    }

    static boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA != parentB) {
            if (level[parentA] >= level[parentB]) {
                parent[parentB] = parentA;
                level[parentA]++;
            } else {
                parent[parentA] = parentB;
                level[parentB]++;
            }
            return true;
        }
        return false;
    }

    static int find(int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }
}
