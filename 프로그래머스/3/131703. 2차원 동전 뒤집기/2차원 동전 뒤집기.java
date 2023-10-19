class Solution {

    static int row, col;
    static int flip = Integer.MAX_VALUE;

    static int[][] targetMap;

    public int solution(int[][] beginning, int[][] target) {
        row = beginning.length;
        col = beginning[0].length;

        targetMap = target;

        circuitRow(beginning, 0, 0);

        if(flip == Integer.MAX_VALUE) {
            return -1;
        }
        return flip;
    }

    void circuitRow(int[][] map, int idx, int cnt) {
        if(idx == row) {
            circuitCol(map, cnt);
            return;
        }

        circuitRow(map, idx + 1, cnt);

        int[][] copy = copyArr(map);
        for(int j=0; j<col; j++) {
            copy[idx][j] = (copy[idx][j] == 1) ? 0 : 1;
        }
        circuitRow(copy, idx + 1, cnt + 1);
    }

    void circuitCol(int[][] map, int cnt) {
        int curflip = 0;
        for(int i=0; i<col; i++) {

            int same = 0;
            for(int j=0; j<row; j++) {
                if(map[j][i] == targetMap[j][i]) {
                    same++;
                }
            }
            if(same == 0) {
                curflip++;
            } else if (same != row) {
                return;
            }
        }
        flip = Math.min(flip, curflip + cnt);
    }
    
    int[][] copyArr(int[][] origin) {
        int row = origin.length;
        int col = origin[0].length;

        int[][] copy = new int[row][col];

        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                copy[i][j] = origin[i][j];
            }
        }
        return copy;
    }
}