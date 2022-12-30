package sw;

import java.util.Scanner;

public class Test_2068 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int i=1; i<=T; i++) {
            int max = 0;

            for(int j=0; j<10; j++) {
                int tmp = sc.nextInt();
                max = Math.max(max, tmp);
            }

            System.out.println("#" + i + " " + max);
        }
    }
}
