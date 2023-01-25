package baekjoon.linkedList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BAEJOON_S3_풍선터뜨리기_2346 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] answer = new int[N];

        HashMap<Integer, Integer> hm = new HashMap<>();
        Queue<Integer> que = new LinkedList<>();

        for(int i=1; i<=N; i++) {
            int tmp = sc.nextInt();
            hm.put(tmp, i);
            que.offer(tmp);
        }

        int idx = 0;
        int d = 1;

        while (!que.isEmpty()) {
            int now = que.poll();

            for (int i = 0; i < (que.size() + now) % que.size(); i++) {
                que.offer(que.poll());
                d++;
            }

            answer[idx] = d % N;
        }

        for(int a : answer) {
            System.out.print(a + " ");
        }
    }
}
