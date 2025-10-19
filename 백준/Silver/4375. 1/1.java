import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int mod;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String read = br.readLine();
        while (read != null && !read.isEmpty()) {
            int num = Integer.parseInt(read);
            int left = 1 % num;
            int count = 1;
            while (true) {
                if (left == 0) {
                    sb.append(count).append("\n");
                    break;
                }
                left = (left * 10 + 1) % num;
                count++;
            }
            read = br.readLine();
        }
        System.out.println(sb);
    }
}