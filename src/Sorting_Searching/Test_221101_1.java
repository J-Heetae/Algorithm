package Sorting_Searching;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//프로그래머스 동굴탐험 위상정렬
public class Test_221101_1 {
    boolean[] visited;
    int[] save;
    ArrayList<Integer>[] path;
    ArrayList<Integer>[] order;
    Queue<Integer> que = new LinkedList<>();


    public boolean solution(int n, int[][] p, int[][] o) {
        visited = new boolean[n];
        save = new int[n];
        path = new ArrayList[n];
        order = new ArrayList[n];

        for(int i=0; i<n; i++) {
            path[i] = new ArrayList<>();
            order[i] = new ArrayList<>();
        }

        for(int i=0; i<p.length; i++) {
            add(path, p[i], 0, 1);
            add(path, p[i], 1, 0);
        }

        for(int i=0; i<o.length; i++) {
            add(order, o[i], 1, 0);
        }

        if(order[0].isEmpty()) {
            visit(0);
        }

        while(!que.isEmpty()) {
            Integer poll = que.poll();

            for (Integer num : path[poll]) {
                if (!visited[num]) {
                    if (order[num].isEmpty()) {
                        if (save[num] != 0) {
                            visit(save[num]);
                        }
                        visit(num);
                    } else {
                        if (visited[order[num].get(0)]) {
                            visit(num);
                        } else {
                            save[order[num].get(0)] = num;
                        }
                    }
                }
            }
        }

        for (boolean b : visited) {
            if(!b) return false;
        }

        return true;
    }

    private void add(ArrayList<Integer>[] path, int[] p, int x, int x1) {
        path[p[x]].add(p[x1]);
    }

    private void visit(int room) {
        que.offer(room);
        visited[room] = true;
    }

    public static void main(String[] args) {
        Test_221101_1 test = new Test_221101_1();
        int n = 9;
        int[][] path = {{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}};
        int[][] order = {{8, 5}, {6, 7}, {4, 1}};

        boolean solution = test.solution(n, path, order);

        System.out.println("solution = " + solution);
    }
}
