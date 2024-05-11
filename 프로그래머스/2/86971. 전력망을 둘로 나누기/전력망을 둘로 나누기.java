import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    int n;
    ArrayList<ArrayList<Integer>> wireList;
    boolean[] visited;

    public int solution(int n, int[][] wires) {
        this.n = n;
        wireList = new ArrayList<>();
        visited = new boolean[n + 1];

        for(int i=0; i<=n; i++) {
            wireList.add(new ArrayList<>());
        }

        for (int[] wire : wires) {
            wireList.get(wire[0]).add(wire[1]);
            wireList.get(wire[1]).add(wire[0]);
        }

        int minDiff = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            wireList.get(wire[0]).remove((Integer) wire[1]);
            wireList.get(wire[1]).remove((Integer) wire[0]);

            int count = getCount(wire[0]);
            minDiff = Math.min(minDiff, Math.abs(n - count * 2));

            wireList.get(wire[0]).add(wire[1]);
            wireList.get(wire[1]).add(wire[0]);

            Arrays.fill(visited, false);
        }
        return minDiff;
    }

    int getCount(int cur) {
        visited[cur] = true;
        int count = 1;
        for (Integer next : wireList.get(cur)) {
            if(!visited[next]) {
                count += getCount(next);
            }
        }
        return count;
    }
}