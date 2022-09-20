package DFS_BFS;

import java.util.Scanner;

//합이 같은 부분 집합
public class Test_220920_71 {
    static int n, total;
    static int[] arr;
    static boolean flag = false;
    static String answer;

    static void DFS(int L, int sum) {
        if(flag) return ;
        if(sum>total) return;
        if(L == n) {
            if(sum == total) {
                answer = "YES";
                flag = true;
            }
        } else {
            DFS(L+1, sum + arr[L]);
            DFS(L+1, sum);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
            total += arr[i];
        }

        if((total % 2) == 0) DFS(0, 0);
        else answer = "NO";

        System.out.print(answer);
    }
}
