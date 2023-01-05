package baekjoon.two_dimensional_array;

import java.util.Scanner;

public class Test_2738 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a1 = new int[n * m];
        int[] a2 = new int[n * m];

        for (int i = 0; i < n * m; i++) a1[i] = sc.nextInt();
        for (int i = 0; i < n * m; i++) a2[i] = sc.nextInt();

        int[][] arr1 = makeArr(n, m, a1);
        int[][] arr2 = makeArr(n, m, a2);

        int[][] arr3 = sumOfArr(n, m, arr1, arr2);

        printArr(n, m, arr3);
    }

    static int[][] makeArr(int n,int m, int[] a) {
        int[][] arr = new int[n][m];

        int cnt = 0;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                arr[i][j] = a[cnt++];
            }
        }
        return arr;
    }

    static int[][] sumOfArr(int n, int m, int[][] a, int[][] b) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                a[i][j] += b[i][j];
            }
        }

        return a;
    }

    static void printArr(int n,int m, int[][] a) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                System.out.print(a[i][j]);
                if(j != m-1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}
