import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        int numJewels = Integer.parseInt(tokenizer.nextToken());
        int bumBags = Integer.parseInt(tokenizer.nextToken());

        Jewel[] jewels = new Jewel[numJewels];
        int[] bags = new int[bumBags];

        for (int i = 0; i < numJewels; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int weight = Integer.parseInt(tokenizer.nextToken());
            int value = Integer.parseInt(tokenizer.nextToken());
            jewels[i] = new Jewel(weight, value);
        }

        int maxWeight = 0;
        for (int i = 0; i < bumBags; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            bags[i] = Integer.parseInt(tokenizer.nextToken());
            maxWeight = Math.max(maxWeight, bags[i]);
        }

        Arrays.sort(jewels, Comparator.comparingInt(a -> a.weight));
        Arrays.sort(bags);

        PriorityQueue<Integer> jewelValueQueue = new PriorityQueue<>(Collections.reverseOrder());
        int jewelIdx = 0;
        long totalValue = 0L;
        for (int bag : bags) {
            while (jewelIdx < jewels.length && jewels[jewelIdx].weight <= bag) {
                jewelValueQueue.offer(jewels[jewelIdx].value);
                jewelIdx++;
            }
            if (!jewelValueQueue.isEmpty()) {
                totalValue += jewelValueQueue.poll();
            }
        }
        System.out.println(totalValue);
    }

    static class Jewel {
        int weight, value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}