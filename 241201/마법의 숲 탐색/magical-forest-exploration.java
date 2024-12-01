import java.util.*;
import java.io.*;

public class Main {

    //북동남서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int r, c;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) + 3;
        c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        map = new int[r + 3][c];
        int total = 0;
        for(int i=1; i<=k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            total += simulation(1, y, d, i) - 2;
        }
        System.out.print(total);
    }

    static int simulation(int x, int y, int d, int k) {
        while(true) {
            //남쪽 확인
            int[] south = check(x, y, 2);
            if(south[0] != -1) {
                x = south[0];
                y = south[1];
                continue;
            }

            //서쪽 확인
            int[] west = check(x, y, 3);
            if(west[0] != -1) {
                x = west[0];
                y = west[1];
                d = ((d - 1) < 0) ? 3 : d - 1;
                continue;
            }

            //동쪽 확인
            int[] east = check(x, y, 1);
            if(east[0] != -1) {
                x = east[0];
                y = east[1];
                d = (d + 1) % 4;
                continue;
            }
            break;
        }

        if(x < 4) { //골렘 몸이 삐져나왔으면
            map = new int[r + 3][c];
            return 2;
        }

        map[x][y] = k;
        for(int i=0; i<4; i++) {
            map[x + dx[i]][y + dy[i]] = k;
        }
        map[x + dx[d]][y + dy[d]] = k + 1_000;

        //최대한 아래로 이동
        return move(x, y, k);
    }

    static int move(int x, int y, int k) {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[r][c];
        que.offer(new int[]{x, y});
        visited[x][y] = true;
        
        int maxC = Integer.MIN_VALUE;
        while(!que.isEmpty() && maxC != (r - 1)) {
            int[] poll = que.poll();
            int currX = poll[0];
            int currY = poll[1];

            maxC = Math.max(maxC, currX);
            for(int i=0; i<4; i++) {
                int nx = currX + dx[i];
                int ny = currY + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= r || ny >= c || visited[nx][ny]) {
                    continue;
                }

                if(map[currX][currY] == map[nx][ny] || (map[currX][currY] + 1_000) == map[nx][ny] || (map[currX][currY] > 1_000 && map[nx][ny] > 0)) {
                    visited[nx][ny] = true;
                    que.offer(new int[]{nx, ny});
                }
            }
        }
        return maxC;
    }

    static int[] check(int x, int y, int d) {
        if(d == 2) { //남
            int sx = x + 2 * dx[2];
            int sy = y;
            int wsx = x + dx[2] + dx[3];
            int wsy = y + dy[2] + dy[3];
            int esx = x + dx[2] + dx[1];
            int esy = y + dy[2] + dy[1];
            if(isValid(sx, sy) && isValid(wsx, wsy) && isValid(esx, esy)) {
                return new int[]{x + dx[2], y + dy[2]};   
            }
        } else if(d == 3) { //서
            int nwx = x + dx[0];
            int nwy = y + dy[3];
            int wwx = x;
            int wwy = y + 2 * dy[3];
            int wwsx = x + dx[2];            
            int wwsy = y + 2 * dy[3];
            int wsx = x + dx[2];
            int wsy = y + dy[3];
            int wssx = x + 2 * dx[2];
            int wssy = y + dy[3];
            if(isValid(nwx, nwy) && isValid(wwx, wwy) && isValid(wwsx, wwsy) && isValid(wsx, wsy) & isValid(wssx, wssy)) {
                return new int[]{x + dx[2], y + dy[3]};   
            }
        } else if(d == 1) { //동
            int nex = x + dx[0];
            int ney = y + dy[1];
            int eex = x;
            int eey = y + 2 * dy[1];
            int eesx = x + dx[2];            
            int eesy = y + 2 * dy[1];
            int esx = x + dx[2];
            int esy = y + dy[1];
            int essx = x + 2 * dx[2];
            int essy = y + dy[1];
            if(isValid(nex, ney) && isValid(eex, eey) && isValid(eesx, eesy) && isValid(esx, esy) & isValid(essx, essy)) {
                return new int[]{x + dx[2], y + dy[1]};   
            }
        }
        return new int[]{-1, -1};
    }

    static boolean isValid(int x, int y) {
        if(x >= r || y < 0 || y >= c || map[x][y] != 0) {
            return false;
        }
        return true;
    }
}