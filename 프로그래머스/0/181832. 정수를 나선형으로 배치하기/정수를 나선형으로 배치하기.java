class Solution {
    public int[][] solution(int n) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int[][] answer = new int[n][n];
        int dir = 0, num = 1, x = 0, y = -1;
        while (num  <= n * n) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n || answer[nx][ny] != 0) {
                dir = (dir + 1) % 4;
                continue;
            }
            answer[nx][ny] = num++;
            x = nx;
            y = ny;
        }
        return answer;
    }
}