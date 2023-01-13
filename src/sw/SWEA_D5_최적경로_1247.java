package sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D5_최적경로_1247 {

    static int N;
    static int min;
    static boolean[] visited;
    static int[][] coor;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            coor = new int[N+2][2];
            min = Integer.MAX_VALUE;


            for(int i=0; i<N+2; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                coor[i][0] = x;
                coor[i][1] = y;
            }

            visited = new boolean[N+2];

            DFS(0,0, 0);

            System.out.printf("#%d %d\n", tc+1, min);
        }

    }

    static void DFS(int depth, int idx, int dis) {
        if(dis > min) return;

        if(depth == N) {
            dis += Math.abs(coor[idx][0] - coor[1][0]) + Math.abs(coor[idx][1] - coor[1][1]);
            min = Math.min(min, dis);
            return;
        }


        for(int i=2; i<N+2; i++) {
            if (!visited[i]) {
                visited[i] = true;
                DFS(depth + 1, i, dis + Math.abs(coor[idx][0] - coor[i][0]) + Math.abs(coor[idx][1] - coor[i][1]));
                visited[i] = false;
            }
        }
    }
}
