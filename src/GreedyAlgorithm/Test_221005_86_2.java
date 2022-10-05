package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

//최대 수입 스케쥴 (PriorityQueue)
public class Test_221005_86_2 {
    static class Lecture implements Comparable<Lecture>{
        int dayLimit;
        int pay;

        public Lecture(int dayLimit, int pay) {
            this.dayLimit = dayLimit;
            this.pay = pay;
        }

        @Override
        public int compareTo(Lecture o) {
            return o.dayLimit - this.dayLimit;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = 0;
        int dayMax = Integer.MIN_VALUE;

        ArrayList<Lecture> list = new ArrayList<>();
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<n; i++) {
            int pay = sc.nextInt();
            int dayLimit = sc.nextInt();

            if(dayLimit > dayMax) dayMax = dayLimit;

            list.add(new Lecture(dayLimit, pay));
        }

        Collections.sort(list);

        int j = 0;
        for(int i=dayMax; i>= 1; i--) {
            for( ; j<n; j++) {
                if(i > list.get(j).dayLimit) break;
                else pQ.offer(list.get(j).pay);
            }
            if(!pQ.isEmpty()) answer += pQ.poll();
        }


        System.out.print(answer);
    }
}
