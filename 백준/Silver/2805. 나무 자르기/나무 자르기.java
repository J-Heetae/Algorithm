import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;
    static int maxHeight = Integer.MIN_VALUE, M;
    static int[] trees;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        trees = new int[N];
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(trees);

        binarySearch(0, trees[N - 1]);

        System.out.println(maxHeight);
    }

    static void binarySearch(int min, int max) {
        if(min >= max) {
            return;
        }

        int mid = (min + max) / 2;

        long cut = 0;
        for (int i = trees.length - 1; i >= 0; i--) {
            if (trees[i] <= mid) {
                break;
            }
            cut += trees[i] - mid;
        }

        if (cut >= M) {
            maxHeight = Math.max(maxHeight, mid);
            binarySearch(mid + 1, max);
        }else {
            binarySearch(min, mid);
        }
    }
}