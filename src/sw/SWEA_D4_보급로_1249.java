package sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_D4_보급로_1249 {
    static int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int N;
    static int[][] map;
    static int[][] time_map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            time_map = new int[N][N];

            for(int i=0; i<N; i++) {
                String number = br.readLine();
                for(int j=0; j<N; j++) {
                    map[i][j] = number.charAt(j) - '0';
                    time_map[i][j] = Integer.MAX_VALUE;
                }
            }

            time_map[0][0] = 0;
            BFS(new Point(0, 0));
            System.out.printf("#%d %d\n", tc+1, time_map[N-1][N-1]);
        }
    }
    static class Point {
        int x,y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void BFS(Point p) {
        Queue<Point> que = new LinkedList<>();
        que.offer(p);

        while(!que.isEmpty()) {
            Point cur = que.poll();
            int x = cur.x;
            int y = cur.y;

            if(time_map[N-1][N-1] < time_map[x][y]) continue;

            for(int i=0; i<4; i++) {
                int nx = x + d[i][0];
                int ny = y + d[i][1];

                if(nx >=0 && nx < N && ny >=0 && ny < N) {
                    if(time_map[nx][ny] > time_map[x][y] + map[nx][ny]) {
                        time_map[nx][ny] = time_map[x][y] + map[nx][ny];
                        que.offer(new Point(nx, ny));
                    }
                }
            }
        }

    }
}