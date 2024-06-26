import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, W;
    static int[] minTime;
    static ArrayList<ArrayList<Road>> roadList;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            minTime = new int[N + 1];
            roadList = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                roadList.add(new ArrayList<>());
            }

            for (int i = 0; i < M + W; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                if (i < M) { // 도로는 양방향 그래프
                    roadList.get(start).add(new Road(end, weight));
                    roadList.get(end).add(new Road(start, weight));
                } else { // 웜홀은 단방향 그래프
                    roadList.get(start).add(new Road(end, -weight));
                }
            }
            sb.append(bellmanFord() ? "YES\n" : "NO\n");
        }
        System.out.println(sb);
    }

    public static boolean bellmanFord() {
        Arrays.fill(minTime, INF);
        minTime[1] = 0; // 시작점은 0으로 초기화.
        boolean update = false;

        // (정점의 개수 - 1)번 동안 최단거리 초기화 작업을 반복함.
        for (int i = 1; i <= N; i++) {
            update = false;

            // 모든 간선에 대해 동작
            for (int j = 1; j <= N; j++) {
                for (Road road : roadList.get(j)) {
                    if (minTime[road.to] > minTime[j] + road.time) {
                        minTime[road.to] = minTime[j] + road.time;
                        update = true;

                        if (i == N) {
                            return true;
                        }
                    }
                }
            }
            if (!update) {
                break;
            }
        }
        return false;
    }

    static class Road {
        int to;
        int time;

        Road(int end, int time) {
            this.to = end;
            this.time = time;
        }
    }


}