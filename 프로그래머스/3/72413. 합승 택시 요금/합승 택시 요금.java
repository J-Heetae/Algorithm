import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 인접 행렬로 변환
        int[][] graph = new int[n + 1][n + 1];
        for (int[] fare : fares) {
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }

        // 최소비용을 저장할 배열
        int[][] minCost = new int[n + 1][n + 1];

        // 다익스트라 알고리즘을 사용해 각 노드에서 모든 노드로의 최소비용 계산
        for (int i = 1; i <= n; i++) {
            dijkstra(i, n, graph, minCost);
        }

        // 최소 합승 비용 계산
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (minCost[s][i] != Integer.MAX_VALUE && minCost[i][a] != Integer.MAX_VALUE && minCost[i][b] != Integer.MAX_VALUE) {
                answer = Math.min(answer, minCost[s][i] + minCost[i][a] + minCost[i][b]);
            }
        }

        return answer;
    }

    private void dijkstra(int start, int n, int[][] graph, int[][] minCost) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(minCost[start], Integer.MAX_VALUE);
        minCost[start][start] = 0;
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];

            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            for (int nextNode = 1; nextNode <= n; nextNode++) {
                if (graph[currentNode][nextNode] != 0 && !visited[nextNode]) {
                    if (minCost[start][nextNode] > minCost[start][currentNode] + graph[currentNode][nextNode]) {
                        minCost[start][nextNode] = minCost[start][currentNode] + graph[currentNode][nextNode];
                        pq.offer(new int[]{nextNode, minCost[start][nextNode]});
                    }
                }
            }
        }
    }
}
