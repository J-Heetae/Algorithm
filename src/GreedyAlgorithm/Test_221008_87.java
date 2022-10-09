package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//친구인가?
public class Test_221008_87 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int stdNum = sc.nextInt();
        int n = sc.nextInt();
        String answer = "NO";

        ArrayList<ArrayList<Integer>> stds = new ArrayList<>();

        for(int i=0; i<=stdNum; i++) {
            stds.add(new ArrayList<Integer>());
        }

        for(int i=0; i<n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            stds.get(a).add(b);
            stds.get(b).add(a);
        }

        int std1 = sc.nextInt();
        int std2 = sc.nextInt();

        int[] check = new int[stdNum+1];
        Queue<Integer> que = new LinkedList<>();

        que.offer(std1);
        check[std1] = 1;

        while(!que.isEmpty() && answer.equals("NO")) {
            Integer poll = que.poll();
            int start = poll;

            for (Integer frd : stds.get(start)) {
                if(frd == std2) {
                    answer = "YES";
                    break;
                }
                if(check[frd] == 0) {
                    que.offer(frd);
                    check[frd]++;
                }
            }
        }

        System.out.println(answer);
    }
}
