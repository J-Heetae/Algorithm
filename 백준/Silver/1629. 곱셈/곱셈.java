import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int mod;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int A = Integer.parseInt(split[0]);
        int B = Integer.parseInt(split[1]);
        mod = Integer.parseInt(split[2]);

        System.out.println(multiply(A, B));
    }

    static long multiply(int a, int b) {
        if (b == 1) return a % mod;

        long temp = multiply(a, b / 2);

        temp = temp * temp % mod;
        if (b % 2 == 1) { //홀수인 경우
            temp = temp * a % mod;
        }
        return temp;
    }
}