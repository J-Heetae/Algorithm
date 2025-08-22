import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] lectures = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            lectures[i] = new int[] {
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(lectures, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        int answer = 1;
        PriorityQueue<Integer> endTime = new PriorityQueue<>();
        for(int[] lec : lectures) {
            if(endTime.isEmpty()) {
                endTime.offer(lec[1]);
                continue;
            }

            if(endTime.peek() <= lec[0]) {
                endTime.poll();
                endTime.offer(lec[1]);
            } else {
                if(endTime.size() == answer) {
                    answer++;
                    endTime.offer(lec[1]);
                }
            }
        }
        System.out.print(answer);
    }
}