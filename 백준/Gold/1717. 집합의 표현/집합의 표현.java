import java.awt.print.Pageable;
import java.io.*;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] read = br.readLine().split(" ");

        int n = stoi(read[0]);
        int m = stoi(read[1]);

        parent = new int[n+1];
        set(parent);

        for(int i=0; i<m;i ++) {
            read = br.readLine().split(" ");

            int command = stoi(read[0]);
            int a = stoi(read[1]);
            int b = stoi(read[2]);

            if(command == 0) {
                union(a, b);
            } else {
                bw.write(isUnion(a, b));
            }
        }
        bw.flush();
    }

    static void set(int[] arr) {
        for(int i=1; i<arr.length; i++) {
            arr[i] = i;
        }
    }

    static void union(int a, int b) {
        int parentOfA = find(a);
        int parentOfB = find(b);

        if(parentOfA != parentOfB) {
            if(parentOfA <= parentOfB) {
                parent[parentOfB] = parentOfA;
            } else {
                parent[parentOfA] = parentOfB;
            }
        }
    }

    static int find(int n) {
        if(parent[n] == n) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    static String isUnion(int a, int b) {
        if(find(a) == find(b)) {
            return "YES\n";
        }
        return "NO\n";
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
