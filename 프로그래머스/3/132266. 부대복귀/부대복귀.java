import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        ArrayList<ArrayList<Integer>> roadList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            roadList.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            roadList.get(road[0]).add(road[1]);
            roadList.get(road[1]).add(road[0]);
        }

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {destination, 0});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curArea = cur[0];
            int curDistance = cur[1];

            if (distance[curArea] < curDistance)
                continue;

            distance[curArea] = curDistance;

            for (int next : roadList.get(curArea)) {
                if (distance[next] < curDistance + 1)
                    continue;
                que.offer(new int[] {next, curDistance + 1});
            }
        }

        int[] result = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            int curDistance = distance[sources[i]];
            result[i] = (curDistance == Integer.MAX_VALUE) ? -1 : curDistance;
        }
        return result;
    }
}