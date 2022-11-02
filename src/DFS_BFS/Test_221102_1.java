package DFS_BFS;

import com.sun.jdi.connect.spi.TransportService;

import java.util.*;

//프로그래머스 네트워크 DFS/BFS
public class Test_221102_1 {
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

    public static void main(String[] args) {
        Test_221102_1 test = new Test_221102_1();
//        System.out.println(test.solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
//        System.out.println(test.solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
//        System.out.println(test.solution(3, new int[][]{{1, 0, 1}, {0, 1, 0}, {1, 0, 1}}));
//        System.out.println(test.solution(4, new int[][]{{1, 1, 0, 1}, {1, 1, 0, 0}, {0, 0, 1, 1}, {1, 0, 1, 1}}));
        System.out.println(test.solution(4, new int[][]{{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}}));
    }
}
