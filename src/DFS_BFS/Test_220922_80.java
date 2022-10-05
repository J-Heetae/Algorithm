package DFS_BFS;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//미로의 최단거리(BFS)
public class Test_220922_80 {
    static int[][] map;
    static int n, island = 0;
    static int[] dx = {1, 0, -1, 0, 1 ,1, -1, -1};
    static int[] dy = {0, 1, 0, -1, 1, -1, 1, -1};


    private static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void DFS(Point point) {
        for(int j=0; j<8; j++) {
            int nx = point.x + dx[j];
            int ny = point.y + dy[j];
            if((nx >= 0) && (nx < n) && (ny >= 0) && (ny < n) && (map[nx][ny] == 1)) {
                map[nx][ny] = 0;
                DFS(new Point(nx, ny));
            }
        }
    }

    private static void BFS(Point point) {
        Queue<Point> que = new LinkedList<>();
        que.offer(point);

        while(!que.isEmpty()) {
            Point xy = que.poll();
            int x = xy.x;
            int y = xy.y;
            for(int j=0; j<8; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if((nx >= 0) && (nx < n) && (ny >= 0) && (ny < n)
                        && (map[nx][ny] == 1)) {
                    map[nx][ny] = 0;
                    que.offer(new Point(nx, ny));
                }
            }
        }
        island++;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];

        for(int i=0; i<n; i++)  {
            for(int j=0; j<n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        //BFS
        /*for(int i=0; i<n; i++)  {
            for(int j=0; j<n; j++) {
                if(city[i][j] == 1)
                    BFS(new Point(i, j));
            }
        }*/

        //DFS
        for(int i=0; i<n; i++)  {
            for(int j=0; j<n; j++) {
                if(map[i][j] == 1) {
                    island++;
                    map[i][j] = 0;
                    DFS(new Point(i, j));
                }
            }
        }

        System.out.println(island);
    }
}