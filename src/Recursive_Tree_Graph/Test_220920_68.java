package Recursive_Tree_Graph;

import java.util.Scanner;

//경로탐색(DFS)
public class Test_220920_68 {
    static int n, m, answer = 0;
    static int[][] graph;
    static int[] ch;

    static void DFS(int v) {
        if(v == n) answer++;
        else {
            for(int i=1; i<=n; i++) {
                if (graph[v][i] == 1 && ch[i] == 0) {
                    ch[i]=1;
                    DFS(i);
                    ch[i]=0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        ch = new int[n+1];
        graph = new int[n+1][n+1];

        for(int i=0; i<m; i++) {
            int tmp1 = sc.nextInt();
            int tmp2 = sc.nextInt();
            graph[tmp1][tmp2] = 1;
        }

        ch[1] = 1;
        DFS(1);
        System.out.println(answer);
    }
}
