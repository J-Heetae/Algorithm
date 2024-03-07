import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(7, 3, 4, 1, new int[][] {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}});
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] loadArr = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                if (i != j)
                    loadArr[i][j] = -1;

        for (int[] fare : fares) {
            loadArr[fare[0]][fare[1]] = fare[2];
            loadArr[fare[1]][fare[0]] = fare[2];
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(s);
        list.add(a);
        list.add(b);

        int[][] dijkstra = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 0; j <= n; j++)
                if (i != j)
                    dijkstra[i][j] = -1;

        boolean[] visited;
        PriorityQueue<int[]> que;
        for (Integer i : list) {
            visited = new boolean[n + 1];
            que = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

            for (int j = 1; j <= n; j++)
                if (loadArr[i][j] > 0)
                    que.offer(new int[] {j, loadArr[i][j]});

            visited[i] = true;
            while (!que.isEmpty()) {
                int[] poll = que.poll();
                int curPoint = poll[0];
                int curPrice = poll[1];

                if (visited[curPoint])
                    continue;
                visited[curPoint] = true;
                dijkstra[i][curPoint] = curPrice;

                for (int j = 1; j <= n; j++)
                    if (loadArr[curPoint][j] > 0 && !visited[j])
                        que.offer(new int[] {j, loadArr[curPoint][j] + curPrice});
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (i == s) {
                min = Math.min(min, (dijkstra[i][a] + dijkstra[i][b]));
            } else if (i == a) {
                min = Math.min(min, (dijkstra[s][a] + dijkstra[a][b]));
            } else if (i == b) {
                min = Math.min(min, (dijkstra[s][b] + dijkstra[b][a]));
            } else {
                int tmp = dijkstra[s][i];
                if (dijkstra[a][i] > 0 && dijkstra[b][i] > 0) {
                    tmp += dijkstra[a][i] + dijkstra[b][i];
                    min = Math.min(min, tmp);
                }
            }
        }
        return min;
    }
}