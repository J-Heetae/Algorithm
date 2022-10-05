package GreedyAlgorithm;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//회의실 배정
public class Test_220923_83_2 {
    private static class Meeting implements Comparable<Meeting> {
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if(this.end == o.end) return this.start - o.start;
            else return this.end - o.end;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int answer = 0;
        ArrayList<Meeting> meetings = new ArrayList<>();

        for(int i=0; i<num; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            meetings.add(new Meeting(start, end));
        }

        Collections.sort(meetings);

        int endTime = 0;
        for(Meeting m : meetings) {
            if(m.start >= endTime) {
                answer++;
                endTime = m.end;
            }
        }

        System.out.println(answer);
    }
}