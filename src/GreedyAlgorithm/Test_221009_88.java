package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//원더랜드(최소스패닝트리) 크루스칼
public class Test_221009_88 {
    static int[] unf;

    private static class Edge implements Comparable<Edge> {
        int city1;
        int city2;
        int cost;

        public Edge(int city1, int city2, int cost) {
            this.city1 = city1;
            this.city2 = city2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static int Find(int v) {
        if(v == unf[v]) return unf[v];
        else return unf[v] = Find(unf[v]);
    }

    private static void Union(int a, int b) {
        int fa = Find(a);
        int fb = Find(b);
        if(fa != fb) unf[fa] = fb;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int answer = 0;
        int city = sc.nextInt();
        int street = sc.nextInt();
        unf = new int[city + 1];

        for(int i=1; i<=city; i++) {
            unf[i] = i;
        }

        ArrayList<Edge> list = new ArrayList<>();

        for(int i=0; i<street; i++) {
            int city1 = sc.nextInt();
            int city2 = sc.nextInt();
            int cost = sc.nextInt();

            list.add(new Edge(city1, city2, cost));
        }

        Collections.sort(list);

        int cnt = 0;

        for (Edge edge : list) {
            int a = edge.city1;
            int b = edge.city2;
            if(Find(a) != Find(b)) {
                Union(a, b);
                answer += edge.cost;
                cnt++;
            }
            if (cnt == city - 1) break;
        }

        System.out.println(answer);
    }
}
