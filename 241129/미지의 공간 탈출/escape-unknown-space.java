import java.util.*;

public class Main {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int n, m, f;
    static int[] connected = new int[4];
    static int[][] map;
    static int[][][] walls;
    static Phnomenon[] pArr;
    static boolean[][][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        f = sc.nextInt();

        Arrays.fill(connected, -1);
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 3) {
                    updateConnected(i, j);
                }
            }
        }

        // 동서남북위
        walls = new int[5][m][m];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    walls[i][j][k] = sc.nextInt();
                }
            }
        }

        int[] start = findStart();
        pArr = new Phnomenon[f];
        for (int i = 0; i < f; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();
            int v = sc.nextInt();
            pArr[i] = new Phnomenon(x, y, d, v, 5);
            map[x][y] = 5;
        }

        int result = simulation(start[0], start[1]);
        System.out.println(result);
    }

    static void updateConnected(int i, int j) {
        connected[0] = Math.max(connected[0], j); // 동
        connected[1] = (connected[1] == -1) ? j : Math.min(connected[1], j); // 서
        connected[2] = Math.max(connected[2], i); // 남
        connected[3] = (connected[3] == -1) ? i : Math.min(connected[3], i); // 북
    }

    static int[] findStart() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (walls[4][i][j] == 2) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1}; // 시작점이 없는 경우
    }

    static int simulation(int x, int y) {
        int turn = 0;
        Queue<TimeMachine> queue = new LinkedList<>();
        visited = new boolean[6][Math.max(n, m)][Math.max(n, m)];
        boolean[] pStop = new boolean[f];

        queue.offer(new TimeMachine(x, y, 4, turn));
        visited[4][x][y] = true;

        while (!queue.isEmpty()) {
            turn++;

            // 시간 이상 현상 처리
            for (int i = 0; i < f; i++) {
                if (!pStop[i] && (turn % pArr[i].v == 0)) {
                    Phnomenon curr = pArr[i];
                    int[] next = move(curr.x, curr.y, curr.d, curr.pos, 1);
                    if (next[0] != -1) {
                        curr.x = next[0];
                        curr.y = next[1];
                        curr.pos = next[2];
                        map[curr.x][curr.y] = 5;
                    } else {
                        pStop[i] = true;
                    }
                }
            }

            int size = queue.size();
            while (size-- > 0) {
                TimeMachine curr = queue.poll();

                if (curr.pos == 5 && map[curr.x][curr.y] == 4) {
                    return curr.turn; // 도착
                }

                for (int d = 0; d < 4; d++) {
                    int[] next = move(curr.x, curr.y, d, curr.pos, 0);
                    if (next[0] != -1) {
                        queue.offer(new TimeMachine(next[0], next[1], next[2], turn));
                    }
                }
            }
        }
        return -1;
    }

    static boolean isValid(int x, int y, int pos, int type) {
        if (pos <= 4) {
            return x >= 0 && y >= 0 && x < m && y < m && walls[pos][x][y] != 1 && !visited[pos][x][y];
        } else {
            return x >= 0 && y >= 0 && x < n && y < n && (type == 1 ? map[x][y] == 0 || map[x][y] == 5 || map[x][y] == 2 : map[x][y] == 0 || map[x][y] == 4) && !visited[pos][x][y];
        }
    }

    static int[] move(int x, int y, int d, int pos, int type) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (isValid(nx, ny, pos, type)) {
            visited[pos][nx][ny] = true;
            return new int[]{nx, ny, pos};
        }

        if (pos == 0 && nx < 0) return adjustPosition(ny, m - 1, 4); // 동
        if (pos == 1 && nx < 0) return adjustPosition(ny, 0, 4); // 서
        if (pos == 2 && nx >= m) return adjustPosition(connected[2] + 1, connected[1] + ny, 5); // 남
        if (pos == 3 && nx >= m) return adjustPosition(connected[3] - 1, connected[1] + (m - 1 - ny), 5); // 북
        return new int[]{-1, -1, -1};
    }

    static int[] adjustPosition(int x, int y, int newPos) {
        return isValid(x, y, newPos, 0) ? new int[]{x, y, newPos} : new int[]{-1, -1, -1};
    }

    static class TimeMachine {
        int x, y, pos, turn;

        TimeMachine(int x, int y, int pos, int turn) {
            this.x = x;
            this.y = y;
            this.pos = pos;
            this.turn = turn;
        }
    }

    static class Phnomenon {
        int x, y, d, v, pos;

        Phnomenon(int x, int y, int d, int v, int pos) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.v = v;
            this.pos = pos;
        }
    }
}
