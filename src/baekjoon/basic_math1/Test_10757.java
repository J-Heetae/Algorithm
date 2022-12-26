package baekjoon.basic_math1;

import java.util.Scanner;

public class Test_10757 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String A = sc.next();
        String B = sc.next();

        int length = Math.max(A.length(), B.length());

        int[] arrA = new int[length + 1];
        int[] arrB = new int[length + 1];

        for (int i=A.length()-1, idx=0; i>=0; i--, idx++) {
            arrA[idx] = Integer.parseInt(A.charAt(i)+"");
        }

        for (int i=B.length()-1, idx=0; i>=0; i--, idx++) {
            arrB[idx] = Integer.parseInt(B.charAt(i)+"");
        }
        
        for(int i=0; i<length; i++) {
            int value = arrA[i] + arrB[i];
            arrA[i] += value % 10;
            arrA[i + 1] += value / 10;
        }

        StringBuilder sb = new StringBuilder();
        if(arrA[length] != 0) sb.append(arrA[length]);

        for(int i=length-1; i>=0; i--) {
            sb.append(arrA[i]);
        }

        System.out.println(sb);
    }
}
