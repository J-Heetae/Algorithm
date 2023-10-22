import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] next = new ArrayList[N+1];

        for(int i=1; i<=N; i++) {
            next[i] = new ArrayList<>();
        }

        int[] indegree = new int[N+1];

        int before, cur;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            before = Integer.parseInt(st.nextToken());
            cur = Integer.parseInt(st.nextToken());
            indegree[cur]++;
            next[before].add(cur);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<=N; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }

        while(!pq.isEmpty()) {
            int problem = pq.poll();
            for(int n : next[problem]) {
                indegree[n]--;
                if(indegree[n] == 0) {
                    pq.offer(n);
                }
            }
            bw.write(problem + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}