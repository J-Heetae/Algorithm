package baekjoon.iteration;

import java.util.Scanner;

public class Test_25304 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalPrice = sc.nextInt();
        int stuffNum = sc.nextInt();
        int sum = 0;
        String answer = "No";

        for(int i=0; i<stuffNum; i++) {
            int stuff = sc.nextInt();
            int num = sc.nextInt();

            sum += stuff*num;
        }

        if(sum == totalPrice) answer = "Yes";

        System.out.println(answer);
    }
}
