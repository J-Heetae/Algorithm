import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {

    static int[] payArr, friendNumber;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());

        payArr = new int[N];
        friendNumber = new int[N];

        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            payArr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < N; i++) {
            friendNumber[i] = i;
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int friendA = Integer.parseInt(tokenizer.nextToken()) - 1;
            int friendB = Integer.parseInt(tokenizer.nextToken()) - 1;
            union(friendA, friendB);
        }

        Map<Integer, Integer> minPayMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num = find(i);
            int pay = payArr[i];

            if (minPayMap.containsKey(num)) {
                minPayMap.put(num, Math.min(minPayMap.get(num), pay));
            } else {
                minPayMap.put(num, pay);
            }
        }

        int sum = 0;
        for (Integer pay : minPayMap.values()) {
            sum += pay;
        }

        if (sum > k) {
            System.out.println("Oh no");
        } else {
            System.out.println(sum);
        }
    }

    static void union(int a, int b) {
        int numberA = find(a);
        int numberB = find(b);

        if (numberA != numberB) {
            if (numberA < numberB) {
                friendNumber[numberB] = numberA;
            } else {
                friendNumber[numberA] = numberB;
            }
        }
    }

    static int find(int a) {
        if (friendNumber[a] == a) {
            return a;
        } else {
            return friendNumber[a] = find(friendNumber[a]);
        }
    }
}