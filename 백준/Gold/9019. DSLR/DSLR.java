import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    final static int MOD = 10_000;
    final static char[] DSLR = {'D', 'S', 'L', 'R'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            boolean[] isExist = new boolean[MOD];
            Queue<Object[]> q = new LinkedList<>();

            q.offer(new Object[] {A, new StringBuilder()});
            isExist[A] = true;

            while (!q.isEmpty()) {
                Object[] poll = q.poll();
                int num = (int)poll[0];
                StringBuilder context = (StringBuilder)poll[1];

                if (num == B) {
                    sb.append(context).append("\n");
                    break;
                }

                int[] results = new int[4];
                results[0] = D(num);
                results[1] = S(num);
                results[2] = L(num);
                results[3] = R(num);

                for (int i = 0; i < 4; i++) {
                    if (!isExist[results[i]]) {
                        isExist[results[i]] = true;
                        StringBuilder nextContext = new StringBuilder(context);
                        q.offer(new Object[] {results[i], nextContext.append(DSLR[i])});
                    }
                }
            }
        }
        System.out.println(sb);
    }

    static int D(int n) {
        return (n * 2) % MOD;
    }

    static int S(int n) {
        return (n == 0) ? 9999 : n - 1;
    }

    static int L(int n) {
        return  (n % 1000) * 10 + (n / 1000);
    }

    static int R(int n) {
        return (n % 10) * 1000 + n / 10;
    }
}