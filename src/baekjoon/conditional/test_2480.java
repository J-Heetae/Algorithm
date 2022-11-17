package baekjoon.conditional;

import java.util.Scanner;

public class test_2480 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int first = sc.nextInt();
        int second = sc.nextInt();
        int third = sc.nextInt();
        int[] count = new int[7];
        int sameNum = Integer.MIN_VALUE;
        int maxNum = 0;
        int reward = 0;
        
        count[first]++;
        count[second]++;
        count[third]++;

        for (int i=1; i<7; i++) {
            if(count[i] >= sameNum) {
                sameNum = count[i];
                maxNum = i;
            }
        }
        

        if(sameNum == 3) {
            reward = 10000 + maxNum * 1000;
        } else if(sameNum == 2) {
            reward = 1000 + maxNum * 100;
        } else {
            reward = maxNum * 100;
        }

        System.out.println(reward);
    }
}
