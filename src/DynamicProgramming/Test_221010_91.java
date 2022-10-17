package DynamicProgramming;

import java.util.Scanner;

//최대 부분 증가수열
public class Test_221010_91 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int max = 1;
        int[] arr = new int[n];
        int[] dy = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        dy[0] = 1;

        for(int i=1; i<n; i++) {
            for(int j=0; j<i; j++) {
                if((arr[i] > arr[j]) && (dy[j]+1 > dy[i])) {
                    dy[i] = dy[j]+1;
                    if(max < dy[i]) max = dy[i];
                }
            }
            if(dy[i] == 0) dy[i] = 1;
        }

        System.out.println(max);
    }
}
