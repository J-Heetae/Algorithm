import java.util.*;
import java.io.*;

class Main {
    public int solution(int n, int[][] computers) {
        return 0;
    }

    static int N, M;
    static long minTime;
    static int[] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        times = new int[N];
        for(int i=0; i<N; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(times);

        minTime = (long)times[times.length - 1] * M;

        binarySearch();

        System.out.print(minTime);
    }

    static void binarySearch() {
        long l = 1;
        long r = minTime;

        while(l <= r) {
            long m = (l + r) / 2;

            if(possible(m)) {
                minTime = Math.min(minTime, m);
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
    }

    static boolean possible(long limit) {
        long passed = 0;
        for(int t : times) {
            passed += limit / t;
            if(passed >= M) {
                return true;
            }
        }
        return false;
    }
}