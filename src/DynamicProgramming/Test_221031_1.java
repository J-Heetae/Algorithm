package DynamicProgramming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//프로그래머스, 가장 먼 노드, 다익스트라
public class Test_221031_1 {
    ArrayList<Integer>[] path; //각노드와 연결된 노드
    boolean[] v; //노드 방문 여부
    int[] d; //1부터 노드까지의 거리
    Queue<Integer> que = new LinkedList<>();
    int max = Integer.MIN_VALUE; //거리의 최댓값


    public int solution(int n, int[][] edge) {
        v = new boolean[n + 1];
        d = new int[n + 1];
        path = new ArrayList[n + 1];

        for(int i=1; i<=n; i++) {
            path[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];

            path[a].add(b);
            path[b].add(a);
        }

        for (Integer num : path[1]) {
            que.offer(num);
            d[num] = 1;
            v[num] = true;
        }

        for(int i=2; i<d.length; i++) {
            if(d[i] != 1) d[i] = Integer.MAX_VALUE;
        }

        v[1] = true;

        //다익스트라
        dijkstra(n);

        //가장 먼 노드의 간선 길이와 노드 찾기
        int answer = getAnswer(max);

        return answer;
    }

    private int getAnswer(int max) {
        int answer = 0;
        for (int num : d) {
            if (num == max) {
                answer++;
            }
        }
        return answer;
    }

    private void dijkstra(int n) {
        while (!que.isEmpty()) {
            Integer poll = que.poll();

            //이중배열을 ArrayList로 바꿔 연결된 노드만 확인
            //이중배열 사용시엔 노드끼리 연결여부 확인을 위해 모든 노드 확인했었음
            for (Integer num : path[poll]) {
                if (!v[num]) {
                    v[num] = true;
                    que.offer(num);
                    if(d[num] > d[poll] + 1) d[num] = d[poll] + 1;
                    if (d[num] > max) max = d[num];
                }
            }
        }
    }

    public static void main(String[] args) {
        Test_221031_1 test = new Test_221031_1();
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        int solution = test.solution(n, edge);

        System.out.println(solution);
    }
}