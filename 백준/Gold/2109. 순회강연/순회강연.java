import java.io.*;
import java.util.*;

public class Main {
    static class Lecture {
        int p, d;
        Lecture(int p, int d) { this.p = p; this.d = d; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Lecture[] arr = new Lecture[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            arr[i] = new Lecture(p, d);
        }

        // 1) 마감일 오름차순 정렬
        Arrays.sort(arr, (a, b) -> a.d - b.d);

        // 2) 수행 후보: 최소 힙(강연료 p가 작은 게 위)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (Lecture lec : arr) {
            // 일단 넣고
            minHeap.offer(lec.p);

            // 3) 선택 개수가 마감일을 초과하면, 가장 싼 강연을 제거
            if (minHeap.size() > lec.d) {
                minHeap.poll();
            }
        }

        long sum = 0;
        while (!minHeap.isEmpty()) sum += minHeap.poll();
        System.out.println(sum);
    }
}