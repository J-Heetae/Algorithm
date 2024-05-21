import java.util.ArrayList;
import java.util.List;

class Solution {
    List<Integer>[] children;
    int[] sales;
    int[][] dp;

    public int solution(int[] sales, int[][] links) {
        int n = sales.length;
        this.sales = sales;
        children = new ArrayList[n + 1];

        for(int i=1; i<=n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int[] link : links) {
            children[link[0]].add(link[1]);
        }

        dp = new int[n + 1][2];

        dfs(1);

        return Math.min(dp[1][0], dp[1][1]);
    }

    void dfs(int node) {
        int sumChildSales = 0;
        boolean childAttended = false;
        boolean isLeaf = true;
        int minDiff = Integer.MAX_VALUE;

        for (Integer child : children[node]) {
            isLeaf = false;

            dfs(child);

            sumChildSales += Math.min(dp[child][0], dp[child][1]);

            if(dp[child][0] > dp[child][1]) {
                childAttended = true; //자식이 참석하는게 더 이득
            } else { //자식이 참석하지 않는게 더 이득일 때
                minDiff = Math.min(minDiff, dp[child][1] - dp[child][0]);
            }
        }
        //현재 노드가 참석하는 경우
        dp[node][1] = sales[node - 1] + sumChildSales;

        if(!isLeaf) {
            //현재 노드가 참석하지 않는 경우
            if (childAttended) { //자식이 참여하는게 더 이득
                dp[node][0] += sumChildSales;
            } else { //자식이 참여하지 않는게 이득일때
                //자식 중 하나는 꼭 참여해야됨
                dp[node][0] = sumChildSales + minDiff;
            }
        }
    }
}