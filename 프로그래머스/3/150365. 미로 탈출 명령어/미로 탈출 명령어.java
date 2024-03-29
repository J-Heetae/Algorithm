class Solution {
    final int[] DX = {1, 0, 0, -1};
    final int[] DY = {0, -1, 1, 0};
    final char[] chars = {'d', 'l', 'r', 'u'};

    int[] end;
    int k, n, m;
    StringBuilder route = new StringBuilder();
    String answer;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        end = new int[] {r, c};

        if (calMinDistance(x, y) > k || (k - calMinDistance(x, y)) % 2 != 0)
            return "impossible";

        escape(x, y, 0);
        return answer;
        // return answer == null ? "impossible" : answer;
    }

    int calMinDistance(int x, int y) {
        return Math.abs(x - end[0]) + Math.abs(y - end[1]);
    }

    void escape(int x, int y, int curDistance) {
        if (answer != null || k - curDistance < calMinDistance(x, y))
            return;

        if (curDistance == k) {
            answer = route.toString();
            return;
        }

        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + DX[i];
            ny = y + DY[i];

            if (nx < 1 || nx > n || ny < 1 || ny > m)
                continue;

            route.append(chars[i]);
            escape(nx, ny, curDistance + 1);
            route.delete(curDistance, curDistance + 1);
        }
    }
}