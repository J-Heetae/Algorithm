package GreedyAlgorithm;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//회의실 배정
public class Test_220923_83 {
    private static class Meeting implements Comparable<Meeting> {
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if(this.start == o.start) return this.end - o.end;
            else return this.start - o.start;
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

        Meeting first = meetings.get(0);
        answer++;

        for(int j=1; j< meetings.size(); j++) {
            Meeting next = meetings.get(j);
            if (first.end <= next.start) {
                answer++;
                first = next;
            }
            else {
                if (first.end > next.end) {
                    first = next;
                }
            }
        }

        System.out.println(answer);
    }
}