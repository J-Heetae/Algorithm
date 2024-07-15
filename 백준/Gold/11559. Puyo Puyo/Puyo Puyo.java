import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};
    static final int ROWS = 12;
    static final int COLS = 6;

    public static void main(String[] args) throws IOException {
        char[][] field = new char[12][6];

        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            for (int j = 0; j < 6; j++) {
                field[i][j] = line.charAt(j);
            }
        }

        int count = 0;
        while (true) {
            boolean boom = false;
            boolean[][] visited = new boolean[12][6];

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (field[i][j] != '.' && !visited[i][j]) {
                        if (bfs(field, visited, i, j)) {
                            boom = true;
                        }
                    }
                }
            }

            if (!boom)
                break;

            gravity(field);
            count++;
        }
        System.out.println(count);
    }

    private static boolean bfs(char[][] field, boolean[][] visited, int i, int j) {
        char c = field[i][j];
        Queue<int[]> queue = new LinkedList<>();
        ArrayList<int[]> remove = new ArrayList<>();

        queue.add(new int[] {i, j});
        remove.add(new int[] {i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = pos[0] + DX[dir];
                int ny = pos[1] + DY[dir];

                if (nx >= 0 && ny >= 0 && nx < ROWS && ny < COLS && !visited[nx][ny] && field[nx][ny] == c) {
                    queue.add(new int[] {nx, ny});
                    remove.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        if (remove.size() >= 4) {
            for (int[] r : remove) {
                field[r[0]][r[1]] = '.';
            }
            return true;
        }
        return false;
    }

    private static void gravity(char[][] field) {
        for (int j = 0; j < COLS; j++) {
            int emptyRow = ROWS - 1;
            for (int i = ROWS - 1; i >= 0; i--) {
                if (field[i][j] != '.') {
                    if (i != emptyRow) {
                        field[emptyRow][j] = field[i][j];
                        field[i][j] = '.';
                    }
                    emptyRow--;
                }
            }
        }
    }
}