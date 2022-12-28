package baekjoon.basic_math2;

import java.util.Scanner;

public class Test_11653 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int copy = N;

        //N이 1인 경우 아무것도 출력하지 않음
        if(N != 1) {
            //1이 되어 더이상 나눠지지 않으면 종료
            while(copy > 1) {
                boolean flag = true;
                //에라토스테네스의 체를 위한 배열
                int[] arr = new int[N + 1];

                for(int i=2; i<N; i++) {
                    if(arr[i] == 0) {
                        for(int j=i; j<N; j+=i) arr[j]++;
                        //소인수인 경우
                        if(copy%i == 0) {
                            System.out.println(i);
                            copy = copy / i;
                            flag = false;
                            break;
                        }
                    }
                }
                //나눌수 있는 소인수가 없는 경우 그대로 출력 후 종료
                if(flag) {
                    System.out.println(copy);
                    break;
                }
            }
        }
    }
}
