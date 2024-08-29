import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            int n = Integer.parseInt(line);

            HopcroftKarp hopcroftKarp = new HopcroftKarp(n);

            for (int i = 0; i < n; i++) {
                line = br.readLine().trim();
                StringTokenizer st = new StringTokenizer(line, ": ()");
                int job = Integer.parseInt(st.nextToken());
                int numServers = Integer.parseInt(st.nextToken());
                for (int j = 0; j < numServers; j++) {
                    hopcroftKarp.addEdge(job, Integer.parseInt(st.nextToken()));
                }
            }
            int maxMatching = hopcroftKarp.hopcroftKarp();
            sb.append(maxMatching).append("\n");
        }
        System.out.println(sb);
    }

    static class HopcroftKarp {
        private static final int NIL = 0;
        private static final int INF = Integer.MAX_VALUE;

        int n, start;
        ArrayList<Integer>[] adj;
        int[] pair, dist;

        public HopcroftKarp(int n) {
            this.n = n;
            this.start = 2 * n;
            this.pair = new int[2 * n];
            this.dist = new int[2 * n + 1];
            this.adj = new ArrayList[2 * n];
            for (int i = 0; i < 2 * n; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int job, int server) {
            this.adj[job].add(server);
        }

        private boolean bfs() {
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (pair[i] == start) {
                    queue.offer(i);
                    dist[i] = 0;
                } else {
                    dist[i] = INF;
                }
            }
            dist[start] = INF;
            while (!queue.isEmpty()) {
                int job = queue.poll();
                if (dist[job] < dist[start]) {
                    for (Integer server : adj[job]) {
                        if (dist[pair[server]] == INF) {
                            dist[pair[server]] = dist[job] + 1;
                            queue.offer(pair[server]);
                        }
                    }
                }
            }
            return dist[start] != INF;
        }

        private boolean dfs(int job) {
            if (job != start) {
                for (Integer server : adj[job]) {
                    if (dist[pair[server]] == dist[job] + 1) {
                        if (dfs(pair[server])) {
                            pair[job] = server;
                            pair[server] = job;
                            return true;
                        }
                    }
                }
                dist[job] = INF;
                return false;
            }
            return true;
        }

        public int hopcroftKarp() {
            Arrays.fill(pair, start);
            int matching = 0;
            while (bfs()) {
                for (int i = 0; i < n; i++) {
                    if (pair[i] == start && dfs(i)) {
                        matching++;
                    }
                }
            }
            return matching;
        }
    }
}