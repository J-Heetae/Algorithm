import java.io.*;
import java.util.*;

public class Main {
    static class Req {
        int a, b;
        Req(int a, int b) { this.a = a; this.b = b; }
    }

    static int[] parent; // parent[x] = x 이상에서 "다음 가용 책 번호"의 대표

    static int find(int x) {
        if (x > parent.length - 1) return x;         // N+1 등 가드
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean assign(int a, int b) {
        int x = find(a);                 // a 이상에서 아직 안 준 가장 작은 책
        if (x > b) return false;         // 구간 내에 남은 책이 없음
        // x 책을 배정하고, 다음 탐색은 x+1부터 하도록 union
        parent[x] = find(x + 1);
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            Req[] reqs = new Req[M];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                reqs[i] = new Req(a, b);
            }
            // 오른쪽 끝 b 오름차순, 같으면 a 오름차순
            Arrays.sort(reqs, (r1, r2) -> r1.b == r2.b ? r1.a - r2.a : r1.b - r2.b);

            // 유니온 파인드 초기화: parent[i] = i
            parent = new int[N + 2]; // N+1 가드 사용
            for (int i = 1; i <= N + 1; i++) parent[i] = i;

            int ans = 0;
            for (Req r : reqs) {
                if (assign(r.a, r.b)) ans++;
            }
            out.append(ans).append('\n');
        }
        System.out.print(out);
    }
}
