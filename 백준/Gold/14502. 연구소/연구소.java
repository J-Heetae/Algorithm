import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, -1, 0, 1};

    static int n, m;
    static int[][] originMap;
    static int maxSafe = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] read;

        read = br.readLine().split(" ");
        n = Integer.parseInt(read[0]);
        m = Integer.parseInt(read[1]);
        originMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            read = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                originMap[i][j] = Integer.parseInt(read[j]);
            }
        }
        buildThreeWall(0);

        System.out.println(maxSafe);
    }

    static void buildThreeWall(int wall) {
        if (wall == 3) { //벽을 모두 세운 경우
            spreadVirus();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (originMap[i][j] == 0) {
                    originMap[i][j] = 1;
                    buildThreeWall(wall + 1);
                    originMap[i][j] = 0;
                }
            }
        }
    }

    static int[][] copyMap() {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = originMap[i][j];
            }
        }
        return copy;
    }

    static void spreadVirus() {
        int[][] copy = copyMap();

        Queue<int[]> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 2) {
                    que.offer(new int[]{i, j});
                }
            }
        }

        while (!que.isEmpty()) {
            int[] poll = que.poll();
            int x = poll[0];
            int y = poll[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + DX[i];
                int ny = y + DY[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (copy[nx][ny] == 0) {
                    copy[nx][ny] = 2;
                    que.offer(new int[]{nx, ny});
                }
            }
        }

        searchSafeArea(copy);
    }

    static void searchSafeArea(int[][] map) {
        int currSafe = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    currSafe++;
                }
            }
        }

        maxSafe = Math.max(maxSafe, currSafe);
    }
}