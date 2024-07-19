import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final String SAVE_HIM = "SAVE HIM";
    static final String GOOD_BYE = "GOOD BYE";

    static String answer = GOOD_BYE;
    static ArrayList<ArrayList<int[]>> list;
    static int V, E, P;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new int[] {b, c});
            list.get(b).add(new int[] {a, c});
        }

        PriorityQueue<Context> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.length));
        q.offer(new Context(1, 0, false));

        int[] min = new int[V + 1];
        Arrays.fill(min, 987654321);
        min[1] = 0;

        while (!q.isEmpty()) {
            Context context = q.poll();
            int cur = context.cur;
            int length = context.length;
            boolean save = context.save;

            if (cur == P) {
                save = true;
            }

            if(cur == V) {
                if(save) {
                    answer = SAVE_HIM;
                }
                continue;
            }

            for (int[] next : list.get(cur)) {
                if (min[next[0]] >= length + next[1]) {
                    min[next[0]] = length + next[1];
                    q.offer(new Context(next[0], length + next[1], save));
                }
            }
        }
        System.out.println(answer);
    }

    static class Context {
        int cur;
        int length;
        boolean save;

        public Context(int cur, int length, boolean save) {
            this.cur = cur;
            this.length = length;
            this.save = save;
        }
    }
}