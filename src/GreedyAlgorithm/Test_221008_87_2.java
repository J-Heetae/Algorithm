package GreedyAlgorithm;

import java.util.Scanner;

//친구인가?(Union & Find)
public class Test_221008_87_2 {
    static int[] unf;

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
        String answer = "NO";
        int stdNum = sc.nextInt();
        int n = sc.nextInt();

        unf = new int[stdNum + 1];
        for(int i=1; i<=stdNum; i++) {
            unf[i] = i;
        }

        for(int i=0; i<n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            Union(a, b);
        }

        int std1 = sc.nextInt();
        int std2 = sc.nextInt();
        if(Find(std1) == Find(std2)) answer = "YES";

        System.out.print(answer);
    }
}
