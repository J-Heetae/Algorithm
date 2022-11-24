package baekjoon.function;

import java.util.Scanner;

public class Test_1065 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int cnt = 0;

        for(int i=1; i<=num; i++) {
            if(hansoo(i)) cnt++;
        }

        System.out.print(cnt);
    }

    static boolean hansoo(int num) {
        if (num  < 100) return true;

        if(num == 100 || num == 1000) return false;

        if(99 < num && num <= 1000) {
            int a = num / 100;
            int b = num / 10 - a * 10;
            int c = num - a * 100 - b * 10;

            if(a - b == b - c) return true;
        }
        return false;
    }
}
