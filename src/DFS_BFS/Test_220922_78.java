package DFS_BFS;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//미로의 최단거리(BFS)
public class Test_220922_78 {
    static int[][] maze;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int count = 0, answer = Integer.MAX_VALUE;
    static boolean flag = true;
    private static void BFS() {
        Queue<Integer> queX = new LinkedList<>();
        Queue<Integer> queY = new LinkedList<>();
        queX.offer(1);
        queY.offer(1);
        boolean flag = false;

        while(!queX.isEmpty()) {
            int size = queX.size();
            for(int i=0; i<size; i++) {
                int x = queX.poll();
                int y = queY.poll();
                for(int j=0; j<4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if(nx >= 1 && nx <= 7 && ny >= 1 && ny <= 7 && maze[nx][ny] == 0) {
                        maze[nx][ny] = 1;
                        queX.offer(nx);
                        queY.offer(ny);
                        if(nx == 7 && ny == 7) {
                            flag = true;
                            break;
                        }
                    }
                }
                if(flag) break;
            }
            count++;
            if(flag) {
                answer = count;
                break;
            }
        }

        if(!flag) answer = -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        maze = new int[8][8];
        for(int i=1; i<=7; i++) {
            for(int j=1; j<=7; j++) {
                maze[i][j] = sc.nextInt();
            }
        }

        maze[1][1] = 1;
        BFS();

        System.out.println(answer);
    }
}