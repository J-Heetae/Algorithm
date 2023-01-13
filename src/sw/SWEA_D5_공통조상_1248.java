package sw;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_D5_공통조상_1248 {

    static int[] check;
    static Queue<Integer> que = new LinkedList<>();
    static int common;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int tc=0; tc<T; tc++) {
            //정점의 개수
            int V = sc.nextInt();
            //간선의 개수
            int E = sc.nextInt();
            //공통조상 찾는 정점 번호 두개
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();

            //공통조상
            common = 1;

            /**
             *  up = 자식->부모
             *  down = 부모->자식
             */
            int[] up = new int[V+1];
            ArrayList<ArrayList<Integer>> down = new ArrayList<>();

            for(int i=0; i<=V; i++) {
                down.add(new ArrayList<>());
            }

            for(int i=0; i<E; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                down.get(a).add(b);
                up[b] = a;
            }

            check = new int[V+1];

            /**
             * 공통조상을 찾는 정점으로부터 올라오면서 정점마다 check[정점]++
             * 결국 값이 2인 정점 중에 가장 큰 값이 가장 가까운 공통조상
            */
            checkBFS(v1, up);
            checkBFS(v2, up);

            //가장 가까운 공통 조상 찾기
            for(int i=V; i>=1; i--) {
                if(check[i] == 2) {
                    common = i;
                    break;
                }
            }

            //공통조상을 루트로 하는 서브트리의 크기
            int size = getCntBFS(common, down);

            System.out.printf("#%d %d %d\n", tc+1, common, size);
        }
    }

    private static int getCntBFS(int common, ArrayList<ArrayList<Integer>> down) {
        int cnt =0;

        que.add(common);

        while(!que.isEmpty()) {
            cnt++;

            int cur = que.poll();

            if(!down.get(cur).isEmpty()) {
                for(int num : down.get(cur)) {
                    que.offer(num);
                }
            }
        }

        return cnt;
    }

    private static void checkBFS(int v1, int[] up) {
        que.offer(v1);

        while (!que.isEmpty()) {
            int cur = que.poll();
            if(cur == 1) break;

            //check[cur]가 이미 1인경우 공통조상
            if(check[cur] == 1) {
                common = cur;
                break;
            }

            check[cur]++;
            que.offer(up[cur]);
        }
    }
}
