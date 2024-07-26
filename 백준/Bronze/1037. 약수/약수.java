import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        int numOfMeasure = Integer.parseInt(reader.readLine());
        int[] arr = new int[numOfMeasure];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numOfMeasure; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(arr);

        int N;
        if (numOfMeasure == 1) {
            N = arr[0] * arr[0];
        } else {
            N = arr[0] * arr[arr.length - 1];
        }

        System.out.println(N);
    }
}