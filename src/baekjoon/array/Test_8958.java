package baekjoon.array;

import java.util.Scanner;

public class Test_8958 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String[] arr = new String[num];

        for(int i=0; i<num; i++) {
            arr[i] = sc.next();
        }

        for(int i=0; i<num; i++) {
            String test = arr[i];
            int cnt = 1;
            int score = 0;

            for (char c : test.toCharArray()) {
                if(c == 'O') score += cnt++;
                else cnt = 1;
            }

            System.out.println(score);
        }
    }
}