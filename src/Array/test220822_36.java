package Array;

import java.util.ArrayList;
import java.util.Scanner;

//Array_멘토링
public class test220822_36 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int stu = sc.nextInt();
        int test = sc.nextInt();
        int answer = 0;
        int[][] result = new int[test][stu];
        ArrayList<Integer> mentee = new ArrayList<>();

        for(int i=0; i<test; i++) {
            for(int j=0; j<stu; j++) {
                result[i][j] = sc.nextInt();
            }
        }
//        각 테스트의 꼴등
        for(int i=0; i<test; i++) {
            mentee.add(result[i][stu - 1]);
        }

        for(int i=1; i<=stu; i++) {
//            각 테스트의 꼴등 멘티 제외
            if(!mentee.contains(i)) {
                for (int j = 1; j <= stu; j++) {
//                    같은 학생 비교 제외
                    if (i != j) {
                        int tmp = 0;
                        for (int k = 0; k < test; k++) {
                            for (int l = 0; l < stu; l++) {
                                if (result[k][l] == i) {
                                    tmp = i;
                                    break;
                                } else if (result[k][l] == j) {
                                    tmp = j;
                                    break;
                                }
                            }
                            if (tmp == j) break;
                        }
                        if (tmp == i) answer++;
                    }
                }
            }
        }
        System.out.print(answer);
    }
}
