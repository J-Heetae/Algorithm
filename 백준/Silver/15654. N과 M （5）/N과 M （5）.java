import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, M;

    static int[] numbers;
    static boolean[] isUsed;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] read;

        read = br.readLine().split(" ");
        N = Integer.parseInt(read[0]);
        M = Integer.parseInt(read[1]);

        numbers = new int[N];
        isUsed = new boolean[N];

        read = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(read[i]);
        }
        Arrays.sort(numbers);

        pickArr(0, new int[M]);

        System.out.println(sb.toString());
    }
    static void addArr(int[] arr) {
        for(int i=0; i<M; i++) {
            sb.append(arr[i]).append(" ");
        }
        sb.append("\n");
    }

    static void pickArr(int idx, int[] arr) {
        if(idx == M) { //수열 완성
            addArr(arr);
            return;
        }

        for(int i=0; i<N; i++) {
            if(!isUsed[i]) {
                isUsed[i] = true;
                arr[idx] = numbers[i];
                pickArr(idx + 1, arr);
                isUsed[i] = false;
            }
        }
    }

}