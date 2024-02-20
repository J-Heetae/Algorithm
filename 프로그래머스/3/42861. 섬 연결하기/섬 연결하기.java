import java.util.Arrays;
import java.util.Comparator;

class Solution {

    static int[] island;

    public int solution(int n, int[][] costs) {
        island = new int[n];
        for (int i = 0; i < n; i++)
            island[i] = i;

        Arrays.sort(costs, Comparator.comparingInt(a -> a[2]));

        int totalCost = 0;
        for (int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];

            if (find(a) != find(b)) {
                union(a, b);
                totalCost += cost[2];
            }
        }
        return totalCost;
    }

    private void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA == parentB)
            return;

        if (parentA > parentB) {
            island[parentA] = parentB;
        } else {
            island[parentB] = parentA;
        }
    }

    private int find(int num) {
        if (island[num] == num)
            return num;
        return find(island[num]);
    }
}