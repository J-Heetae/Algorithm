import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] solutions = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(solutions);

        int min = Integer.MAX_VALUE;
        int[] two = new int[2];
        int left = 0;
        int right = solutions.length - 1;
        while (left < right) {
            int sum = solutions[left] + solutions[right];

            if (Math.abs(min) > Math.abs(sum)) {
                min = sum;
                two[0] = solutions[left];
                two[1] = solutions[right];
            }

            if (solutions[left] + solutions[right] > 0) {
                right--;
            } else if (solutions[left] + solutions[right] < 0) {
                left++;
            } else {
                break;
            }
        }
        System.out.println(two[0] + " " + two[1]);
    }
}