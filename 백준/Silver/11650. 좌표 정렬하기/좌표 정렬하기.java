import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        int numCoors = Integer.parseInt(reader.readLine());

        PriorityQueue<Coordinate> priorityQueue = new PriorityQueue<>((a, b) -> {
            if (a.x == b.x)
                return a.y - b.y;
            return a.x - b.x;
        });

        for(int i = 0; i < numCoors; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            priorityQueue.add(new Coordinate(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
        }

        StringBuilder sb = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            Coordinate coordinate = priorityQueue.poll();
            sb.append(coordinate.x).append(" ").append(coordinate.y).append("\n");
        }
        System.out.println(sb);
    }

    static class Coordinate {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}