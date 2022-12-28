package baekjoon.basic_math2;

import java.util.Scanner;

public class Test_9020_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        int[] numbers = new int[caseNum];
        int max = Integer.MIN_VALUE;

        for(int i=0; i<caseNum; i++) {
            numbers[i] = sc.nextInt();
            max = Math.max(max, numbers[i]);
        }

        boolean[] prime = new boolean[max + 1];

        for(int i=2; i<=max; i++) {
            if(!prime[i]) {
                for(int j=i*2; j<=max; j+=i) {
                    prime[j] = true;
                }
            }
        }

        for (int num : numbers) {
            int left = num / 2;
            int right = num / 2;

            while (left > 1 && right < num) {
                if (!prime[left] && !prime[right]) {
                    System.out.println(left + " " + right);
                    break;
                }
                left--;
                right++;
            }
        }

    }
}