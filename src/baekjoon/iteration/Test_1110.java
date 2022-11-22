package baekjoon.iteration;

import java.util.Scanner;

public class Test_1110 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        //처음 주어진 정수 저장
        int originNum = num;
        //싸이클 길이 카운트
        int cnt = 0;

        while(true) {
            //싸이클 카운트 추가
            cnt++;

            //왼쪽 자리 수
            int front;
            //오른쪽 자리 수
            int back;
            //각 자리수 합
            int tmp2;

            if(num < 10) {
                back = tmp2 = num;
            } else {
                front = num / 10;
                back = num - front * 10;
                tmp2 = front + back;
            }

            //각 자리 수 합은 최대 18까지 가능
            //가장 오른쪽 자리 수만 필요하기 때문에 10 이상일 경우 -10
            if(tmp2 >= 10) tmp2 -= 10;

            //새로운 수
            int newNum = back * 10 + tmp2;

            //처음 주어진 수와 새로운 수가 같은 경우 끝
            if(newNum == originNum) break;
            else num = newNum;
        }

        System.out.print(cnt);
    }
}
