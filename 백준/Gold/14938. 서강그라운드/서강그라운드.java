import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i <=n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }


        List<int[]>[] roads = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) {
            roads[i] = new ArrayList<>();
        }

        for(int i=0; i<r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            roads[a].add(new int[]{b, d});
            roads[b].add(new int[]{a, d});
        }

        int max = 0;
        for(int start=1; start<=n; start++) {
            int[] length = new int[n + 1];
            for(int i=1; i<=n; i++) {
                length[i] = -1;
            }
            length[start] = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            pq.offer(new int[]{start, 0});

            while(!pq.isEmpty()) {
                int[] poll = pq.poll();
                int from = poll[0];
                int dis = poll[1];

                for(int[] road : roads[from]) { //현재 위치에서 갈 수 있는 지역
                    int to = road[0];
                    int nextDis = road[1];
                    if(length[to] == -1 || length[to] > dis + nextDis) { //처음 방문하는 지역
                        if(dis + nextDis <= m) { //수색범위 내
                            length[to] = dis + nextDis;
                            pq.offer(new int[]{to, length[to]});
                        }
                    }
                }
            }

            int sum = 0;
            for(int i=1; i<=n; i++) {
                if(length[i] != -1) sum += items[i];
            }

            max = Math.max(sum, max);
        }
        System.out.print(max);
    }
}