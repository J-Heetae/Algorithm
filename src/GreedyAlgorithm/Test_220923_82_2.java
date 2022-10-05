package GreedyAlgorithm;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//씨름선수
public class Test_220923_82_2 {

    private static class Wrestler implements Comparable<Wrestler>{
        int height, weight;
        public Wrestler(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(Wrestler o) {
            if(this.height == o.height) return o.weight - this.weight;
            else return o.height - this.height;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int answer = 0;
        ArrayList<Wrestler> players = new ArrayList<>();

        for(int i=0; i<num; i++) {
            int height = sc.nextInt();
            int weight = sc.nextInt();
            players.add(new Wrestler(height, weight));
        }

        Collections.sort(players);

        int targetWeight = players.get(0).weight;
        answer = 1;

        for(Wrestler p : players) {
            if(targetWeight < p.weight) {
                answer++;
                targetWeight = p.weight;
            }
        }

        System.out.print(answer);
    }
}