import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] cable = new int[K];

        long max = 0;
        for(int i = 0; i < K; i++) {
            cable[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, cable[i]);
        }

        max++;
        long min = 1;
        long maxLength = 0;

        while (min < max) {
            long mid = (max + min) / 2;

            long num = 0;
            for (int i : cable) {
                num += i / mid;
            }

            if (num >= N) {
                min = mid + 1;
                maxLength = mid;
            } else {
                max = mid;
            }
        }
        System.out.println(maxLength);
    }
}