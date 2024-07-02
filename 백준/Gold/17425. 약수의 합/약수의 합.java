import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer read;

        read = new StringTokenizer(br.readLine());
        int testcase = Integer.parseInt(read.nextToken());
        long[] arr = new long[1_000_001];

        for (int i = 1; i <= 1_000_000; i++) {
            for (int j = i; j <= 1_000_000; j += i) {
                arr[j] += i;
            }
        }

        long[] prefix = new long[1_000_001];
        for (int i = 1; i <= 1_000_000; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        while (testcase-- > 0) {
            read = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(read.nextToken());
            stringBuilder.append(prefix[N]).append("\n");
        }
        System.out.println(stringBuilder);
    }
}