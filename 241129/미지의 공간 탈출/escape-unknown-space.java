import java.util.*;

public class Main {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int n, m, f;
    static int[] connected = new int[4];
    static int[][] map;
    static int[][][] walls;
    static Phnomenon[] pArr;
    static boolean[][] mapVisited;
    static boolean[][][] wallsVisited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        f = sc.nextInt();

        Arrays.fill(connected, -1);
        map = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j] == 3) {
                    if(connected[0] == -1 || connected[0] < j) {
                        connected[0] = j;
                    }
                    if(connected[1] == -1 || connected[1] > j) {
                        connected[1] = j;
                    }
                    if(connected[2] == -1 || connected[2] < i) {
                        connected[2] = i;
                    }
                    if(connected[3] == -1 || connected[3] > i) {
                        connected[3] = i;
                    }
                }
            }
        }

        //동서남북위
        walls = new int[5][m][m];
        for(int i=0; i<5; i++) {
            for(int j=0; j<m; j++) {
                for(int k=0; k<m; k++) {
                    walls[i][j][k] = sc.nextInt();
                }
            }
        }

        int[] start = new int[2];
        for(int i=0; i<m; i++) {
            for(int j=0; j<m; j++) {
                if(walls[4][i][j] == 2) {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        pArr = new Phnomenon[f];
        for(int i=0; i<f; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();
            int v = sc.nextInt();
            pArr[i] = new Phnomenon(x, y, d, v, 5);
            map[x][y] = 1;
        }
        
        int result = simulation(start[0], start[1]);
        System.out.println(result);
    }

    static int simulation(int x, int y) {
        int turn = 0;

        Queue<TimeMachine> que = new LinkedList<>();
        mapVisited = new boolean[n][n];
        wallsVisited = new boolean[5][m][m];
        boolean[] pStop = new boolean[f];

        que.offer(new TimeMachine(x, y, 4, turn));
        wallsVisited[4][x][y] = true;

        // pos : 0동 1서 2남 3북 4위 5맵
        outer:
        while(true) {
            turn++;
            //시간 이상 현상
            for(int i=0; i<f; i++) {
                if(!pStop[i] && ((turn % pArr[i].v) == 0)) {
                    Phnomenon curr = pArr[i];
                    int[] next = move(curr.x, curr.y, curr.d, curr.pos);
                    if(next[0] != -1) {
                        curr.x = next[0];
                        curr.y = next[1];
                        curr.pos = next[2];
                        map[curr.x][curr.y] = 1;
                    } else {
                        pStop[i] = true;
                    }
                }
            }

            // System.out.println("turn = " + turn);

            while(!que.isEmpty()) {
                if(que.peek().turn == turn) {
                    break;
                }

                TimeMachine curr = que.poll();

                if(curr.pos == 5 && map[curr.x][curr.y] == 4) { //도착
                    // System.out.println("arrival!");
                    return curr.turn;
                }

                //타임머신 이동
                for(int d=0; d<4; d++) {
                    int[] next = move(curr.x, curr.y, d, curr.pos);
                    if(next[0] != -1) {
                        // System.out.println(next[0] + ", " + next[1] + ", " + next[2] + ", " + turn);
                        que.offer(new TimeMachine(next[0], next[1], next[2], turn));
                    }
                }
            }
            if(que.isEmpty()) {
                break;
            }
        }
        return -1;
    }

    static boolean isValid(int x, int y, int pos) {
        if(pos <= 4) {
            return walls[pos][x][y] != 1 && !wallsVisited[pos][x][y];
        } else {
            return map[x][y] != 1 && !mapVisited[x][y];
        }
    }

    static int[] move(int x, int y, int d, int pos) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if(pos == 0) { //동
            if(nx < 0) { //위의 우측
                int nnx = (m - 1) - ny;
                int nny = m - 1;
                pos = 4;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(nx >= m) { //바닥
                int nnx = connected[3] + ((m - 1) - ny);
                int nny = connected[pos] + 1;
                pos = 5;

                if(nnx >= 0 && nny >= 0 && nnx < n && nny < n && isValid(nnx, nny, pos)) {
                    mapVisited[nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(ny < 0) { //남의 오른쪽
                int nnx = nx;
                int nny = m - 1;
                pos = 2;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(ny >= m) { //북의 왼쪽
                int nnx = nx;
                int nny = 0;
                pos = 3;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else {
                if (isValid(nx, ny, pos)){
                    wallsVisited[pos][nx][ny] = true;
                    return new int[]{nx, ny, pos};
                }
            }
        } else if(pos == 1) { //서
            if(nx < 0) { //위의 좌측
                int nnx = ny;
                int nny = 0;
                pos = 4;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(nx >= m) { //바닥
                int nnx = connected[3] + ny;
                int nny = connected[pos] - 1;
                pos = 5;

                if(nnx >= 0 && nny >= 0 && nnx < n && nny < n && isValid(nnx, nny, pos)) {
                    mapVisited[nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(ny < 0) { //북의 오른쪽
                int nnx = nx;
                int nny = m - 1;
                pos = 3;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(ny >= m) { //남의 왼쪽
                int nnx = nx;
                int nny = 0;
                pos = 2;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else {
                if (isValid(nx, ny, pos)){
                    wallsVisited[pos][nx][ny] = true;
                    return new int[]{nx, ny, pos};
                }
            }
        } else if(pos == 2) { //남
            if(nx < 0) { //위의 하단
                int nnx = m - 1;
                int nny = ny;
                pos = 4;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(nx >= m) { //바닥
                int nnx = connected[pos] + 1;
                int nny = connected[1] + ny;
                pos = 5;

                if(nnx >= 0 && nny >= 0 && nnx < n && nny < n && isValid(nnx, nny, pos)) {
                    mapVisited[nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(ny < 0) { //서의 오른쪽
                int nnx = nx;
                int nny = m - 1;
                pos = 1;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(ny >= m) { //동의 왼쪽
                int nnx = nx;
                int nny = 0;
                pos = 0;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else {
                if (isValid(nx, ny, pos)){
                    wallsVisited[pos][nx][ny] = true;
                    return new int[]{nx, ny, pos};
                }
            }
        } else if(pos == 3) { //북
            if(nx < 0) { //위의 위쪽
                int nnx = 0;
                int nny = (m - 1) - ny;
                pos = 4;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(nx >= m) { //바닥
                int nnx = connected[pos] - 1;
                int nny = connected[1] + ((m - 1) - ny);
                pos = 5;

                if(nnx >= 0 && nny >= 0 && nnx < n && nny < n && isValid(nnx, nny, pos)) {
                    mapVisited[nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(ny < 0) { //동의 오른쪽
                int nnx = nx;
                int nny = m - 1;
                pos = 0;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(ny >= m) { //서의 왼쪽
                int nnx = nx;
                int nny = 0;
                pos = 1;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else {
                if (isValid(nx, ny, pos)){
                    wallsVisited[pos][nx][ny] = true;
                    return new int[]{nx, ny, pos};
                }
            }
        } else if(pos == 4) { //위
            if(nx < 0) { //북의 위쪽
                int nnx = 0;
                int nny = (m - 1) - ny;
                pos = 3;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(nx >= m) { //남의 위쪽
                int nnx = 0;
                int nny = ny;
                pos = 2;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(ny < 0) { //서의 위쪽
                int nnx = 0;
                int nny = nx;
                pos = 1;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else if(ny >= m) { //동의 위쪽
                int nnx = 0;
                int nny = (m - 1) - nx;
                pos = 0;

                if(isValid(nnx, nny, pos)) {
                    wallsVisited[pos][nnx][nny] = true;
                    return new int[]{nnx, nny, pos};
                }
            } else {
                if (isValid(nx, ny, pos)){
                    wallsVisited[pos][nx][ny] = true;
                    return new int[]{nx, ny, pos};
                }
            }
        } else if(pos == 5) { //바닥
            if(nx >= 0 && ny >= 0 && nx < n && ny < n && isValid(nx, ny, pos)) {
                mapVisited[nx][ny] = true;
                return new int[]{nx, ny, 5};
            }
        }
        return new int[]{-1, -1, -1};
    }

    static class TimeMachine {
        int x;
        int y;
        int pos;
        int turn;

        TimeMachine(int x, int y, int pos, int turn) {
            this.x = x;
            this.y = y;
            this.pos = pos;
            this.turn = turn;
        }
    }

    static class Phnomenon {
        int x;
        int y;
        int d;
        int v;
        int pos;

        Phnomenon(int x, int y, int d, int v, int pos) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.v = v;
            this.pos = pos;
        }
    }
}