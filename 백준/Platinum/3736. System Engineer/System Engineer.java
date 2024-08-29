import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            int n = Integer.parseInt(line);
            List<Integer>[] jobToServers = new ArrayList[n];

            for (int i = 0; i < n; i++) {
                line = br.readLine().trim();
                StringTokenizer st = new StringTokenizer(line, ": ()");
                int job = Integer.parseInt(st.nextToken());
                int numServers = Integer.parseInt(st.nextToken());
                jobToServers[job] = new ArrayList<>();

                for (int j = 0; j < numServers; j++) {
                    jobToServers[job].add(Integer.parseInt(st.nextToken()));
                }
            }

            System.out.println(maximumMatching(jobToServers, n));
        }
    }

    private static int maximumMatching(List<Integer>[] jobToServers, int n) {
        int[] pairJob = new int[n];
        int[] pairServer = new int[2 * n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            pairJob[i] = -1;
        }
        for (int i = 0; i < 2 * n; i++) {
            pairServer[i] = -1;
        }

        int matching = 0;
        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            if (dfs(i, jobToServers, pairJob, pairServer, visited)) {
                matching++;
            }
        }
        return matching;
    }

    private static boolean dfs(int job, List<Integer>[] jobToServers, int[] pairJob, int[] pairServer, boolean[] visited) {
        if (visited[job]) return false;
        visited[job] = true;

        for (int server : jobToServers[job]) {
            if (pairServer[server] == -1 || dfs(pairServer[server], jobToServers, pairJob, pairServer, visited)) {
                pairJob[job] = server;
                pairServer[server] = job;
                return true;
            }
        }
        return false;
    }
}
