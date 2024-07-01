import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer read;

        read = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(read.nextToken());
        int m = Integer.parseInt(read.nextToken());

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int finish = 0;
        for (int turn = 1; turn <= m; turn++) {
            read = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(read.nextToken());
            int b = Integer.parseInt(read.nextToken());

            if(find(a) == find(b)) {
                finish = turn;
                break;
            } else {
                union(a, b);
            }
        }
        System.out.println(finish);
    }

    static int find(int a) {
        if (parent[a] == a)
            return a;
        else
            return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA != parentB) {
            if (parentA < parentB) {
                parent[parentB] = parentA;
            } else {
                parent[parentA] = parentB;
            }
        }
    }
}