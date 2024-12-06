import java.util.*;

public class Main {

    static class Coor {
        int x,y;
        Coor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static int N, M, total_move;
    static int sx, sy, square_size, escape_count;
    static Coor exit;
    static Coor[] p;
    static int[][] maze;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int K = sc.nextInt();
        
        maze = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                maze[i][j] = sc.nextInt();
            }
        }

        p = new Coor[M];
        for(int i=0; i<M; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            p[i] = new Coor(x, y);
        }

        int x = sc.nextInt() - 1;
        int y = sc.nextInt() - 1;
        exit = new Coor(x, y);

        while(K-- > 0) {
            move();
            if(escape_count == M) {
                break;
            }
            findSquare();
            rotate();
        }
        System.out.println(total_move);
        System.out.println((exit.x + 1) + " " + (exit.y + 1));
    }

    static void rotate() {
        int[][] temp_maze = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                temp_maze[i][j] = maze[i][j];
            }
        }

        for(int x=sx; x<sx+square_size; x++) {
            for(int y=sy; y<sy+square_size; y++) {
                int ox = x - sx, oy = y - sy;
                int rx = oy, ry = square_size - ox - 1;
                temp_maze[sx + rx][sy + ry] = maze[x][y];
            }
        }
        
        for(int x=sx; x<sx+square_size; x++) {
            for(int y=sy; y<sy+square_size; y++) {
                maze[x][y] = temp_maze[x][y];
                if (maze[x][y] > 0) {
                    maze[x][y]--;
                }
            }
        }

        //출구 위치 이동
        int origin_exit_x = exit.x;
        int origin_exit_y = exit.y;
        exit.x = (origin_exit_y - sy) + sx;
        exit.y = square_size - (origin_exit_x - sx) - 1 + sy;

        //참가자 위치 이동
        for(Coor c : p) {
            if(c.x == -1) { //탈출했으면 넘어가기
                continue;
            }
            if(c.x < sx || c.x >= sx + square_size || c.y < sy || c.y >= sy + square_size) {
                continue;
            }
            int origin_x = c.x;
            int origin_y = c.y;
            c.x = (origin_y - sy) + sx;
            c.y = square_size - (origin_x - sx) - 1 + sy;
        }
    }

    static void findSquare() {
        for(int size=2; size<=N; size++) {
            for(int x=0; x<=N-size; x++) {
                for(int y=0; y<=N-size; y++) {
                    //출구 확인
                    if(exit.x < x || exit.x >= x + size || exit.y < y || exit.y >= y + size) {
                        continue;
                    }
                    //참가자 확인
                    for(Coor c : p) {
                        if(c.x < x || c.x >= x + size || c.y < y || c.y >= y + size) {
                            continue;
                        }
                        sx = x;
                        sy = y;
                        square_size = size;
                        return;
                    }
                }
            }
        }
    }

    static void move() {
        for(int i=0; i<M; i++) {
            if(p[i].x == -1) { //탈출 했으면 넘어가기
                continue;
            }
            
            Coor curr = p[i];
            int origin_distance = getDistance(curr.x, curr.y);

            for(int dir=0; dir<4; dir++) {
                int nx = curr.x + dx[dir];
                int ny = curr.y + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N ||
                    maze[nx][ny] > 0 ||
                    origin_distance <= getDistance(nx, ny)) {
                    continue;
                }
                total_move++;
                if(nx == exit.x && ny == exit.y) { //다음 이동할 곳이 출구
                    escape_count++; 
                    curr.x = -1;
                    curr.y = -1;
                } else {
                    curr.x = nx;
                    curr.y = ny;
                }
                break;
            }
        }
    }

    static int getDistance(int x, int y) {
        return (int)(Math.abs(x - exit.x) + Math.abs(y - exit.y));
    }
}