class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[][] {{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}});
    }

    //우 하 좌 상
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static int length;
    static boolean[][] visited;
    static int[][] minPrice;
    static int[][] map;

    public int solution(int[][] board) {
        length = board.length;
        visited = new boolean[length][length]; // 방문체크 초기화
        minPrice = new int[length][length]; // 최솟값 배열 초기환
        for (int[] ints : minPrice) {
            for (int i = 0; i < length; i++) {
                ints[i] = Integer.MAX_VALUE;
            }
        }
        map = board;

        for(int i=0; i<=1; i++) {
            visited[0][0] = true;
            build(0, 0, i, 0);
            visited = new boolean[length][length];
        }

        return minPrice[length - 1][length - 1];
    }

    private void build(int x, int y, int curDir, int curPrice) {
        if (curPrice > minPrice[x][y])
            return;

        minPrice[x][y] = Math.min(minPrice[x][y], curPrice);

        if (x == length - 1 && y == length - 1) {
            return;
        }

        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + DX[i];
            ny = y + DY[i];

            if (nx >= length || ny >= length || nx < 0 || ny < 0)
                continue;

            if (visited[nx][ny] || map[nx][ny] == 1)
                continue;

            visited[nx][ny] = true;

            int extraPrice = 0;
            if (i == curDir) extraPrice = 100;
            else extraPrice = 600;

            build(nx, ny, i, curPrice + extraPrice);

            visited[nx][ny] = false;
        }
    }
}