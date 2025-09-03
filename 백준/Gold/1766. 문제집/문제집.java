import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] in = new int[N + 1];
        List<Integer>[] out = new List[N + 1];

        for(int i=1; i<=N; i++) {
            out[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            in[b]++;
            out[a].add(b);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<=N; i++) {
            if(in[i] == 0) {
                pq.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            int poll = pq.poll();

            sb.append(poll).append(" ");

            for(int next : out[poll]) {
                in[next]--;
                if(in[next] == 0) {
                    pq.offer(next);
                }
            }
        }
        System.out.print(sb);
    }
}