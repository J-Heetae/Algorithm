import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        if(M > 200_000) {
            System.out.println(0);
            System.exit(0);
        }

        int[] count = new int[100_001];
        String[] read = br.readLine().split(" ");

        int answer = 0;
        for (String s : read) {
            int num = Integer.parseInt(s);

            if(num > M) {
                continue;
            }

            if (count[M - num] > 0) {
                count[M - num]--;
                answer++;
            } else {
                count[num]++;
            }
        }
        System.out.println(answer);
    }
}