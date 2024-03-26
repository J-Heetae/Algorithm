class Solution {
    final int SHEEP = 0, WOLF = 1;
    int maxSheepCount = 1;
    int[] info;
    int[][] edges;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        this.edges = edges;

        dfs(0, 0, 0, new boolean[info.length]);

        return maxSheepCount;
    }

    void dfs(int idx, int sheepCount, int wolfCount, boolean[] visited) {
        visited[idx] = true;

        if(info[idx] == SHEEP) {
            sheepCount++;
            maxSheepCount = Math.max(maxSheepCount, sheepCount);
        } else {
            wolfCount++;
        }

        if (sheepCount <= wolfCount)
            return;

        for (int[] edge : edges) {
            if(visited[edge[0]] && !visited[edge[1]])
                dfs(edge[1], sheepCount, wolfCount, visited.clone());
        }
    }
}