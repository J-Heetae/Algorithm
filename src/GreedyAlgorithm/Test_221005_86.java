package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//최대 수입 스케쥴
public class Test_221005_86 {
    static class Lecture implements Comparable<Lecture>{
        int dayLimit;
        int pay;

        public Lecture(int dayLimit, int pay) {
            this.dayLimit = dayLimit;
            this.pay = pay;
        }

        @Override
        public int compareTo(Lecture o) {
            if(o.pay == this.pay) return o.dayLimit - this.dayLimit;
            return o.pay - this.pay;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = 0;

        ArrayList<Lecture> list = new ArrayList<>();
        int dayMax = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            int pay = sc.nextInt();
            int dayLimit = sc.nextInt();

            if(dayLimit>dayMax) dayMax = dayLimit;

            list.add(new Lecture(dayLimit, pay));
        }

        Collections.sort(list);

        int[] dayCount = new int[dayMax+1];
        int lectureNum = 0;

        for(int i=0; i<n; i++) {
            Lecture lecture = list.get(i);
            int dayLimit = lecture.dayLimit;
            int pay = lecture.pay;

            if(dayCount[dayLimit] == 0) {
                dayCount[dayLimit]++;
                answer += pay;
                lectureNum++;
            } else if(dayLimit != 1) {
                int cnt = dayLimit -1;
                while(cnt != 0) {
                    if(dayCount[cnt] == 0) {
                        dayCount[cnt]++;
                        answer += pay;
                        lectureNum++;
                        break;
                    }
                    cnt--;
                }
            }

            if (dayMax == lectureNum) break;
        }

        System.out.print(answer);
    }
}
