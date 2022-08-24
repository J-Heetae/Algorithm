package TwoPointers_SlidingWindow;

import java.util.*;

//연속된 자연수의 합(two pointer)
public class test220823_41 {
    //2중 for문
    /*
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = 0;
        int[] arr = new int[n/2 +1];
        for(int i=0; i<arr.length; i++) {
            arr[i] = i+1;
        }

        for(int i=0; i<arr.length; i++) {
            int sum = 0;
            for(int j=i; j<arr.length; j++) {
                sum += arr[j];
                if(sum == n) {
                    answer++;
                    break;
                }
                if(sum > n) break;
            }
        }
        System.out.print(answer);
    }
    */
    //two pointer
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = 0, sum =0, lt =0;
        int[] arr = new int[n/2 +1];
        for(int i=0; i<arr.length; i++) {
            arr[i] = i+1;
        }
        for(int rt=0; rt<arr.length; rt++) {
            sum += arr[rt];
            if(sum == n) answer++;
            while(sum >= n) {
                sum -= arr[lt++];
                if(sum == n) answer++;
            }
        }
        System.out.print(answer);
    }
}
