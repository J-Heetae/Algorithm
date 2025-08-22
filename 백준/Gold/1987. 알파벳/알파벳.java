import java.util.*;
import java.io.*;

class Main {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};

    static int R, C, maxPass = 1;
    static char[][] map;
    static boolean[] alphabet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0; i<R; i++) {
            String read = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = read.charAt(j);
            }
        }

        alphabet = new boolean[26];
        alphabet[map[0][0] - 'A'] = true;
        dfs(1, 0, 0);

        System.out.print(maxPass);
    }

    static void dfs(int depth, int x, int y) {
        maxPass = Math.max(maxPass, depth);

        for(int i=0; i<4; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];

            if(nx < 0 || nx >= R || ny < 0 || ny >= C || alphabet[map[nx][ny] - 'A']) {
                continue;
            }
            alphabet[map[nx][ny] - 'A'] = true;
            dfs(depth + 1, nx, ny);
            alphabet[map[nx][ny] - 'A'] = false;
        }
    }
}