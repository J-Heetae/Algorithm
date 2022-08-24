package TwoPointers_SlidingWindow;

import java.util.*;

public class test220823_40 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int answer = 0;
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        for(int i=0; i<n; i++) {
            int tmp =0;
            for(int j=i; j<n; j++) {
                tmp += arr[j];
                if(tmp == m) {
                    answer++;
                    break;
                }
                if(tmp > m) break;
            }
        }

        System.out.print(answer);
    }
}
