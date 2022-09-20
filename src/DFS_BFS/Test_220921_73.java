package DFS_BFS;

import java.util.ArrayList;
import java.util.Scanner;

//최대점수 구하기(DFS)
public class Test_220921_73 {

    static int max = Integer.MIN_VALUE, num, time_limit;

    static class test {
        int time;
        int score;

        public test(int score, int time) {
            this.time = time;
            this.score = score;
        }
    }

    static void DFS(int L, int sum_time, int sum_score, ArrayList<test> arr) {
        if(sum_time > time_limit) return;
        if(L == num) {
            max = Math.max(max, sum_score);
        } else {
            DFS(L + 1, sum_time + arr.get(L).time,
                    sum_score + arr.get(L).score, arr);
            DFS(L + 1, sum_time, sum_score, arr);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        time_limit = sc.nextInt();
        ArrayList<test> tests = new ArrayList<>();

        for(int i=0; i<num; i++) {
            int tmp_score = sc.nextInt();
            int tmp_time = sc.nextInt();
            tests.add(new test(tmp_score, tmp_time));
        }

        DFS(0, 0, 0, tests);

        System.out.println(max);
    }
}
