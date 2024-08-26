import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            int number = Integer.parseInt(br.readLine());

            if(maxHeap.size() == minHeap.size()) {
                maxHeap.offer(number);
            } else {
                minHeap.offer(number);
            }

            if(!maxHeap.isEmpty() && !minHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(minHeap.poll());
            }
            answer.append(maxHeap.peek()).append("\n");
        }
        System.out.println(answer);
    }
}