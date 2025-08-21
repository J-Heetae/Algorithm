import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static List<Integer>[] adj;
    static int[] match;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for(int i=1; i<=N; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            for(int j=0; j<len; j++) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        match = new int[M + 1];
        for(int i=1; i<=N; i++) {
            visited = new boolean[M + 1];
            dfs(i);
        }

        int answer = 0;
        for(int i=1; i<=M; i++) {
            if(match[i] != 0) answer++;
        }
        System.out.print(answer);
    }

    static boolean dfs(int worker) {
        for(int work : adj[worker]) {
            if(visited[work]) {
                continue;
            }
            visited[work] = true;

            if(match[work] == 0 || dfs(match[work])) {
                match[work] = worker;
                return true;
            }
        }
        return false;
    }
}