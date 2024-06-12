import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    boolean[] light;
    boolean[] visited;
    ArrayList<Integer>[] roads;
    Queue<Integer> q = new LinkedList<>();

    public int solution(int n, int[][] lighthouse) {
        if(n == 2)
            return 1;

        light = new boolean[n + 1];
        visited = new boolean[n + 1];
        roads = new ArrayList[n + 1];

        Arrays.fill(light, true);

        for (int i = 1; i <= n; i++) {
            roads[i] = new ArrayList<>();
        }

        for (int[] o : lighthouse) {
            roads[o[0]].add(o[1]);
            roads[o[1]].add(o[0]);
        }

        for (int i = 1; i <= n; i++) {
            if (roads[i].size() == 1) {
                visited[i] = true;
                light[i] = false;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                find(i);
                break;
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (light[i])
                answer++;
        }
        return answer;
    }

    boolean find(int cur) {
        int black = 0;
        for (Integer next : roads[cur]) {
            if (visited[next]) {
                if (!light[next]) {
                    black++;
                }
            } else {
                visited[next] = true;
                if (!find(next)) {
                    black++;
                }
            }
        }
        if (black == 0)
            light[cur] = false;
        return light[cur];
    }
}