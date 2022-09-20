package Recursive_Tree_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//경로탐색(인접리스트, ArrayList)
public class Test_220920_70 {
    static int n, m;
    static int[] dis, ch;
    static ArrayList<ArrayList<Integer>> graph;

    static void DFS(int v) {
        Queue<Integer> Q = new LinkedList<>();
        ch[1] = 1;
        dis[1] = 0;
        Q.offer(v);
        while(!Q.isEmpty()) {
            int cv = Q.poll();
            for(int nv : graph.get(cv)) {
                if(ch[nv] == 0) {
                    ch[nv] = 1;
                    Q.offer(nv);
                    dis[nv] = dis[cv] + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        ch = new int[n+1];
        dis = new int[n+1];
        graph = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
        }
        DFS(1);
        for(int i=2; i<=n; i++) {
            System.out.println( i + " : " + dis[i]);
        }
    }
}
