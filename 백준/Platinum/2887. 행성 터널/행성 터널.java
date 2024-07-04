import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    static int[] setNumber;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());

        int numberOfPlanets = Integer.parseInt(tokenizer.nextToken());

        setNumber = new int[numberOfPlanets];
        for (int i = 0; i < numberOfPlanets; i++) {
            setNumber[i] = i;
        }

        int[][] planets = new int[numberOfPlanets][4];

        for (int i = 0; i < numberOfPlanets; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            planets[i][0] = i;
            planets[i][1] = Integer.parseInt(tokenizer.nextToken());
            planets[i][2] = Integer.parseInt(tokenizer.nextToken());
            planets[i][3] = Integer.parseInt(tokenizer.nextToken());
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        for (int i = 1; i <= 3; i++) {
            int axis = i;
            Arrays.sort(planets, Comparator.comparingInt(a -> a[axis]));

            for (int j = 1; j < numberOfPlanets; j++) {
                pq.offer(new int[] {planets[j - 1][0], planets[j][0], Math.abs(planets[j - 1][i] - planets[j][i])});
            }
        }

        int numberOfTunnels = 0;
        long minCost = 0;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            if (find(poll[0]) != find(poll[1])) {
                minCost += poll[2];
                union(poll[0], poll[1]);
                numberOfTunnels++;

                if (numberOfTunnels == numberOfPlanets - 1) {
                    break;
                }
            }
        }
        System.out.println(minCost);
    }

    static void union(int a, int b) {
        int setNumberA = find(a);
        int setNumberB = find(b);

        if (setNumberA != setNumberB) {
            if (setNumberA > setNumberB) {
                setNumber[setNumberA] = setNumberB;
            } else {
                setNumber[setNumberB] = setNumberA;
            }
        }
    }

    static int find(int a) {
        if (setNumber[a] == a) {
            return a;
        } else {
            return setNumber[a] = find(setNumber[a]);
        }
    }
}