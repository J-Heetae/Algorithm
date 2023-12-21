import java.util.*;

class Solution {
    int[] connect;
    int cnt = 1;

    public int solution(int n, int[][] computers) {
        connect = new int[n];

        for(int i=0; i<n; i++) {
            if (connect[i] == 0) {
                Queue<Integer> que = new LinkedList<>();
                que.offer(i);
                connect[i] = cnt++;

                while (!que.isEmpty()) {
                    Integer poll = que.poll();

                    for(int j=0; j<n; j++) {
                        if(computers[poll][j] == 1) {
                            if(connect[j] == 0) {
                                if(poll != j) {
                                    que.offer(j);
                                    connect[j] = connect[poll];
                                }
                            }
                        }
                    }
                }
            }
        }

        return cnt - 1;
    }
}