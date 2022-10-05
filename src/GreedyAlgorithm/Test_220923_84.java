package GreedyAlgorithm;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

//결혼식
public class Test_220923_84 {
    private static class People implements Comparable<People> {
        int in, out;

        public People(int in, int out) {
            this.in = in;
            this.out = out;
        }

        @Override
        public int compareTo(People o) {
            if(this.out == o.out) return this.in - o.in;
            else return o.out - this.out;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        ArrayList<People> peoples = new ArrayList<>();
        HashMap<Integer, Integer> time = new HashMap<>();

        for(int i=0; i<num; i++) {
            int in = sc.nextInt();
            int out = sc.nextInt();
            peoples.add(new People(in, out));
        }

        Collections.sort(peoples);

        for(People p : peoples) {
            for(int i=p.in; i<p.out; i++) {
                time.put(i, time.getOrDefault(i, 0) + 1);
            }
        }

        Integer max = Collections.max(time.values());

        System.out.println(max);
    }
}