import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();

        int[] counter = new int[10];
        for (int i = 0; i < N.length(); i++) {
            counter[N.charAt(i) - '0']++;
        }

        int countSix = counter[6];
        int countNine = counter[9];

        double sum = countSix + countNine;
        int result = (int)Math.ceil(sum / 2);

        counter[6] = result;
        counter[9] = result;

        int maxNumber = 0;
        for (int i = 0; i < 10; i++) {
            if(counter[i] > counter[maxNumber]) {
                maxNumber = i;
            }
        }

        System.out.println(counter[maxNumber]);
    }
}