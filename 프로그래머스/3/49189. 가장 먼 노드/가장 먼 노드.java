import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] edge) {
        ArrayList<ArrayList<Integer>> edgeList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            edgeList.add(new ArrayList<>());
        }

        for (int[] cur : edge) {
            edgeList.get(cur[0]).add(cur[1]);
            edgeList.get(cur[1]).add(cur[0]);
        }

        boolean[] isVisited = new boolean[n + 1];
        int maxLength = Integer.MIN_VALUE;
        int answer = 0;

        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(1, 0));
        isVisited[1] = true;
        while (!que.isEmpty()) {
            Node cur = que.poll();

            for (int next : edgeList.get(cur.value)) {
                if (isVisited[next]) continue;
                isVisited[next] = true;
                que.offer(new Node(next, cur.passed + 1));
            }

            if(cur.passed > maxLength) {
                maxLength = cur.passed;
                answer = 1;
            } else if(cur.passed == maxLength) {
                answer++;
            }
        }
        return answer;
    }

    private class Node {
        int value, passed;

        public Node(int value, int passed) {
            this.value = value;
            this.passed = passed;
        }
    }
}