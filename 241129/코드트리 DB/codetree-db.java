import java.util.*;
import java.io.*;

public class Main {

    static class DynamicSegmentTree {
        class Node {
            int count;
            long sum;
            String name;
            Node left, right;
            
            Node() {
                count = 0;
                sum = 0;
                name = null;
                left = right = null;
            }
        }

        Node root;
        int minRange, maxRange;
        
        DynamicSegmentTree(int minRange, int maxRange) {
            this.root = new Node();
            this.minRange = minRange;
            this.maxRange = maxRange;
        }

        boolean exists(int value) {
            return findValue(root, minRange, maxRange, value);
        }

        boolean findValue(Node node, int start, int end, int value) {
            if(node == null) {
                return false;
            }

            if(start == end) {
                return node.count != 0;
            }

            int mid = (start + end) / 2;
            if(value <= mid) {
                return findValue(node.left, start, mid, value);
            } else {
                return findValue(node.right, mid + 1, end, value);
            }
        }
        

        void insert(int pos, int value, String name) {
            query(root, minRange, maxRange, pos, value, name);
        }
        
        void delete(int pos, int value) {
            query(root, minRange, maxRange, pos, -value, null);
        }

        private void query(Node node, int start, int end, int pos, int value, String name) {
            if(start == end) {
                node.count += (value > 0)? 1 : -1;
                node.sum += (long)value;
                node.name = name;
                return;
            }

            int mid = (start + end) / 2;
            if(pos <= mid) {
                if(node.left == null) {
                    node.left = new Node();
                }
                query(node.left, start, mid, pos, value, name);
            } else {
                if(node.right == null) {
                    node.right = new Node();
                }
                query(node.right, mid + 1, end, pos, value, name);
            }

            node.count = ((node.left == null) ? 0 : node.left.count) +
                ((node.right == null) ? 0 : node.right.count);
            node.sum = ((node.left == null) ? 0 : node.left.sum) +
                ((node.right == null) ? 0 : node.right.sum);
        }

        String rank(int k) {
            return findRank(root, minRange, maxRange, k);
        }

        private String findRank(Node node, int start, int end, int k) {
            if(node == null || k > node.count) {
                return "None";
            }

            if(start == end) {
                return node.name;
            }

            int leftCount = (node.left == null) ? 0 : node.left.count;
            int mid = (start + end) / 2;
            if(k <= leftCount) {
                return findRank(node.left, start, mid, k);
            } else {
                return findRank(node.right, mid + 1, end, k - leftCount);
            }
        }

        long sum(int k) {
            return findSum(root, minRange, maxRange, 1, k);
        }
        
        private long findSum(Node node, int start, int end, int from, int to) {
            if(node == null || end < from || to < start) {
                return 0L;
            }

            if(from <= start && end <= to) {
                return node.sum;
            }

            int mid = (start + end) / 2;
            return findSum(node.left, start, mid, from, to) + 
                findSum(node.right, mid + 1, end, from, to);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Map<String, Integer> table = new HashMap<>();
        DynamicSegmentTree dst = new DynamicSegmentTree(1, 1_000_000_000);

        int Q = Integer.parseInt(br.readLine());
        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            String query = st.nextToken();
            
            if(query.equals("init")) {
                table.clear();
                dst = new DynamicSegmentTree(1, 1_000_000_000);

            } else if(query.equals("insert")) {
                String name = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                if(table.containsKey(name) || dst.exists(value)) {
                    sb.append(0).append("\n");
                } else {
                    table.put(name, value);
                    dst.insert(value, value, name);
                    sb.append(1).append("\n");
                }
            } else if(query.equals("delete")) {
                String name = st.nextToken();

                if(table.containsKey(name)) {
                    int value = table.get(name);
                    table.remove(name);
                    dst.delete(value, value);
                    sb.append(value).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else if(query.equals("rank")) {
                int k = Integer.parseInt(st.nextToken());
                String name = dst.rank(k);
                sb.append(name).append("\n");
            } else if(query.equals("sum")) {
                int k = Integer.parseInt(st.nextToken());
                long value = dst.sum(k);
                sb.append(value).append("\n");
            }
        }
        System.out.print(sb);
    }
}