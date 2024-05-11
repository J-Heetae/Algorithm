class Solution {

    int n;
    int minDiff = Integer.MAX_VALUE;
    boolean[][] wireArr;
    boolean[] visited;

    public int solution(int n, int[][] wires) {
        this.n = n;
        wireArr = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int[] wire : wires) {
            int tower1 = wire[0];
            int tower2 = wire[1];
            wireArr[tower1][tower2] = true;
            wireArr[tower2][tower1] = true;
        }

        int minDiff = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            int tower1 = wire[0];
            int tower2 = wire[1];
            wireArr[tower1][tower2] = false;
            wireArr[tower2][tower1] = false;
            visited[tower1] = true;
            visited[tower2] = true;
            int aCount = getCount(tower1);
            int bCount = getCount(tower2);
            visited[tower1] = false;
            visited[tower2] = false;
            minDiff = Math.min(minDiff, Math.abs(aCount - bCount));
            wireArr[tower1][tower2] = true;
            wireArr[tower2][tower1] = true;
        }
        return minDiff;
    }

    int getCount(int cur) {
        int count = 1;
        for(int i=1; i<=n; i++) {
            if (wireArr[cur][i] && !visited[i]) {
                visited[i] = true;
                count += getCount(i);
                visited[i] = false;
            }
        }
        return count;
    }
}