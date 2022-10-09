package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

//원더랜드(최소스패닝트리) 프림
public class Test_221009_88_2 {
    private static class Street implements Comparable<Street> {
        int toCity;
        int cost;

        public Street(int toCity, int cost) {
            this.toCity = toCity;
            this.cost = cost;
        }

        @Override
        public int compareTo(Street o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int answer = 0;
        int cityNum = sc.nextInt();
        int streetNum = sc.nextInt();
        int[] ch = new int[cityNum + 1];

        ArrayList<ArrayList<Street>> arr = new  ArrayList<ArrayList<Street>>();

        for(int i = 0; i<= cityNum; i++) {
            arr.add(new ArrayList<Street>());
        }

        for(int i = 0; i< streetNum; i++) {
            int city1 = sc.nextInt();
            int city2 = sc.nextInt();
            int cost = sc.nextInt();

            arr.get(city1).add(new Street(city2, cost));
            arr.get(city2).add(new Street(city1, cost));
        }

        PriorityQueue<Street> pQ = new PriorityQueue<>();

        pQ.offer(new Street(1, 0));
        int cnt = -1;

        while ((!pQ.isEmpty()) && (cnt != cityNum-1)) {
            Street poll = pQ.poll();
            int toCity = poll.toCity;
            int cost = poll.cost;

            if(ch[toCity] == 0) {
                answer += cost;
                ch[toCity]++;
                cnt++;
                for (Street street : arr.get(toCity)) {
                    pQ.offer(street);
                }
            }
        }

        System.out.println(answer);
    }
}
