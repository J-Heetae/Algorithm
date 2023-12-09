import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int[] degree, timeArr, answer;
    static ArrayList<Integer>[] edgeList;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        answer = new int[N+1];
        timeArr = new int[N+1];
        degree = new int[N+1];
        edgeList = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            edgeList[i] = new ArrayList();
        }

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            timeArr[i] = time;

            int before = Integer.parseInt(st.nextToken());
            while(before != -1) {
                degree[i]++;
                edgeList[before].add(i);
                before = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Integer> que = new LinkedList<>();
        for(int i=1; i<=N; i++) {
            if(degree[i] == 0) {
                que.offer(i);
            }
        }

        while(!que.isEmpty()) {
            int cur = que.poll();
            build(cur);
        }

        for(int i=1; i<=N; i++) {
            bw.write(answer[i] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void build(int num) {
        answer[num] += timeArr[num];

        for(int next : edgeList[num]) {
            degree[next]--;
            answer[next] = Math.max(answer[next], answer[num]);
            if(degree[next] == 0) {
                build(next);
            }
        }
    }
}