package DFS_BFS;

import java.util.Scanner;

//바둑이 승차(DFS)
public class Test_220920_72 {

    static int max, c, n;

    static void DFS(int L, int sum, int[] arr) {
        if(sum > c) return;
        if(L == n) {
            max = Math.max(max, sum);
            return;
        } else {
            DFS(L + 1, sum + arr[L], arr);
            DFS(L + 1, sum, arr);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        n = sc.nextInt();
        int[] baduks = new int[n];

        for (int i = 0; i < n; i++) {
            baduks[i] = sc.nextInt();
        }

        DFS(0, 0, baduks);

        System.out.print(max);
    }
}
