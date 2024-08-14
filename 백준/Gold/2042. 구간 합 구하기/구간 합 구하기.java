import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        SegmentTree tree = new SegmentTree(arr);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int action = Integer.parseInt(st.nextToken());
            if (action == 1) {
                int idx = Integer.parseInt(st.nextToken());
                long value = Long.parseLong(st.nextToken());
                tree.change(1, N, 1, idx, value);

            } else if (action == 2) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                long sum = tree.getSum(1, N, from, to, 1);
                sb.append(sum).append("\n");
            }
        }
        System.out.println(sb);
    }

    static class SegmentTree {
        int treeSize;
        long[] tree;

        public SegmentTree(long[] arr) {
            calTreeSize(arr.length);
            this.tree = new long[treeSize];
            initTree(arr, 1, arr.length - 1, 1);
        }

        public void calTreeSize(int arrSize) {
            double height = Math.ceil(Math.log(arrSize) / Math.log(2));
            this.treeSize = (int)Math.pow(2, height + 1);
        }

        public void initTree(long[] arr, int start, int end, int node) {
            if (start == end) {
                this.tree[node] = arr[start];
                return;
            }
            int mid = (start + end) / 2;
            initTree(arr, start, mid, node * 2);
            initTree(arr, mid + 1, end, node * 2 + 1);
            this.tree[node] = this.tree[node * 2] + this.tree[node * 2 + 1];
        }

        public void change(int start, int end, int node, int idx, long value) {
            if (start > idx || end < idx) {
                return;
            }
            if (start == end) {
                this.tree[node] = value;
                return;
            }
            int mid = (start + end) / 2;
            change(start, mid, node * 2, idx, value);
            change(mid + 1, end, node * 2 + 1, idx, value);
            this.tree[node] = this.tree[node * 2] + this.tree[node * 2 + 1];
        }

        public long getSum(int start, int end, int from, int to, int node) {
            if (end < from || to < start) {
                return 0;
            }
            if (from <= start && end <= to) {
                return this.tree[node];
            }
            int mid = (start + end) / 2;
            return getSum(start, mid, from, to, node * 2) + getSum(mid + 1, end, from, to, node * 2 + 1);
        }
    }
}