package baekjoon.string;

import java.util.Scanner;

public class Test_2675 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();

        for(int i=0; i<caseNum; i++) {
            int reps = sc.nextInt();
            String str = sc.next();
            for (char c : str.toCharArray()) {
                for(int j=0; j<reps; j++) {
                    System.out.print(c);
                }
            }
            System.out.println();
        }

    }
}
