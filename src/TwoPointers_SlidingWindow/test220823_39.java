package TwoPointers_SlidingWindow;

import java.util.*;

//sliding window
public class test220823_39 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        int sum = 0;
        for(int i=0; i<m; i++) {
            sum += arr[i];
        }

        int answer = sum;

        for(int i=m; i<n; i++) {
            sum += (arr[i] - arr[i-m]);
            answer = Math.max(sum, answer);
        }

        System.out.print(answer);
    }
}
