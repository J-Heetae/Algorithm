import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MOD = 1_000_000_007;

    static int[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        tree = new int[N * 4];
        initTree(1, N, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int action = Integer.parseInt(st.nextToken());
            if (action == 1) {
                int idx = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                update(1, N, 1, idx, value);
                arr[idx] = value;
            } else if (action == 2) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                sb.append(getIntervalProduct(1, N, from, to, 1)).append("\n");
            }
        }
        System.out.println(sb);
    }

    static int getIntervalProduct(int start, int end, int from, int to, int node) {
        if (end < from || to < start)
            return 1;

        if (start >= from && to >= end) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return getRemainder(getIntervalProduct(start, mid, from, to, node * 2), getIntervalProduct(mid + 1, end, from, to, node * 2 + 1));
    }

    static int update(int start, int end, int node, int idx, int value) {
        if (start > idx || idx > end) {
            return tree[node];
        }

        if (start == end) {
            return tree[node] = value;
        }

        int mid = (start + end) / 2;
        return tree[node] = getRemainder(update(start, mid, node * 2, idx, value),
            update(mid + 1, end, node * 2 + 1, idx, value));
    }

    static int getRemainder(int a, int b) {
        return (int)(((long)a * b) % MOD);
    }

    static void initTree(int start, int end, int node) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        initTree(start, (start + end) / 2, node * 2);
        initTree((start + end) / 2 + 1, end, node * 2 + 1);

        tree[node] = getRemainder(tree[node * 2], tree[node * 2 + 1]);
    }
}