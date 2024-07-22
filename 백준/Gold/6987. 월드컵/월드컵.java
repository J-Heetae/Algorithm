import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static long[] exp = {1_000_000_000_000_000L, 1_000_000_000_000L, 1_000_000_000L, 1_000_000L, 1_000L, 1L};
    static long result;
    static int[][] matchUp = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 4},
        {2, 5}, {3, 4}, {3, 5}, {4, 5}};

    public static void main(String[] args) throws IOException {
        for (int tc = 0; tc < 4; tc++) {
            result = Long.parseLong(br.readLine().replaceAll(" ", ""));
            sb.append(solve(0, 0) ? "1" : "0").append(" ");
        }
        System.out.print(sb.toString());
    }

    public static boolean solve(int n, long state) {
        if (n == 15)
            return state == result;

        if (state > result)
            return false;

        int A = matchUp[n][0], B = matchUp[n][1];

        long win = state + 100 * exp[A] + exp[B];
        long draw = state + 10 * exp[A] + 10 * exp[B];
        long lose = state + exp[A] + 100 * exp[B];

        return solve(n + 1, win) || solve(n + 1, draw) || solve(n + 1, lose);
    }
}