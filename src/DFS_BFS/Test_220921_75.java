package DFS_BFS;


import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

//동전 교환
public class Test_220921_75 {
    static int type, change, answer = Integer.MAX_VALUE;
    static Integer[] coins;

    private static void DFS(int L, int sum) {
        if (sum > change) return;
        if(L >= answer) return;
        if(sum == change) {
            answer = Math.min(L, answer);
            return;
        }
        else {
            for(int i=0; i<type; i++)
                DFS(L + 1, sum + coins[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        type = sc.nextInt();
        coins = new Integer[type];
        for (int i=0; i<type; i++) {
            coins[i] = sc.nextInt();
        }
        Arrays.sort(coins, Collections.reverseOrder());
        change = sc.nextInt();

        DFS(0, 0);

        System.out.println(answer);
    }
}