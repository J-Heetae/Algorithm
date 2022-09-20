package Recursive_Tree_Graph;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

//경로탐색(인접리스트, ArrayList)
public class Test_220920_69 {
    static int n, m, answer = 0;
    static ArrayList<ArrayList<Integer>> graph ;
    static int[] ch;

    static void DFS(int v) {
        if(v == n) answer++;
        else {
            for(int nv : graph.get(v)) {
                if(ch[nv] == 0) {
                    ch[nv] = 1;
                    DFS(nv);
                    ch[nv] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        ch = new int[n+1];
        graph = new ArrayList<ArrayList<Integer>>();

        for(int i=1; i<=n; i++) graph.add(new ArrayList<>());

        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
        }

        ch[1] = 1;
        DFS(1);
        System.out.println(answer);
    }
}
