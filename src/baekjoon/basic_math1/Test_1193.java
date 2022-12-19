package baekjoon.basic_math1;

import java.util.Scanner;

public class Test_1193 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int x = 1;
        int y = 1;

        int cnt = 1;
        int rule = 2;

        while(cnt < num) {
            if(rule%2 == 0) y++;
            else x++;
            cnt++;

            for(int i=1; i<rule; i++) {
                if(cnt == num) break;

                if(rule%2 == 0) {
                    x++;
                    y--;
                } else {
                    x--;
                    y++;
                }
                cnt++;
            }

            rule++;
        }

        System.out.print(x + "/" + y);
    }
}
