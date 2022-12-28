package baekjoon.basic_math2;

import java.util.ArrayList;
import java.util.Scanner;

public class Test_9020 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        int[] numbers = new int[caseNum];
        int max = Integer.MIN_VALUE;

        for(int i=0; i<caseNum; i++) {
            numbers[i] = sc.nextInt();
            max = Math.max(max, numbers[i]);
        }

        int[] arr = new int[max + 1];
        ArrayList<Integer> prime = new ArrayList<>();

        for(int i=2; i<=max; i++) {
            if(arr[i] == 0) {
                prime.add(i);
                for(int j=i*2; j<=max; j+=i) arr[j]++;
            }
        }

        for (int num : numbers) {
            int a = 10001;
            int b = 0;

            for (int j = prime.size() - 1; j >= 0; j--) {
                int copy = num;
                int num1 = prime.get(j);
                if (num1 < copy) {
                    copy -= num1;
                    for (int k = j; k >= 0; k--) {
                        int num2 = prime.get(k);
                        if (num2 <= copy) {
                            if (num1 + num2 == num) {
                                if (Math.abs(a - b) > Math.abs(num1 - num2)) {
                                    a = num1;
                                    b = num2;
                                }
                            }
                        }
                    }
                }
            }
            if (a > b) {
                System.out.println(b + " " + a);
            } else {
                System.out.println(a + " " + b);
            }
        }

    }
}