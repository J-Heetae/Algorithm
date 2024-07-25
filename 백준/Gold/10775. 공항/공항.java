import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int[] next;

    public static void main(String[] args) throws IOException {
        int G = Integer.parseInt(reader.readLine());
        int P = Integer.parseInt(reader.readLine());

        next = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            next[i] = i;
        }

        int plane = 0;
        while (P-- > 0) {
            int gate = find(Integer.parseInt(reader.readLine()));
            if (find(gate) == 0) {
                break;
            } else {
                union(gate, gate - 1);
                plane++;
            }
        }
        System.out.println(plane);
    }

    static int find(int a) {
        if (a == next[a])
            return a;
        return next[a] = find(next[a]);
    }

    static void union(int a, int b) {
        int nextA = find(a);
        int nextB = find(b);

        if (nextA != nextB) {
            if (next[nextA] >= next[nextB]) {
                next[nextA] = nextB;
            } else {
                next[nextB] = nextA;
            }
        }
    }
}
