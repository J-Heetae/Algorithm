import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        int a = Integer.parseInt(tokenizer.nextToken());
        int b = Integer.parseInt(tokenizer.nextToken());

        int gcd = Math.max(a, b);
        while (true) {
            if (a % gcd == 0 && b % gcd == 0) {
                System.out.println(gcd);
                break;
            }
            gcd--;
        }

        int lcm = Math.min(a, b);
        while (true) {
            if (lcm % a == 0 && lcm % b == 0) {
                System.out.println(lcm);
                break;
            }
            lcm++;
        }
    }
}