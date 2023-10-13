import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static final boolean MIN = true;
    static final boolean MAX = false;

    static int n, m, min, max;
    static int[] nums, minTree, maxTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] read = br.readLine().split(" ");

        n = stoi(read[0]);
        m = stoi(read[1]);

        nums = new int[n + 1];
        for(int i=1; i<=n; i++) {
            nums[i] = stoi(br.readLine());
        }

        int depth = getTreeDepth();

        minTree = new int[depth];
        maxTree = new int[depth];

        makeTree(minTree, MIN, 1, n, 1);
        makeTree(maxTree, MAX, 1, n, 1);

        StringBuilder answer = new StringBuilder();
        int from, to;
        for(int i=0; i<m; i++) {
            read = br.readLine().split(" ");
            from = stoi(read[0]);
            to = stoi(read[1]);

            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            findNum(1, n, from, to, 1);

            answer.append(min)
                    .append(" ")
                    .append(max)
                    .append("\n");
        }
        System.out.println(answer);
    }

    private static void findNum(int start, int end, int from, int to, int curNode) {
        if(to < start || end < from) {
            return;
        }

        if(start >= from && to >= end) {
            min = Math.min(min, minTree[curNode]);
            max = Math.max(max, maxTree[curNode]);
            return;
        }

        int mid = (start + end) / 2;
        findNum(start, mid, from, to, curNode * 2);
        findNum(mid + 1, end, from, to, curNode * 2 + 1);
    }

    private static void makeTree(int[] tree, boolean isMinTree, int start, int end, int curNode) {
        if(start == end) {
            tree[curNode] = nums[start];
            return;
        }

        int mid = (start + end) / 2;
        makeTree(tree, isMinTree, start, mid, curNode * 2);
        makeTree(tree, isMinTree, mid + 1, end, curNode * 2 + 1);

        if(isMinTree) {
            tree[curNode] = Math.min(tree[curNode * 2], tree[curNode * 2 + 1]);
        } else {
            tree[curNode] = Math.max(tree[curNode * 2], tree[curNode * 2 + 1]);
        }
    }

    private static int getTreeDepth() {
        return (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)) + 1);
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
}