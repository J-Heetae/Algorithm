import java.util.ArrayList;

class Solution {
    int minDis = Integer.MAX_VALUE;
    int iX, iY;
    ArrayList<int[]>[][] map = new ArrayList[101][101];
    boolean[][] visited = new boolean[101][101];
    boolean[][] remove = new boolean[101][101];

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        iX = itemX * 2;
        iY = itemY * 2;

        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int[] rec : rectangle) {
            for (int i = rec[0] * 2 + 1; i < rec[2] * 2; i++) {
                for (int j = rec[1] * 2 + 1; j < rec[3] * 2; j++) {
                    remove[i][j] = true;
                }
            }
        }

        for (int[] rec : rectangle) {
            rec[0] *= 2;
            rec[1] *= 2;
            rec[2] *= 2;
            rec[3] *= 2;

            for (int i = rec[0] + 1; i <= rec[2]; i++) {
                if (!remove[i][rec[1]] && !remove[i - 1][rec[1]]) {
                    map[i - 1][rec[1]].add(new int[] {i, rec[1]});
                    map[i][rec[1]].add(new int[] {i - 1, rec[1]});
                }
                if (!remove[i][rec[3]] && !remove[i - 1][rec[3]]) {
                    map[i - 1][rec[3]].add(new int[] {i, rec[3]});
                    map[i][rec[3]].add(new int[] {i - 1, rec[3]});
                }
            }

            for (int i = rec[1] + 1; i <= rec[3]; i++) {
                if (!remove[rec[0]][i] && !remove[rec[0]][i - 1]) {
                    map[rec[0]][i].add(new int[] {rec[0], i - 1});
                    map[rec[0]][i - 1].add(new int[] {rec[0], i});
                }
                if (!remove[rec[2]][i] && !remove[rec[2]][i - 1]) {
                    map[rec[2]][i].add(new int[] {rec[2], i - 1});
                    map[rec[2]][i - 1].add(new int[] {rec[2], i});
                }
            }
        }

        visited[characterX * 2][characterY * 2] = true;
        find(characterX * 2, characterY * 2, 0);

        return minDis / 2;
    }

    void find(int x, int y, int dis) {
        if (x == iX && y == iY) {
            minDis = Math.min(minDis, dis);
            return;
        }

        for (int[] next : map[x][y]) {
            int nx = next[0], ny = next[1];

            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                find(nx, ny, dis + 1);
                visited[nx][ny] = false;
            }
        }
    }
}