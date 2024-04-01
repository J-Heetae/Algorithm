class Solution {
    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int[][][] map = new int[m + 1][n + 1][2];
        map[1][1][0] = map[1][1][1] = 1;
        for (int x = 1; x <= m; x++) {
            for (int y = 1; y <= n; y++) {
                switch (cityMap[x - 1][y - 1]) {
                    case 0:
                        map[x][y][0] += (map[x][y - 1][0] + map[x - 1][y][1]) % MOD;
                        map[x][y][1] += (map[x][y - 1][0] + map[x - 1][y][1]) % MOD;
                        break;
                    case 1:
                        map[x][y][0] = map[x][y][1] = 0;
                        break;
                    case 2:
                        map[x][y][0] += map[x][y - 1][0];
                        map[x][y][1] += map[x - 1][y][1];
                }
            }
        }
        return map[m][n][0];
    }
}