package TwoPointers_SlidingWindow;

import java.util.*;

//최대 길이 연속부분수열
public class test220823_42 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int lt = 0, zeroCnt = 0, answer = 0;
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = sc.nextInt();

        /*
        int tmp = 0;
        for(int rt=0; rt<n; rt++) {
            if(arr[rt] == 0) {
                zeroCnt++;
                if(zeroCnt > m) {
                    answer = Math.max(answer, tmp);
                    while(zeroCnt > m) {
                        if(arr[lt] == 0) {
                            zeroCnt--;
                        }
                        lt++;
                        tmp--;
                    }
                }
            }
            tmp++;
        }*/
        for(int rt=0; rt<n; rt++) {
            if(arr[rt] == 0) zeroCnt++;
            while(zeroCnt > m) {
                if(arr[lt] == 0) zeroCnt--;
                lt++;
            }
            answer = Math.max(answer, rt-lt+1);
        }
        System.out.print(answer);
    }
}
