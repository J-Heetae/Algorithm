import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        // 보석의 무게와 가격 저장
        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            int weight = sc.nextInt();
            int value = sc.nextInt();
            jewels[i] = new Jewel(weight, value);
        }

        // 가방의 최대 허용 무게 저장
        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = sc.nextInt();
        }

        // 보석과 가방을 각각 무게, 허용무게 기준으로 오름차순 정렬
        Arrays.sort(jewels, (a, b) -> a.weight - b.weight);
        Arrays.sort(bags);

        // 최대 힙을 사용하여 최대 가격의 보석을 선택
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        long maxValue = 0;
        int jewelIndex = 0;

        for (int i = 0; i < K; i++) {
            while (jewelIndex < N && jewels[jewelIndex].weight <= bags[i]) {
                maxHeap.add(jewels[jewelIndex].value);
                jewelIndex++;
            }

            if (!maxHeap.isEmpty()) {
                maxValue += maxHeap.poll();
            }
        }

        System.out.println(maxValue);
    }
}

class Jewel {
    int weight;
    int value;

    public Jewel(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}
