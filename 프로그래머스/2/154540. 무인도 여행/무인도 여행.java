import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new String[] {"X591X", "X1X5X", "X231X", "1XXX1"});
    }

    public int[] solution(String[] maps) {
        int row = maps.length;
        int col = maps[0].length();

        final int[] DX = {0, -1, 0, 1};
        final int[] DY = {1, 0, -1, 0};
        boolean[][] visited = new boolean[row][col];

        ArrayList<Integer> days = new ArrayList<>();
        Queue<int[]> que = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] || maps[i].charAt(j) == 'X')
                    continue;
                visited[i][j] = true;

                int value = 0;
                que.offer(new int[] {i, j});
                while (!que.isEmpty()) {
                    int[] cur = que.poll();
                    value += Character.getNumericValue(maps[cur[0]].charAt(cur[1]));

                    int nx, ny;
                    for (int k = 0; k < 4; k++) {
                        nx = cur[0] + DX[k];
                        ny = cur[1] + DY[k];

                        if (nx >= row || ny >= col || nx < 0 || ny < 0 || visited[nx][ny]
                            || maps[nx].charAt(ny) == 'X') {
                            continue;
                        }
                        visited[nx][ny] = true;
                        que.offer(new int[] {nx, ny});
                    }
                }
                days.add(value);
            }
        }

        if(days.size() == 0) {
            return new int[] {-1};
        }

        Collections.sort(days);
        int[] answer = new int[days.size()];
        for(int i=0; i<answer.length; i++)
            answer[i] = days.get(i);
        return answer;
    }
}