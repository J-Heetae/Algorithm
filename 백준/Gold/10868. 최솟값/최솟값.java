import java.io.*;
import java.util.StringTokenizer;

class Main {

    static int N, M, min, from, to;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] numArr = new int[N + 1];
        for(int i=1; i<=N; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
        }

        makeTree(numArr);

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            min = Integer.MAX_VALUE;
            findMinNum(1, N, 1);
            bw.write(min + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void findMinNum(int start, int end, int node) {
        if(end < from || to < start) {
            return;
        }

        if(start >= from && end <= to) {
            min = Math.min(min, tree[node]);
            return;
        }

        int mid = (start + end) / 2;
        findMinNum(start, mid, node * 2);
        findMinNum(mid + 1, end, node * 2 + 1);
    }

    private static void makeTree(int[] arr) {
        int depth = getTreeDepth();
        tree = new int[depth];
        fillTree(arr, 1, N, 1);
    }

    private static void fillTree(int[] arr, int start, int end, int node) {
        if(start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        fillTree(arr, start, mid, node * 2);
        fillTree(arr, mid + 1, end, node * 2 + 1);

        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }

    private static int getTreeDepth() {
        return (int)Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)) + 1);
    }
}