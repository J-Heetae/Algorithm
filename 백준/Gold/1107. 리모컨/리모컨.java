import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        int targetChannel = Integer.parseInt(reader.readLine());
        int numbOfBroken = Integer.parseInt(reader.readLine());

        ArrayList<Integer> brokenButtons = new ArrayList<>();
        if (numbOfBroken > 0) {
            tokenizer = new StringTokenizer(reader.readLine());
            while (tokenizer.hasMoreTokens()) {
                brokenButtons.add(Integer.parseInt(tokenizer.nextToken()));
            }
        }

        int minPress = Math.abs(100 - targetChannel);
        process:
        for (int i = 0; i <= 1_000_000; i++) {
            String str = String.valueOf(i);
            for (int j = 0; j < str.length(); j++) {
                if (brokenButtons.contains(str.charAt(j) - '0')) {
                    continue process;
                }
            }
            minPress = Math.min(minPress, Math.abs(i - targetChannel) + str.length());
        }

        System.out.println(minPress);
    }
}
