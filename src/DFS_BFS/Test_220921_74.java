package DFS_BFS;

import java.util.Scanner;

//중복순열
public class Test_220921_74 {
    static int num, len;
    static int[] arr;
    static void DFS(int L) {
        if(L == len) {
            for(int i : arr) System.out.print(i + " ");
            System.out.println();
        } else {
            for(int i=1; i<=num; i++) {
                arr[L] = i;
                DFS(L + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        len = sc.nextInt();
        arr = new int[len];

        DFS(0);
    }
}
