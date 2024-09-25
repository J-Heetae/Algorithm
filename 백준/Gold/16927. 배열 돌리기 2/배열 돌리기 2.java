import java.io.*;
import java.util.*;

class Main {

    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};

    static int N, M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        final int R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = Math.min(N, M);
        for (int i = 0; i < min / 2; i++) {
            int rotateCount = R % (((N - (2 * i)) + (M - (2 * i))) * 2 - 4);
            rotate(rotateCount, i);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void rotate(int rotateCount, int num) {
        while (rotateCount-- > 0) {
            int x = num;
            int y = num;
            int saved = arr[x][y];

            int dir = 0;
            while (dir < 4) {
                int nx = x + DX[dir];
                int ny = y + DY[dir];

                if (nx < num || nx >= N - num || ny < num || ny >= M - num) {
                    dir++;
                    continue;
                }
                arr[x][y] = arr[nx][ny];
                x = nx;
                y = ny;
            }
            arr[num + 1][num] = saved;
        }
    }
}
