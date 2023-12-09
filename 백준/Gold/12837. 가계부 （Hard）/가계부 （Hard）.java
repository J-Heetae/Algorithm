import java.io.*;
import java.util.StringTokenizer;

class Main {

    static int N;
    static long[] tree;
    static long change;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int treeSize = (int) Math.pow(2, (int)(Math.ceil(Math.log(N)/Math.log(2))) + 1);
        tree = new long[treeSize + 1];

        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());

            int queryNum = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(queryNum == 1) add(1, 1, N, a, b);
            else if (queryNum == 2) print(a, b, bw);
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void add(int node, int left, int right, int day, long mount) {
        if(right < day) return;

        if(left >= day) {
            tree[node] += mount;
            return;
        }

        int mid = (left + right) / 2;
        add(node*2, left, mid, day, mount);
        add(node*2+1, mid+1, right, day, mount);
    }

    private static void print(int from, int to, BufferedWriter bw) throws IOException {
        change = 0L;
        get(1, from - 1, 1, N, 0L, true);
        get(1, to, 1, N, 0L, false);
        bw.write(change + "\n");
    }

    private static void get(int node, int target, int left, int right, long mount, boolean isBefore) {
        if(left > target || right < target) return;

        if(left >= right) {
            if(isBefore) change -= (mount + tree[node]);
            else change += (mount + tree[node]);
            return;
        }

        int mid = (left + right) / 2;
        get(node * 2, target, left, mid, mount + tree[node], isBefore);
        get(node * 2 + 1, target, mid + 1, right, mount + tree[node], isBefore);
    }
}