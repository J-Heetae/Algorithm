package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Collections;

//프로그래머스 섬 연결하기 크루스칼, 그리디
public class Test_221028_1 {
    static class Node implements Comparable<Node>{
        int a;
        int b;
        int cost;

        public Node(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if(this.cost == o.cost) return this.a - o.a;
            else return this.cost - o.cost;
        }
    }

    //부모 노드 찾는 함수
    public int getParent(int[] parent, int x) {
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent, parent[x]);
    }

    //두 부모 노드를 합치는 함수
    public void unionParent(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    //부모 노드가 같은지 확인
    public boolean findParent(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);

        return a == b;
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;
        int[] parent = new int[n];
        ArrayList<Node> arr = new ArrayList<>();

        for(int i=0; i<n; i++) {
            parent[i] = i;
        }

        for (int[] cost : costs) {
            arr.add(new Node(cost[0], cost[1], cost[2]));
        }

        Collections.sort(arr);

        for (Node node : arr) {
            if (!findParent(parent, node.a, node.b)) {
                answer += node.cost;
                unionParent(parent, node.a, node.b);
            }
        }

        return answer;
    }
}
