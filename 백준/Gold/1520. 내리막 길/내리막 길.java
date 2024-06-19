import java.io.*;
import java.util.PriorityQueue;

public class Main {

    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};

    static int N, M;
    static int[][] map, dp;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        map = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        visited[0][0] = true;
        dp[0][0] = 1;
        PriorityQueue<Spot> pq = new PriorityQueue<>((a, b) -> b.height - a.height);
        pq.offer(new Spot(0, 0, map[0][0]));
        while (!pq.isEmpty()) {
            Spot cur = pq.poll();

            if (cur.x == N - 1 && cur.y == M - 1)
                continue;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + DX[i];
                int ny = cur.y + DY[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                if (map[nx][ny] < map[cur.x][cur.y]) {
                    dp[nx][ny] += dp[cur.x][cur.y];
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        pq.offer(new Spot(nx, ny, map[nx][ny]));
                    }
                }
            }
        }
        System.out.println(dp[N - 1][M - 1]);
    }

    static class Spot {
        int x, y, height;

        public Spot(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}