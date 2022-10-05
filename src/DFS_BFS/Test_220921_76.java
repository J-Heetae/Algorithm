package DFS_BFS;


import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

//동전 교환
public class Test_220921_76 {
    static int[] combi, sequen, ck2;
    static int[][] ck;
    static int len, number;
    static boolean flag = false;

    private static int combination(int n, int r) {
        if(ck[n][r] > 0) return ck[n][r];
        if(n == r || r == 0) return 1;
        else return ck[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
    }

    private static void DFS(int L, int sum) {
        if(flag) return;
        if(L == len) {
            if (sum == number) {
                for(int s : sequen) {
                    System.out.print(s + " ");
                }
                flag = true;
            }
        } else {
            for(int i=1; i<=len; i++) {
                if(ck2[i] == 0) {
                    ck2[i] = 1;
                    sequen[L] = i;
                    DFS(L+1, sum + (sequen[L] * combi[L]));
                    ck2[i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        len = sc.nextInt();
        number = sc.nextInt();
        combi = new int[len];
        sequen = new int[len];
        ck = new int[len+1][len+1];
        ck2 = new int[len+1];


        for(int i=0; i<combi.length; i++) {
            combi[i] = combination(len-1, i);
        }
        DFS(0, 0);
    }
}