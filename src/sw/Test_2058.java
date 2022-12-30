package sw;

import java.util.Scanner;

public class Test_2058 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int sum = 0;

        int tmp = 1000;

        while(tmp != 0) {
            sum += N/tmp;
            N %= tmp;
            tmp /= 10;
        }

        System.out.println(sum);
    }
}
