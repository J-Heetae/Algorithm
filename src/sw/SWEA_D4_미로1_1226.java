package sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_D4_미로1_1226 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] maze = new int[16][16];
    static Coor start;
    static Queue<Coor> que;
    static boolean possible = false;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int tc=0; tc<10; tc++) {
            int T = Integer.parseInt(br.readLine());

            for(int i=0; i<16; i++) {
                String line = br.readLine();

                for(int j=0; j<16; j++) {
                    maze[i][j] = line.charAt(j) - '0';

                    if(maze[i][j] == 2)
                        start = new Coor(i, j);
                }
            }

            que = new LinkedList<>();
            visited = new boolean[16][16];
            possible = false;

            que.offer(start);
            visited[start.x][start.y] = true;

            BFS();

            if(possible) {
                System.out.printf("#%d 1\n",T);
            } else {
                System.out.printf("#%d 0\n",T);
            }
        }
    }

    private static void BFS() {
        while(!que.isEmpty()) {
            Coor cur = que.poll();

            for(int i=0; i<4; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];

                if(maze[x][y] == 0 && !visited[x][y]) {
                    visited[x][y] = true;
                    que.offer(new Coor(x,y));
                }
                if(maze[x][y] == 3) {
                    possible = true;
                    return;
                }
            }
        }
    }

    static class Coor{
        int x, y;
        Coor(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}