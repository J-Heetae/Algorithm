package GreedyAlgorithm;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//결혼식
public class Test_220923_84_2 {
    private static class Time implements Comparable<Time> {
        int time;
        char status;

        public Time(int time, char status) {
            this.time = time;
            this.status = status;
        }

        @Override
        public int compareTo(Time o) {
            if(this.time == o.time) return o.status - this.status;
            else return this.time - o.time;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int count = 0;
        int answer = 0;
        ArrayList<Time> times = new ArrayList<>();

        for(int i=0; i<num; i++) {
            int in = sc.nextInt();
            int out = sc.nextInt();
            times.add(new Time(in, 'i'));
            times.add(new Time(out, 'o'));
        }

        Collections.sort(times);

        for(Time t : times) {
            if(t.status == 'i') count++;
            else {
                answer = Math.max(answer, count);
                count--;
            }
        }

        System.out.println(answer);
    }
}