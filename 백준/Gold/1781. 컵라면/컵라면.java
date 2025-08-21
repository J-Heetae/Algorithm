import java.util.*;
import java.io.*;

class Main {
    static class Problem {
        int deadLine, cupramean;
        Problem(int deadLine, int cupramean) {
            this.deadLine = deadLine;
            this.cupramean = cupramean;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Problem[] problems = new Problem[N];
        for(int i=0; i<N; i++) {
            String[] read = br.readLine().split(" ");
            int deadLine = Integer.parseInt(read[0]);
            int cupramean = Integer.parseInt(read[1]);
            problems[i] = new Problem(deadLine, cupramean);
        }
        Arrays.sort(problems, Comparator.comparingInt(p -> p.deadLine));

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(Problem p : problems) {
            q.offer(p.cupramean);

            if(q.size() > p.deadLine) {
                q.poll();
            }
        }

        int maxCupramean = 0;
        while(!q.isEmpty()) {
            maxCupramean += q.poll();
        }

        System.out.println(maxCupramean);
    }
}