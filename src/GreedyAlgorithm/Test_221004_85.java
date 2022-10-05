package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

//다익스트라 알고리즘
public class Test_221004_85 {
    static class Edge implements Comparable<Edge> {
        public int vex;
        public int cost;

        public Edge(int vex, int cost) {
            this.vex = vex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
        int[] dis = new int[n + 1];

        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<Edge>());
            dis[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<m; i++) {
            int start = sc.nextInt();
            int vex = sc.nextInt();
            int cost = sc.nextInt();
            graph.get(start).add(new Edge(vex, cost));
        }

        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        dis[1] = 0;
        pQ.offer(new Edge(1, 0));

        while(!pQ.isEmpty()) {
            Edge tmp = pQ.poll();
            int now = tmp.vex;
            int nowCost = tmp.cost;

            if(dis[now] < nowCost) continue;

            for(Edge o : graph.get(now)) {
                if(dis[o.vex] > (nowCost + o.cost)) {
                    dis[o.vex] = nowCost + o.cost;
                    pQ.offer(new Edge(o.vex, nowCost + o.cost));
                }
            }
        }



    }
}
