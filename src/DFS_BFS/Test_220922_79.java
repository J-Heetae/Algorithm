package DFS_BFS;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//미로의 최단거리(BFS)
public class Test_220922_79 {
    static int[][] box;
    static int w, h, days = 0, ripeX = 0;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};


    private static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void BFS() {
        Queue<Point> que = new LinkedList<>();
        boolean flag = false;

        for(int i=0; i<w; i++)  {
            for(int j=0; j<h; j++) {
                if(box[i][j] == 1) que.offer(new Point(i, j));
                else if(box[i][j] == 0) ripeX++;
            }
        }

        while(!que.isEmpty()) {
            int size = que.size();
            for(int i=0; i<size; i++) {
                Point xy = que.poll();
                int x = xy.x;
                int y = xy.y;
                for(int j=0; j<4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if(nx >= 0 && nx < w && ny >= 0 && ny < h && box[nx][ny] == 0) {
                        box[nx][ny] = 1;
                        ripeX--;
                        que.offer(new Point(nx, ny));
                    }
                    if(ripeX == 0) {
                        flag = true;
                        break;
                    }
                }
                if(flag) break;
            }
            days++;
            if(flag) break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        h = sc.nextInt();
        w = sc.nextInt();
        box = new int[w][h];

        for(int i=0; i<w; i++)  {
            for(int j=0; j<h; j++) {
                box[i][j] = sc.nextInt();
            }
        }

        BFS();

        System.out.println(days);
    }
}