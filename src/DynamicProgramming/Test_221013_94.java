package DynamicProgramming;

import java.util.ArrayList;
import java.util.Scanner;

//최대점수 구하기(냅색 알고리즘)
public class Test_221013_94 {
    static class Question {
        int score;
        int time;

        public Question(int score, int time) {
            this.score = score;
            this.time = time;
        }
    }

    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        int qNum = sc.nextInt();
        int limit = sc.nextInt();
        int[] dy = new int[limit + 1];
        ArrayList<Question> list = new ArrayList<>();

        for(int i=0; i<qNum; i++) {
            int score = sc.nextInt();
            int time = sc.nextInt();
            list.add(new Question(score, time));
        }

        for (Question question : list) {
            for(int i=limit; i>= question.time; i--) {
                dy[i] = Math.max(dy[i], dy[i - question.time] + question.score);
            }
        }

        System.out.print(dy[limit]);
    }
}