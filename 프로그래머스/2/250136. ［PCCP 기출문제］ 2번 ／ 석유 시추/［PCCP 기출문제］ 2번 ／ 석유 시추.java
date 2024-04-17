import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Queue;

class Solution {
    final int[] DX = {0, 1, 0, -1};
    final int[] DY = {1, 0, -1, 0};

    int maxOil = 0;
    int row, col;
    int oilIdx = 0;
    int[][] land;
    int[][] oilNumber;
    HashMap<Integer, Integer> oilMap = new HashMap<>();

    public int solution(int[][] land) {
        this.land = land;
        row = land.length;
        col = land[0].length;
        oilNumber = new int[row][col];

        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(oilNumber[i][j] == 0 && land[i][j] == 1) {
                    oilIdx++;
                    oilMap.put(oilIdx, 0);
                    numberingOil(oilIdx, i, j);
                }
            }
        }

        for(int i=0; i<col; i++) {
            HashSet<Integer> oilSet = new HashSet<>();
            int curOil = 0;
            for(int j=0; j<row; j++) {
                if(oilNumber[j][i] == 0)
                    continue;

                if(oilSet.add(oilNumber[j][i])) {
                    curOil += oilMap.get(oilNumber[j][i]);
                }
            }
            maxOil = Math.max(maxOil, curOil);
        }
        return maxOil;
    }

    void numberingOil(int oilIdx, int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        oilNumber[x][y] = oilIdx;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];
            oilMap.put(oilIdx, oilMap.get(oilIdx) + 1);

            for (int i = 0; i < 4; i++) {
                int nx = curX + DX[i];
                int ny = curY + DY[i];

                if(nx < 0 || nx >= row || ny < 0 || ny >= col || oilNumber[nx][ny] != 0 || land[nx][ny] != 1)
                    continue;

                oilNumber[nx][ny] = oilIdx;
                queue.offer(new int[]{nx, ny});
            }
        }
    }
}
