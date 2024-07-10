import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());

        int subin = Integer.parseInt(tokenizer.nextToken());
        int brother = Integer.parseInt(tokenizer.nextToken());

        int[] before = new int[100_001];
        int[] time = new int[100_001];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(subin);
        before[subin] = -1;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == brother)
                break;

            int[] nextPos = {now - 1, now + 1, now * 2};
            for (int next : nextPos) {
                if (next >= 0 && next <= 100_000 && next != subin && time[next] == 0) {
                    time[next] = time[now] + 1;
                    before[next] = now;
                    queue.add(next);
                }
            }
        }

        Stack<Integer> stack = new Stack<>();
        int tracking = brother;
        do {
            stack.add(tracking);
            tracking = before[tracking];
        } while (tracking != -1);

        StringBuilder sb = new StringBuilder();
        sb.append(time[brother]).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }
}